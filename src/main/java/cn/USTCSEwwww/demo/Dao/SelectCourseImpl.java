package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.SelectCourse;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

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
        List<SelectCourse> queryLives = mongoTemplate.find(query, SelectCourse.class, "Select_Course");
        if(queryLives.size() > 0){
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
    public List<SelectCourse> findSelectCourseByUser_id(String user_id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("user_id").is(user_id);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, SelectCourse.class,"Select_Course");
    }

    @Override
    public List<SelectCourse> findSelectCourseByUser_idPage(String user_id, int pageIndex, int pageSize) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(user_id));
        Long count = mongoTemplate.count(query, SelectCourse.class);
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "_id"));
        query.with(pageable);
        List<SelectCourse> selectCourses = mongoTemplate.find(query,SelectCourse.class,"Select_Course");
//        return PageableExecutionUtils.getPage(users,pageable,"");
        return selectCourses;
    }
}
