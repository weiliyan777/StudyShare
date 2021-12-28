package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.Live;
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

@Repository("LiveDao")
public class LiveDaoImpl implements LiveDao{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int insertLive(Live live) {
        Query query = new Query();
        Criteria criteria = Criteria.where("course_id").is(live.getCourse_id());
        query.addCriteria(criteria);
        List<Live> queryLives = mongoTemplate.find(query, Live.class, "Live");
        if(queryLives.size() > 0){
            return 0;
        }
        Live res = mongoTemplate.insert(live, "Live");
        if(res != null){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int deleteLive(Live live) {
        DeleteResult res = mongoTemplate.remove(live,"Live");
        if(res.wasAcknowledged()) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public List<Live> findLiveByCourse_id(String course_id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("course_id").is(course_id);
        query.addCriteria(criteria);
        return mongoTemplate.find(query,Live.class,"Live");
    }

    @Override
    public List<Live> findLiveByCourse_id(String course_id, int pageIndex, int pageSize) {
        Query query = new Query();
        query.addCriteria(Criteria.where("course_id").is(course_id));
        Long count = mongoTemplate.count(query, Live.class);
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "_id"));
        query.with(pageable);
        List<Live> lives = mongoTemplate.find(query, Live.class,"Live");
        return lives;
    }

    @Override
    public List<Live> findLiveByCourse_idAndStatus(String course_id, int status) {
        Query query = new Query();
        Criteria criteria = Criteria.where("course_id").is(course_id).and("status").is(status);
        query.addCriteria(criteria);
        return mongoTemplate.find(query,Live.class,"Live");
    }

    @Override
    public List<Live> findLiveByCourse_idAndStatusPage(String course_id, int status, int pageIndex, int pageSize) {
        Query query = new Query();
        query.addCriteria(Criteria.where("course_id").is(course_id).and("status").is(status));
        Long count = mongoTemplate.count(query, Live.class);
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "_id"));
        query.with(pageable);
        List<Live> lives = mongoTemplate.find(query, Live.class,"Live");
        return lives;
    }
}
