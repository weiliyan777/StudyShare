package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.Course;
import cn.USTCSEwwww.demo.Model.CourseFile;
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

import java.util.List;

@Repository("CourseFileDao")
public class CourseFileDaoImpl implements CourseFileDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int insertCourseFile(CourseFile courseFile) {
        Query query =new Query();
        Criteria c1=Criteria.where("name").is(courseFile.getName());
        query.addCriteria(c1);
        List<CourseFile> queryCourseFiles=mongoTemplate.find(query,CourseFile.class,"CourseFile");
        if(queryCourseFiles.size() >= 1){
            CourseFile cd_in_db = queryCourseFiles.get(0);
            int num = cd_in_db.getName_id();
            num = num + 1;
            cd_in_db.setName_id(num);
            String cfName = courseFile.getName();
            cfName += ("("+Integer.toString(num)+")");
            courseFile.setName(cfName);
            // 更新操作
            int updateRes = updateCourseFile(cd_in_db);
            if(updateRes == 0){
                System.out.println("fail to update date in insert operation");
                return  0;
            }
        }
        CourseFile res=mongoTemplate.insert(courseFile,"CourseFile");
        if(res != null)
            return 1;
        else
            return 0;

    }

    @Override
    public int deleteCourseFile(String _id) {
        Query query =new Query();
        Criteria c1=Criteria.where("_id").is(_id);
        query.addCriteria(c1);
        DeleteResult res=mongoTemplate.remove(query,"CourseFile");
        if(res.wasAcknowledged())
            return 1;
        else
            return 0;
    }

    @Override
    public int updateCourseFile(CourseFile courseFile) {
        if(courseFile==null)
            return 0;
        Query query =new Query();
        Criteria c1=Criteria.where("_id").is(courseFile.get_id());
        query.addCriteria(c1);
        Update update=new Update();
        update.set("name_id",courseFile.getName_id());
        update.set("name",courseFile.getName());
        update.set("upload_user",courseFile.getUpload_user());
        update.set("role",courseFile.getRole());
        update.set("course_id",courseFile.getCourse_id());
        update.set("course_name",courseFile.getCourse_name());
        update.set("type",courseFile.getType());
        update.set("upload_time",courseFile.getUpload_time());
        update.set("score",courseFile.getScore());
        update.set("file",courseFile.getFile());
        UpdateResult updateResult=mongoTemplate.updateFirst(query,update,"CourseFile");
        if(updateResult.wasAcknowledged())
            return 1;
        else
            return 0;
    }

    @Override
    public CourseFile findCourseFileBy_id(String _id) {
        return mongoTemplate.findById(_id,CourseFile.class,"CourseFile");
    }

    @Override
    public List<CourseFile> findCourseFileByCourse_id(String course_id) {
        Query query=new Query();
        Criteria c1=Criteria.where("course_id").is(course_id);
        query.addCriteria(c1);
        return mongoTemplate.find(query,CourseFile.class,"CourseFile");
    }

    @Override
    public List<CourseFile> findAllCourseFile() {
        Query query=new Query();
        return mongoTemplate.find(query,CourseFile.class,"CourseFile");
    }

    @Override
    public List<CourseFile> findCourseFileByCourse_id_Page(String course_id, int pageIndex, int pageSize) {
        Query query=new Query();
        Pageable pageable= PageRequest.of(pageIndex,pageSize, Sort.by(Sort.Direction.ASC,"_id"));
        query.with(pageable);
        Criteria c1=Criteria.where("course_id").is(course_id);
        query.addCriteria(c1);
        return mongoTemplate.find(query,CourseFile.class,"CourseFile");
    }

    @Override
    public List<CourseFile> findCourseFileByUpload_user(String upload_user) {
        Query query=new Query();
        Criteria c1=Criteria.where("upload_user").is(upload_user);
        query.addCriteria(c1);
        return mongoTemplate.find(query,CourseFile.class,"CourseFile");
    }

    @Override
    public List<CourseFile> findCourseFileByUpload_user(String upload_user, int pageIndex, int pageSize) {
        Query query=new Query();
        Pageable pageable= PageRequest.of(pageIndex,pageSize, Sort.by(Sort.Direction.ASC,"_id"));
        query.with(pageable);
        Criteria c1=Criteria.where("upload_user").is(upload_user);
        query.addCriteria(c1);
        return mongoTemplate.find(query,CourseFile.class,"CourseFile");
    }
}
