package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Dao.UserDao;
import cn.USTCSEwwww.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public int insertUser(User user) {
        return  userDao.insertUser(user);
    }

    @Override
    public int insertUser(List<User> users) {
        return 0;
    }

    @Override

    public int deleteUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(List<User> users) {
        return 0;
    }

    @Override
    public List<User> getUsersByRole(int role, int pageIndex, int pageSize) {
        if(pageIndex>=0)
            return userDao.findUsersByRolePage(role,pageIndex,pageSize);
        else
            return null;
    }

    @Override

    public boolean checkPassword(User user) {
        return false;
    }

    @Override
    public int checkRoleAndPassword(User user) {

        User realUser=userDao.findUserByUser_id(user.getUser_id());

        if(!realUser.getPassword().equals(user.getPassword()))
            return -1;//-1是账号或者密码错误
        return realUser.getRole();
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int updatePassword(User user, String password) {
        return 0;
    }

    @Override
    public List<User> getUsersAllByRole(int role, int pageIndex, int pageSize) {
        return null;
    }
}
