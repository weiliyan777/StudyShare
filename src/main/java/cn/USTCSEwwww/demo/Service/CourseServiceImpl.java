package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Dao.CourseDao;
import cn.USTCSEwwww.demo.Dao.SelectCourseDao;
import cn.USTCSEwwww.demo.Model.Course;
import cn.USTCSEwwww.demo.Model.CourseFile;
import cn.USTCSEwwww.demo.Model.Live;
import cn.USTCSEwwww.demo.Model.Record;
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
    CourseFileService courseFileService;

    @Autowired
    SelectCourseDao selectCourseDao;

    @Override
    public int insertCourse(Course course) {
        try {
            Course findCourse=courseDao.findCourseByCourse_id(course.getCourse_id());
            if(findCourse!=null){
                throw new Exception(course.getCourse_id()+"course_id has used");
            }
            List<String> permissionStudents=course.getPermission_students();
            for(int i=0;i<permissionStudents.size();i++){
                SelectCourse selectCourse=selectCourseDao.findSelectCourseByUser_id(permissionStudents.get(i)).get(0);
                List<String> privateCourses=selectCourse.getPrivate_Courses();
                if(!privateCourses.contains(course.getCourse_id())){
                    privateCourses.add(course.getCourse_id());
                    selectCourse.setPrivate_Courses(privateCourses);
                    selectCourseDao.updateSelectCourse(selectCourse);
                }
            }
            return courseDao.insertCourse(course);
        }catch (Exception e){
            System.out.println(e.toString());
            return 0;
        }

    }

    @Override
    public int insertCourses(List<Course> courses) {
        int res=0;
        for(int i=0;i<courses.size();i++){
            res+=insertCourse(courses.get(i));
        }
        return res;
    }

    @Override
    public int deleteCourse(Course course) {
        try {
           List<CourseFile> courseFiles= courseFileService.findCourseFileByCourse_id(course.getCourse_id());
           List<Live> lives =liveService.findLivesByCourse_id(course.getCourse_id());
           List<Record> records=recordService.findRecordsByCourse_id(course.getCourse_id());
           int res;
           if(courseFiles.size()!=0){
               res=0;
               for(int i=0;i<courseFiles.size();i++){
                   res+=courseFileService.deleteCourseFile(courseFiles.get(i).get_id());
               }
               if(res!=courseFiles.size()){
                   throw new Exception("courseFile 未全部删除");
               }
           }
           if(lives.size()!=0){
               res=0;
               for(int i=0;i<lives.size();i++){
                   res+=liveService.deleteLive(lives.get(i));
               }
               if(res!=lives.size()){
                   throw new Exception("lives 未全部删除");
               }
           }
           if(records.size()!=0){
                res=0;
                for(int i=0;i<records.size();i++){
                    res+=recordService.deleteRecord(records.get(i));
                }
                if(res!=records.size()){
                    throw new Exception("records 未全部删除");
                }
           }

           return courseDao.deleteCourse(course);
        }catch (Exception e)
        {
            System.out.println(e.toString());
            return  0;
        }
    }

    @Override
    public int deleteCourse(List<Course> courses) {
        int res=0;
        for(int i=0;i<courses.size();i++){
            res+=deleteCourse(courses.get(i));
        }
        return res;
    }

    @Override
    public int updateCourse(Course course) {
        try{
            if(deleteCourse(course)!=0||insertCourse(course)!=0){
                return 1;
            }else {
                throw new Exception("插入失败");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            return 0;
        }
    }

    @Override
    public List<Course> findCoursesByTeacherId(String teacherId, int pageIndex, int pageSize) {
        return courseDao.findCourseByTeacherIdPage(teacherId,pageIndex,pageSize);
    }

    @Override
    public Course findCoursesByCourse_Id(String course_id) {
        return courseDao.findCourseByCourse_id(course_id);
    }

    @Override
    public List<Course> findCoursesAll(int pageIndex, int pageSize) {
        return courseDao.findCourseAllPage(pageIndex,pageSize);
    }

    @Override
    public List<Course> findCoursesPublic(int pageIndex, int pageSize) {
        return courseDao.findCoursesByPermission(0);
    }

    @Override
    public int SelectPublicCourse(String user_id,String course_id) {
        try {
            List<SelectCourse> selectCourses = selectCourseDao.findSelectCourseByUser_id(user_id);
            if(selectCourses.size()==0||selectCourses.size()>1){
                throw new Exception("SelectCourse 对象不唯一");
            }
            SelectCourse selectCourse=selectCourses.get(0);
            List<String> publicCourse=selectCourse.getPublic_Courses();
            if(publicCourse.contains(course_id)){
                throw new Exception("已选该课程");
            }
            publicCourse.add(course_id);
            selectCourse.setPublic_Courses(publicCourse);
            return selectCourseDao.updateSelectCourse(selectCourse);
        }catch (Exception e){
            System.out.println(e.toString());
            return 0;
        }
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
