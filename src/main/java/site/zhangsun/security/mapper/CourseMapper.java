package site.zhangsun.security.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import site.zhangsun.security.pojo.entity.Course;
import site.zhangsun.security.pojo.entity.CourseExample;
import site.zhangsun.security.pojo.entity.CourseKey;
import site.zhangsun.security.pojo.entity.CourseWithBLOBs;

public interface CourseMapper {
    int countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(CourseKey key);

    int insert(CourseWithBLOBs record);

    int insertSelective(CourseWithBLOBs record);

    List<CourseWithBLOBs> selectByExampleWithBLOBs(CourseExample example);

    List<Course> selectByExample(CourseExample example);

    CourseWithBLOBs selectByPrimaryKey(CourseKey key);

    int updateByExampleSelective(@Param("record") CourseWithBLOBs record, @Param("example") CourseExample example);

    int updateByExampleWithBLOBs(@Param("record") CourseWithBLOBs record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(CourseWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CourseWithBLOBs record);

    int updateByPrimaryKey(Course record);
}