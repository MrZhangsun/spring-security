package site.zhangsun.security.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import site.zhangsun.security.pojo.entity.SysUserRole;
import site.zhangsun.security.pojo.entity.SysUserRoleExample;
import site.zhangsun.security.pojo.entity.SysUserRoleKey;

public interface SysUserRoleMapper {
    int countByExample(SysUserRoleExample example);

    int deleteByExample(SysUserRoleExample example);

    int deleteByPrimaryKey(SysUserRoleKey key);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    List<SysUserRole> selectByExample(SysUserRoleExample example);

    SysUserRole selectByPrimaryKey(SysUserRoleKey key);

    int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    int updateByExample(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}