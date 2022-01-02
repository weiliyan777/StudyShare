package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.SelectCourse;
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
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("SelectCourseDao")
public class SelectCourseImpl implements SelectCourseDao{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int insertSelectCourse(SelectCourse selectCourse) {
        Query query = new Query();
        Criteria criteria = Criteria.where("user_id").is(selectCourse.getUser_id());
        query.addCriteria(criteria);

        List<SelectCourse> querySelectCourse = mongoTemplate.find(query, SelectCourse.class, "Select_Course");
        if(querySelectCourse.size() > 0){
            return 0;
        }
        SelectCourse res = mongoTemplate.insert(selectCourse, "Select_Course");
        if(res != null){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int deleteSelectCourse(SelectCourse selectCourse) {
        DeleteResult res = mongoTemplate.remove(selectCourse,"Select_Course");
        if(res.wasAcknowledged()) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int deleteCourseIdInUserPublicCourse(String user_id, String course_id) {
        Query query =new Query();
        Criteria c1=Criteria.where("user_id").is(user_id);
        query.addCriteria(c1);
        SelectCourse selectCourse = mongoTemplate.findOne(query, SelectCourse.class, "Select_Course");
        assert selectCourse != null;
        List<String> publicCourses = selectCourse.getPublic_Courses();
        if (publicCourses.isEmpty()){
            return  0;
        }
        if (publicCourses.contains(course_id)){
            publicCourses.remove(course_id);
            selectCourse.setPublic_Courses(publicCourses);
            updateSelectCourse(selectCourse);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateSelectCourse(SelectCourse selectCourse) {
        Query query =new Query();
        Criteria c1=Criteria.where("_id").is(selectCourse.get_id());
        query.addCriteria(c1);
        SelectCourse querySelectCourse=mongoTemplate.findOne(query,SelectCourse.class,"Select_Course");
        if(querySelectCourse==null)
            return 0;
        Update update=new Update();
        update.set("Private_Courses",selectCourse.getPrivate_Courses());
        update.set("Public_Courses",selectCourse.getPublic_Courses());
        UpdateResult updateResult=mongoTemplate.updateFirst(query,update,"Select_Course");
        if(updateResult.wasAcknowledged())
            return 1;
        else
            return 0;
    }


    @Override
    public List<SelectCourse> findSelectCourseByUser_id(String user_id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("user_id").is(user_id);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, SelectCourse.class,"Select_Course");
    }


    @Override
    public List<String> findCoursesByUser_idAndPermissionInPage(String user_id, int permission , int pageIndex, int pageSize) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(user_id));
        Long count = mongoTemplate.count(query, SelectCourse.class);
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "_id"));
        query.with(pageable);
        List<SelectCourse> selectCourses = mongoTemplate.find(query,SelectCourse.class,"Select_Course");
        if(selectCourses.isEmpty()){
            return  new ArrayList<String>();
        }
        else {
            SelectCourse selectCourse = selectCourses.get(0);
            if(permission == 0){
                return selectCourse.getPublic_Courses();
            }
            else {
                return selectCourse.getPrivate_Courses();
            }
        }
    }
}
