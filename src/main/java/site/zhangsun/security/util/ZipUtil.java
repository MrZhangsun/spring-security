package site.zhangsun.security.util;

import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * <p> Function: Zip tool for compress and decompress zip file.</p>
 *
 * @author: zhangsunjiankun 2018/4/14 下午9:42
 */
public class ZipUtil {

    /**
     * <p>
     *     Compress file to zip file and sign the compressed file name as the zip file name.
     * </p>
     * @param srcFilePath Path of the compressed file
     * @param zipFilePath The zip file root path
     */
    public static void compress(String srcFilePath, String zipFilePath) {
        File srcFile = new File(srcFilePath);
        String zipName;
        if (srcFile.isFile()) {
            String fileName = srcFilePath.substring(srcFilePath.lastIndexOf(File.separator));
            zipName = fileName.substring(0, fileName.lastIndexOf(".")) + ".zip";
        } else {
            zipName = srcFilePath.substring(srcFilePath.lastIndexOf(File.separator)) + ".zip";
        }
        compress(srcFilePath, zipFilePath, zipName);
    }

    /**
     * <p>
     *     Compress file to zip file and rename zip file name.
     * </p>
     *
     * @param srcFilePath Path of the compressed file
     * @param zipFilePath The zip file root path
     * @param zipName The zip file name
     */
    public static void compress(String srcFilePath,  String zipFilePath, String zipName) {
        File srcFile = new File(srcFilePath);

        if (StringUtils.isBlank(srcFilePath) || StringUtils.isBlank(zipFilePath) || StringUtils.isBlank(zipName)) {
            throw new RuntimeException("The compress params can't be empty!");
        }

        if (!srcFile.exists()) {
            throw new RuntimeException("The srcFile doesn't exist!");
        }

        if (srcFile.isDirectory() && zipFilePath.contains(srcFilePath)) {
            /*
             * srcFile = /Users/personal/srcFile
             * zipFile = /Users/personal/srcFile/inner
             */
            throw new RuntimeException("The zipFile path can not be included by srcFile path!");
        }

        File zipRootDir = new File(zipFilePath);
        if (!zipRootDir.exists() || zipRootDir.isFile()) {
            zipRootDir.mkdirs();
        }

        ZipOutputStream zos = null;
        CheckedOutputStream cos;

        // create zip file
        File zipFile = new File(zipFilePath + File.separator + zipName);
        if (zipFile.exists()) {
            throw new RuntimeException("The file has existed!");
        }


        try {
            cos = new CheckedOutputStream(new FileOutputStream(zipFile), new CRC32());
            zos = new ZipOutputStream(cos);
            String rootDir = srcFilePath;
            if (srcFile.isFile()) {
                rootDir = srcFilePath.substring(0, srcFilePath.lastIndexOf(File.separator));
            }

            // Recursion compression
            recursion(rootDir, srcFile, zos);
            zos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert zos != null;
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * <p>
     *     Recursion compress zip file
     * </p>
     *
     * @param rootDir The root directory of the srcFile
     * @param srcFile The srcFile
     * @param zos The zip output stream
     */
    private static void recursion(String rootDir, File srcFile, ZipOutputStream zos) throws IOException {
        BufferedInputStream bis;
        if (srcFile.isDirectory()) {
            File[] files = srcFile.listFiles();
            assert files != null;
            for (File file : files) {
                recursion(rootDir, file, zos);
            }
        } else {
            /* substring the relative path of srcFile to root directory
             *
             * srcFileAbsolutePath = /usr/local/share/1.txt
             * rootDir = /usr/local
             * relativePath = /share/1.txt
             */
            String srcFileAbsolutePath = srcFile.getAbsolutePath();
            String relativePath = srcFileAbsolutePath.replace(rootDir, "");
            ZipEntry nextEntry = new ZipEntry(relativePath);
            zos.putNextEntry(nextEntry);


            byte[] buffer = new byte[1024];
            int count;
            bis = new BufferedInputStream(new FileInputStream(srcFile));
            while ((count = bis.read(buffer)) != -1) {
                zos.write(buffer, 0, count);
            }
            bis.close();
            zos.closeEntry();
        }
    }


    /**
     * <p>
     *     Decompress zip file.
     * </p>
     * @param zipFilePath Zip file path
     * @param unZipFilePath Decompressed file path
     * @param unZipHere Whether decompress zip file with zip file name:
     *                  false : exclude the zip file name
     *                  true  : include the zip file name
     */
    public static void decompress(String zipFilePath, String unZipFilePath, Boolean unZipHere) throws IOException {
        if (StringUtils.isBlank(zipFilePath) || StringUtils.isBlank(unZipFilePath) || unZipHere == null) {
            throw new RuntimeException("The decompress params can not be empty!");
        }

        File zipFile = new File(zipFilePath);
        if (!zipFile.exists() || zipFile.isDirectory()) {
            throw new RuntimeException("The zip file doesn't exist or is not a file!");
        }

        if (unZipHere) {
            String zipFileName = zipFile.getName();
            unZipFilePath += File.separator + zipFileName.substring(0, zipFileName.lastIndexOf("."));
        }

        File unzipFile = new File(unZipFilePath);
        if (!unzipFile.exists() || unzipFile.isFile()) {
            unzipFile.mkdirs();
        }

        ZipEntry entry;
        String entryPath;
        String entryRoot;

        byte[] buffer = new byte[1024];
        int count;

        BufferedInputStream bis;
        BufferedOutputStream bos;

        ZipFile zip = new ZipFile(zipFile);
        Enumeration<? extends ZipEntry> entries = zip.entries();
        while (entries.hasMoreElements()) {
            entry = entries.nextElement();
            entryPath = unZipFilePath + File.separator + entry.getName();
            // entryPath :c:/file or /usr
            // entryRoot :c:/     or /
            entryRoot = entryPath.substring(0, entryPath.lastIndexOf(File.separator));
            File entryRootFile = new File(entryRoot);
            if (!entryRootFile.exists() || entryRootFile.isFile()) {
                entryRootFile.mkdirs();
            }

            File entryFile = new File(entryPath);
            if (entryFile.exists() && entryFile.isFile()) {
                SecurityManager securityManager = new SecurityManager();
                securityManager.checkDelete(entryPath);
                //删除已存在的目标文件
                entryFile.delete();
            }

            bos = new BufferedOutputStream(new FileOutputStream(entryFile));
            bis = new BufferedInputStream(zip.getInputStream(entry));
            while ((count = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, count);
            }
            bos.flush();
            bos.close();
        }
    }


    public static void main(String[] args) throws IOException {
        String srcFilePath = "/Users/zhangsunjiankun/Documents/personal/srcFile";
        String zipRootPath = "/Users/zhangsunjiankun/Documents/personal/";
        String zipFileName = "hello.zip";
//        compress(srcFilePath, zipRootPath, zipFileName);
//        compress(srcFilePath, zipRootPath);

        String zipFilePath = "/Users/zhangsunjiankun/Documents/personal/hello.zip";
        String unZipFilePath = "/Users/zhangsunjiankun/Documents/personal";
        Boolean unZipHere = false;
        decompress(zipFilePath, unZipFilePath, unZipHere);
    }

}
