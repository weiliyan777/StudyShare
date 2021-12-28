package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.User;
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
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository("UserDao")
public class UserDaoImpl implements UserDao{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int insertUser(User user) {
        //先判断学号是否重复
        Query query =new Query();
        Criteria c1=Criteria.where("user_id").is(user.getUser_id());
        query.addCriteria(c1);
        List<User> queryUsers= mongoTemplate.find(query,User.class,"User");
        if(queryUsers.size()>0)
                return 0;
        //进行插入
        User res= mongoTemplate.insert(user,"User");
        if(res!=null)
            return 1;
        else
            return 0;
    }

    @Override
    public int deleteUser(User user) {
        DeleteResult res=mongoTemplate.remove(user,"User");
        if(res.wasAcknowledged())
            return 1;
        else
            return 0;
    }

    @Override
    public int updateUser(User user) {
        if(user!=null){
        Query query=new Query();
        Criteria c1=Criteria.where("_id").is(user.get_id());
        query.addCriteria(c1);

        Update update=new Update();
        update.set("user_id",user.getUser_id());
        update.set("role",user.getRole());
        update.set("name",user.getName());
        update.set("sex",user.getSex());
        update.set("email",user.getEmail());
        update.set("phone",user.getPhone());
        update.set("birth",user.getBirth());
        update.set("major",user.getMajor());
        update.set("academy",user.getAcademy());
        update.set("sign",user.getSign());
        update.set("password",user.getPassword());
        UpdateResult updateResult= mongoTemplate.updateFirst(query, update,"User");
        if(updateResult.wasAcknowledged())
            return 1;
        else
            return 0;
        }
        return 0;
    }

    @Override
    public User findUserBy_id(String _id) {

        return   mongoTemplate.findById(_id,User.class,"User");
    }

    @Override
    public User findUserByUser_id(String user_id) {
        Query query=new Query();
        Criteria c1=Criteria.where("user_id").is(user_id);
        query.addCriteria(c1);
        return mongoTemplate.findOne(query,User.class,"User");
    }

    @Override
    public List<User> findUsersAll() {
        return mongoTemplate.findAll(User.class,"User");
    }

    @Override
    public List<User> findUsersAllPage(int skip, int limit) {
//        Query query =new Query();
//        Pageable pageable=new PageRequest(pageIndex,pageSize);
//        query.with()
//        List<User> users=mongoTemplate.find(query.with(new Sort(Sort.Direction.ASC,"_id")).skip(skip).limit(limit));

        return null;
    }

    @Override
    public List<User> findUsersAllPageBySize(int pageIndex, int pageSize) {
        Query query =new Query();
        Pageable pageable=PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "_id"));
        query.with(pageable);
//        Long count=mongoTemplate.count(query,)
        return mongoTemplate.find(query,User.class,"User");
    }

    @Override
    public List<User> findUsersByMajor(String major) {
        Query query=new Query();
        Criteria c1=Criteria.where("major").is(major);
        query.addCriteria(c1);
        return mongoTemplate.find(query,User.class,"User");
    }


    @Override
    public List<User> findUsersByMajorPage(String major, int pageIndex, int pageSize) {
        Query query =new Query();
        query.addCriteria(Criteria.where("major").is(major));
        Long count=mongoTemplate.count(query,User.class);
        Pageable pageable=PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "_id"));
        query.with(pageable);
        List<User> users=mongoTemplate.find(query,User.class,"User");
//        return PageableExecutionUtils.getPage(users,pageable,"");
        return users;
    }


    @Override
    public List<User> findUsersByAcademy(String academy) {
        Query query=new Query();
        Criteria c1=Criteria.where("academy").is(academy);
        query.addCriteria(c1);
        return mongoTemplate.find(query,User.class,"User");
    }

    @Override
    public List<User> findUsersByAcademyPage(String academy, int pageIndex, int pageSize) {
        Query query =new Query();
        query.addCriteria(Criteria.where("academy").is(academy));
        Long count=mongoTemplate.count(query,User.class);
        Pageable pageable=PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "_id"));
        query.with(pageable);
        List<User> users=mongoTemplate.find(query,User.class,"User");
        return users;
    }

    @Override
    public List<User> findUsersByRolePage(int role, int pageIndex, int pageSize) {
        Query query=new Query();
        query.addCriteria(Criteria.where("role").is(role));
        Pageable pageable=PageRequest.of(pageIndex,pageSize,Sort.by(Sort.Direction.ASC,"_id"));
        query.with(pageable);
        return mongoTemplate.find(query,User.class,"User");
    }

}
