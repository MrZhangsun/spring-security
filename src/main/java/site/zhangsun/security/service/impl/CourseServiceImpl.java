package site.zhangsun.security.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.zhangsun.security.mapper.CourseMapper;
import site.zhangsun.security.pojo.entity.Course;
import site.zhangsun.security.pojo.entity.CourseExample;
import site.zhangsun.security.service.api.CourseService;

import java.util.List;

/**
 * <p> Function: Course Service Impl</p>
 *
 * @author : zhangsunjiankun 2018/5/19 上午8:44
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    /**
     * @see CourseService#getCourseList()
     */
    @Override
    public List<Course> getCourseList() {
        CourseExample example = new CourseExample();
        example.createCriteria().andDeletedEqualTo(false);
        logger.info("select course list successfully!");
        return courseMapper.selectByExample(example);
    }
}
