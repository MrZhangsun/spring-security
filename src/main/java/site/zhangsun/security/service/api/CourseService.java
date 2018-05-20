package site.zhangsun.security.service.api;

import site.zhangsun.security.pojo.entity.Course;

import java.util.List;

/**
 * <p> Function: Course Service</p>
 *
 * @author : zhangsunjiankun 2018/5/19 上午8:43
 */
public interface CourseService {
    /**
     * 查询课程列表
     *
     * @return 课程列表
     */
    List<Course> getCourseList();
}
