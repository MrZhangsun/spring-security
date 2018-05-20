package site.zhangsun.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import site.zhangsun.security.pojo.ResultDTO;
import site.zhangsun.security.pojo.entity.Course;
import site.zhangsun.security.service.api.CourseService;

import java.util.List;

/**
 * <p> Function: </p>
 *
 * @author : zhangsunjiankun 2018/5/19 上午8:38
 */
@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/courses")
    public ResultDTO<List<Course>> getCourseList() {
        ResultDTO<List<Course>> courses = new ResultDTO<>();
        courses.setData(courseService.getCourseList());
        courses.setStatus(true);
        courses.setMessage("success");
        courses.setCode(200);
        return courses;
    }
}
