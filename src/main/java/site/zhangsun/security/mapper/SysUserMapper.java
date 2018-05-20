package site.zhangsun.security.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import site.zhangsun.security.pojo.entity.SysUser;
import site.zhangsun.security.pojo.entity.SysUserExample;
import site.zhangsun.security.pojo.entity.SysUserKey;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(SysUserKey key);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(SysUserKey key);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}