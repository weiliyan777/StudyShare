package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Dao.CourseFileDao;
import cn.USTCSEwwww.demo.Model.CourseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CourseFileService")
public class CourseFileServiceImpl implements CourseFileService {

    @Autowired
    private CourseFileDao courseFileDao;

    @Override
    public int insertCourseFile(CourseFile courseFile) {

        return courseFileDao.insertCourseFile(courseFile);
    }

    @Override
    public int deleteCourseFile(String _id) {
        return courseFileDao.deleteCourseFile(_id);
    }

    @Override
    public int updateCourseFile(CourseFile courseFile) {
        return courseFileDao.updateCourseFile(courseFile);
    }

    @Override
    public CourseFile findCourseFileBy_id(String _id) {
        return courseFileDao.findCourseFileBy_id(_id);
    }

    @Override
    public List<CourseFile> findCourseFileByCourse_id(String course_id) {
        return courseFileDao.findCourseFileByCourse_id(course_id);
    }

    @Override
    public List<CourseFile> findAllCourseFile() {
        return courseFileDao.findAllCourseFile();
    }

    @Override
    public List<CourseFile> findCourseFileByCourse_id_Page(String course_id, int pageIndex, int pageSize) {
        return courseFileDao.findCourseFileByCourse_id_Page(course_id,pageIndex,pageSize);
    }

    @Override
    public List<CourseFile> findCourseFileByUpload_user(String upload_user) {
        return courseFileDao.findCourseFileByUpload_user(upload_user);
    }

    @Override
    public List<CourseFile> findCourseFileByUpload_user(String upload_user, int pageIndex, int pageSize) {
        return courseFileDao.findCourseFileByUpload_user(upload_user,pageIndex,pageSize);
    }
}
