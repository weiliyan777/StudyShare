package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Model.Course;
import cn.USTCSEwwww.demo.Model.CourseFile;
import cn.USTCSEwwww.demo.Model.SelectCourse;
import cn.USTCSEwwww.demo.Model.User;

import java.util.List;

public interface CourseService {
    public int insertCourse(Course course);

    public int insertCourses(List<Course> courses);

    public int deleteCourse(Course course);

    public int deleteCourse(List<Course> courses);

    public int updateCourse(Course course);

    public List<Course> findCoursesByTeacherId(String teacherId,int pageIndex,int pageSize);

    public Course findCoursesByCourse_Id(String course_id);

    public List<Course> findCoursesAll(int pageIndex,int pageSize);

    public List<Course> findCoursesPublic(int pageIndex,int pageSize);


    /**
     * 学生选择公开的课程
     * @param  user_id, course_id
     * @return 1-成功 0-失败
     */
    public int SelectPublicCourse(String user_id,String course_id);

    /**
     * 学生删除已选的公开课程 只用user_id,course_id
     * @param  user_id, course_id
     * @return 1-成功 0-失败
     */
    public int deleteSelectedPublicCourse(String user_id,int course_id);

    /**
     * 查找用户已选的某种权限（公开/私密）的课程
     * @return
     */
    public List<SelectCourse> findSelectCoursesByUser_idAndPermission(String user_id,int permission,int pageIndex,
                                                                      int pageSize);

}
