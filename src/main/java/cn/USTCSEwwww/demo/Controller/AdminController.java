package cn.USTCSEwwww.demo.Controller;

import cn.USTCSEwwww.demo.Model.User;
import cn.USTCSEwwww.demo.Service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/getUsersByRole")
    @ApiOperation(value = "Admin/getUsersByRole")
    public List<User> getUsersAllByRole(int role,int pageIndex,int pageSize){
        return userService.getUsersByRole(role,pageIndex,pageSize);
    }

    @PostMapping("/updateUser")
    @ApiOperation(value = "Admin/updateUser")
    public int updateUser(User user){
        return userService.updateUser(user);
    }

    @PostMapping("/insertOne")
    @ApiOperation(value = "Admin/insertOne")
    public int insertOne(User user){
        return userService.insertUser(user);
    }

    @PostMapping("/insertUsers")
    @ApiOperation(value = "Admin/insertUsers")
    public int insertUsers(List<User> users){
        return userService.insertUser(users);
    }

    @PostMapping("/deleteUser")
    @ApiOperation(value = "deleteUser")
    public int deleteUser(User user){
        return userService.deleteUser(user);
    }

    @PostMapping("/deleteUsers")
    @ApiOperation(value = "deleteUsers")
    public int deleteUsers(List<User> users){
        return userService.deleteUser(users);
    }

    @PostMapping("/getUserByUser_id")
    @ApiOperation(value = "getUserByUser_id")
    public User getUserByUser_id(String user_id)
    {
        return userService.getUserByUser_id(user_id);
    }


}
