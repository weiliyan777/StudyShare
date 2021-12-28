package cn.USTCSEwwww.demo.Dao;
import cn.USTCSEwwww.demo.Model.User;

import java.util.List;

public interface UserDao {
    public int insertUser(User user);

    public int deleteUser(User user);

    public int updateUser(User user);

    public User findUserBy_id(String _id);

    public User findUserByUser_id(String user_id);

    public List<User> findUsersAll();

    public List<User> findUsersAllPage(int skip,int limit);

    public List<User> findUsersAllPageBySize(int pageIndex,int pageSize);

    public List<User> findUsersByMajor(String major);

    public List<User> findUsersByMajorPage(String major,int pageIndex,int pageSize);

    public List<User> findUsersByAcademy(String academy);

    public List<User> findUsersByAcademyPage(String academy,int pageIndex,int pageSize);

    public List<User> findUsersByRolePage(int role,int pageIndex,int pageSize);

}
