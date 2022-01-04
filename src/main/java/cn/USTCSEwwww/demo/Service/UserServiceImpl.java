package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Dao.CourseDao;
import cn.USTCSEwwww.demo.Dao.SelectCourseDao;
import cn.USTCSEwwww.demo.Dao.UserDao;
import cn.USTCSEwwww.demo.Model.Course;
import cn.USTCSEwwww.demo.Model.SelectCourse;
import cn.USTCSEwwww.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Repository("UserService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private SelectCourseDao selectCourseDao;

    @Autowired
    private CourseDao courseDao;
    @Override
    public int insertUser(User user) {
        try {
            if(userDao.insertUser(user)!=1)
                throw new Exception(user.getUser_id()+"insert error");
            SelectCourse selectCourse=new SelectCourse();
            selectCourse.setUser_id(user.getUser_id());
            List<String> empty=new LinkedList<>();
            selectCourse.setPublic_Courses(empty);
            selectCourse.setPrivate_Courses(empty);
            if(selectCourseDao.insertSelectCourse(selectCourse)!=1)
                throw new Exception(user.getUser_id()+"s select_course insert error");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
            return 0;
        }
        return 1;
    }

    @Override
    public int insertUser(List<User> users) {
//        try {
//            for (int i = 0; i < users.size(); i++) {
//                if (userDao.insertUser(users.get(i)) != 1) {
//                    throw new Exception(users.get(i).getUser_id()+"inserted error");
//                }
//            }
//            for(int i=0;i<users.size();i++){
//                SelectCourse selectCourse=new SelectCourse();
//                selectCourse.setUser_id(users.get(i).getUser_id());
//                List<String> private_Courses=new LinkedList<>();
//                List<String> public_Courses=new LinkedList<>();
//                selectCourse.setPrivate_Courses(private_Courses);
//                selectCourse.setPublic_Courses(public_Courses);
//                if(selectCourseDao.insertSelectCourse(selectCourse)!=1) {
//                    throw  new Exception(users.get(i).getUser_id()+"'s select_course insert error");
//                }
//                }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return  0;
//        }
//        return  users.size();
        int res=0;
        for(int i=0;i<users.size();i++){
          res+= userDao.insertUser(users.get(i));
        }
        return  res;
    }

    @Override
    public int deleteUser(User user) {
        try {
            User getUser = userDao.findUserByUser_id(user.getUser_id());
            if(getUser==null)
                throw new Exception(user.getUser_id()+"没有找到");
            SelectCourse selectCourse= new SelectCourse();
            List<SelectCourse> getSelectCourse = selectCourseDao.findSelectCourseByUser_id(user.getUser_id());
            if(getSelectCourse.size()!=1)
                throw new Exception("selectCourse get error");
            selectCourse= getSelectCourse.get(0);
//            List<String> publicCourses=selectCourse.getPublic_Courses();
            List<String> privateCourses=selectCourse.getPrivate_Courses();
            for(int i=0;i<privateCourses.size();i++)
            {
                Course course= courseDao.findCourseByCourse_id(privateCourses.get(i));
                List<String> permissions=course.getPermission_students();
                Iterator<String> iter=permissions.iterator();
                while(iter.hasNext()){
                    if(iter.next().equals(user.getUser_id()))
                        iter.remove();
                }
                course.setPermission_students(permissions);
                courseDao.updateCourse(course);
            }
            if(selectCourseDao.deleteSelectCourse(selectCourse)!=1)
                throw new Exception(user.getUser_id()+"'s selectCourses delete error");
            if(userDao.deleteUser(getUser)!=1)
                throw new Exception(user.getUser_id()+"delete error");
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return -1;
        }
    }

    @Override
    public int deleteUser(List<User> users) {
        int res=0;
        for(int i=0;i<users.size();i++){
            res+=deleteUser(users.get(i));
        }
        return res;
    }

    @Override
    public List<User> getUsersByRole(int role, int pageIndex, int pageSize) {
        if(pageIndex>=0)
            return userDao.findUsersByRolePage(role,pageIndex,pageSize);
        else
            return null;
    }

//    @Override
//
//    public boolean checkPassword(User user) {
//        return false;
//    }

    @Override
    public int checkRoleAndPassword(User user) {
        User realUser=userDao.findUserByUser_id(user.getUser_id());
        if(realUser==null)
            return -1;
        if(!realUser.getPassword().equals(user.getPassword()))
            return -1;//-1是账号或者密码错误
        if(realUser.getRole()==user.getRole())
            return realUser.getRole();
        else
            return -1;
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int updatePassword(User user, String password) {
        User olduser=userDao.findUserByUser_id(user.getUser_id());
        olduser.setPassword(password);
        return updateUser(olduser);
    }

    @Override
    public List<User> getUsersAllByRole(int role, int pageIndex, int pageSize) {
//        return null;
        return userDao.findUsersByRolePage(role,pageIndex,pageSize);
    }

    @Override
    public User getUserByUser_id(String user_id) {
        return userDao.findUserByUser_id(user_id);
    }
}
