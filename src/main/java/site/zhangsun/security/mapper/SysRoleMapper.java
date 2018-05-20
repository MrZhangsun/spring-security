package site.zhangsun.security.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import site.zhangsun.security.pojo.entity.SysRole;
import site.zhangsun.security.pojo.entity.SysRoleExample;
import site.zhangsun.security.pojo.entity.SysRoleKey;

public interface SysRoleMapper {
    int countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(SysRoleKey key);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(SysRoleKey key);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}