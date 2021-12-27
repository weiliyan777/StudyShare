package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.Course;

import java.util.List;

public interface CourseDao {
    public int insertCourse(Course course);

    public int deleteCourse(Course course);

    public int updateCourse(Course course);

    public Course findCourseBy_id(String _id);

    public Course findCourseByCourse_id(String course_id);

    public List<Course> findCourseAll();

    public List<Course> findCourseAllPage(int pageIndex,int pageSize);

    public List<Course> findCoursesByPermission(int permission);

    public List<Course> findCoursesByPermissionPage(int permission,int pageIndex,int pageSize);
    /**
     * 模糊查询
     * @param likeStr
     * @return  List<Course>
     */
    public List<Course> findCoursesLike(String likeStr);

    public List<Course> findCoursesLikePage(String likeStr,int pageIndex,int pageSize);

    public List<Course> findCourseByTeacherId(String teacherId);

    public List<Course> findCourseByTeacherIdPage(String teacherId,int pageIndex,int pageSize);

}
