package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Dao.CourseDao;
import cn.USTCSEwwww.demo.Dao.SelectCourseDao;
import cn.USTCSEwwww.demo.Model.Course;
import cn.USTCSEwwww.demo.Model.SelectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    SelectCourseDao selectCourseDao;

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
    public int deleteSelectedPublicCourse(String user_id, String course_id) {
        return selectCourseDao.deleteCourseIdInUserPublicCourse(user_id,course_id);
    }

    @Override
    public List<Course> findSelectCoursesByUser_idAndPermission(String user_id, int permission, int pageIndex, int pageSize) {
        List<String> coursesId = selectCourseDao.findCoursesByUser_idAndPermissionInPage(user_id, permission, pageIndex, pageSize);
        if(coursesId.isEmpty()){
            return  new ArrayList<Course>();
        }
        else {
            List<Course> courses = new ArrayList<>();
            for (String id: coursesId) {
                Course course = courseDao.findCourseByCourse_id(id);
                if(course != null){
                    courses.add(course);
                }
            }
            return courses;
        }
    }
}
