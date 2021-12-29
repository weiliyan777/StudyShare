package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Dao.CourseDao;
import cn.USTCSEwwww.demo.Model.Course;
import cn.USTCSEwwww.demo.Model.SelectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("CourseService")
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseDao courseDao;

    @Autowired
    RecordService recordService;

    @Autowired
    LiveService liveService;

    @Autowired
    CourseService courseService;

    @Override
    public int insertCourse(Course course) {
//        try {
//            if()
//        }
        return 1;
    }

    @Override
    public int insertCourses(List<Course> courses) {
        return 0;
    }

    @Override
    public int deleteCourse(Course course) {
        return 0;
    }

    @Override
    public int deleteCourse(List<Course> courses) {
        return 0;
    }

    @Override
    public int updateCourse(Course course) {
        return 0;
    }

    @Override
    public List<Course> findCoursesByTeacherId(String teacherId, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Course findCoursesByCourse_Id(String course_id) {
        return null;
    }

    @Override
    public List<Course> findCoursesAll(int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public List<Course> findCoursesPublic(int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public int SelectPublicCourse(int user_id, int course_id) {
        return 0;
    }

    @Override
    public int deleteSelectedPublicCourse(int user_id, int course_id) {
        return 0;
    }

    @Override
    public List<SelectCourse> findSelectCoursesByUser_idAndPermission(String user_id, int permission, int pageIndex, int pageSize) {
        return null;
    }
}
