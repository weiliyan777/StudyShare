package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.CourseFile;

import java.util.List;

public interface CourseFileDao {

    public int insertCourseFile(CourseFile courseFile);

    public int deleteCourseFile(String _id);

    public int updateCourseFile(CourseFile courseFile);

    public CourseFile findCourseFileBy_id(String _id);

    public  CourseFile findCourseFileByName(String name);

    public List<CourseFile> findCourseFileByCourse_id(String course_id);

    public List<CourseFile> findAllCourseFile();

    public List<CourseFile> findCourseFileByCourse_id_Page(String course_id,int pageIndex,int pageSize);

    public List<CourseFile> findCourseFileByUpload_user(String upload_user);

    public List<CourseFile> findCourseFileByUpload_user(String upload_user,int pageIndex,int pageSize);
}
