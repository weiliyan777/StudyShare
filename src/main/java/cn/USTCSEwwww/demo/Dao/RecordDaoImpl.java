package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.Record;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.internal.bulk.InsertRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository("RecordDao")
public class RecordDaoImpl implements RecordDao{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int insertRecord(Record record) {
        Record record1=mongoTemplate.insert(record);
        if(record1!=null)
            return 1;
        else
            return 0;
    }

    @Override
    public int deleteRecord(Record record) {
        Query query=new Query();
        Criteria c1=Criteria.where("_id").is(record.get_id());
        query.addCriteria(c1);
        DeleteResult deleteResult= mongoTemplate.remove(query,Record.class);
        if(deleteResult.wasAcknowledged())
            return 1;
        else
            return 0;
    }

    @Override
    public Record findRecordByRecord_id(String record_id) {
        Query query=new Query();
        query.addCriteria(Criteria.where("record_id").is(record_id));
        return mongoTemplate.findOne(query,Record.class,"Record");
    }

    @Override
    public List<Record> findRecordByCourseId(String course_id) {
        Query query=new Query();
        Criteria c1=Criteria.where("course_id").is(course_id);
        query.addCriteria(c1);
        return mongoTemplate.find(query,Record.class,"Record");
    }

    @Override
    public List<Record> findRecordByCourseIdPage(String course_id, int pageIndex, int pageSize) {
        Query query=new Query();
        Criteria c1=Criteria.where("course_id").is(course_id);
        query.addCriteria(c1);
        Pageable pageable= PageRequest.of(pageIndex,pageSize, Sort.by(Sort.Direction.ASC,"_id"));
        query.with(pageable);
        return mongoTemplate.find(query,Record.class,"Record");
    }
}
