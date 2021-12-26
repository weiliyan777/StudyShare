package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.Course;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


import java.awt.desktop.QuitEvent;
import java.util.List;

public class CourseDaoImpl implements CourseDao{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int insertCourse(Course course) {
        Query query =new Query();
        Criteria c1=Criteria.where("course_id").is(course.getCourse_id());
        query.addCriteria(c1);
        List<Course> queryCourses=mongoTemplate.find(query,Course.class,"Course");
        if(queryCourses.size()>1)
            return 0;
        Course res=mongoTemplate.insert(course,"Course");
        if(res!=null)
            return 1;
        else
            return 0;
    }

    @Override
    public int deleteCourse(Course course) {
        DeleteResult res=mongoTemplate.remove(course);
        if(res.wasAcknowledged())
            return 1;
        else
            return 0;
    }

    @Override
    public int updateCourse(Course course) {
        if(course==null)
            return 0;
        Query query =new Query();
        Criteria c1=Criteria.where("_id").is(course.get_id());
        query.addCriteria(c1);

        Update update=new Update();
        update.set("course_id",course.getCourse_id());
        update.set("name",course.getName());
        update.set("teacherId",course.getTeacherId());
        update.set("permission",course.getPermission());
        update.set("startDate",course.getStartDate());
        update.set("permission_students",course.getPermission_students());
        update.set("files",course.getFiles());
        UpdateResult updateResult=mongoTemplate.updateFirst(query,update,"Course");
        if(updateResult.wasAcknowledged())
            return 1;
        else
            return 0;
    }

    @Override
    public Course findCourseBy_id(String _id) {
        return mongoTemplate.findById(_id,Course.class);
    }

    @Override
    public Course findCourseByCourse_id(String course_id) {
        Query query=new Query();
        Criteria c1=Criteria.where("course_id").is(course_id);
        query.addCriteria(c1);
        return mongoTemplate.findOne(query,Course.class);
    }

    @Override
    public List<Course> findCourseAll() {
        return null;
    }

    @Override
    public List<Course> findCourseAllPage(int pageIndex, int pageSize) {
        Query query=new Query();
        Pageable pageable= PageRequest.of(pageIndex,pageSize, Sort.by(Sort.Direction.ASC,"_id"));
        query.with(pageable);
        return mongoTemplate.find(query,Course.class,"Course");
    }

    @Override
    public List<Course> findCoursesByPermission(int permission) {
        return null;
    }

    @Override
    public List<Course> findCoursesByPermissionPage(int permission, int pageIndex, int pageSize) {
        Query query=new Query();
        query.addCriteria(Criteria.where("permission").is(permission));
        Pageable pageable=PageRequest.of(pageIndex,pageSize,Sort.by(Sort.Direction.ASC,"_id"));
        return null;
    }

    @Override
    public List<Course> findCoursesLike(String query) {
        return null;
    }

    @Override
    public List<Course> findCoursesLikePage(String query, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public List<Course> findCourseByTeacherId(String teacherId) {
        return null;
    }

    @Override
    public List<Course> findCourseByTeacherIdPage(String teacherId, int pageIndex, int pageSize) {
        return null;
    }
}
