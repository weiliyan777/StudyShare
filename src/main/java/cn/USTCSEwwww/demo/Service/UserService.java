package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Model.User;

import java.util.List;

public interface UserService {
    public int insertUser(User user);

    public int insertUser(List<User> users);

    public int deleteUser(User user);

    public int deleteUser(List<User> users);

    public List<User> getUsersByRole(int role,int pageIndex,int pageSize);

//    public boolean checkPassword(User user);

    public int checkRoleAndPassword(User user);

    public int updateUser(User user);

    public int updatePassword(User user,String password);

    /**
     * 根据User.role按页查找出所有的用户
     * @param role
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<User> getUsersAllByRole(int role,int pageIndex,int pageSize);
}
