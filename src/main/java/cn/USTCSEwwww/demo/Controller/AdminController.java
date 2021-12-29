package cn.USTCSEwwww.demo.Controller;

import cn.USTCSEwwww.demo.Model.User;
import cn.USTCSEwwww.demo.Service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private UserService userService;


    @PostMapping("/insertOne")
    @ApiOperation(value = "Admin/insertOne")
    @ResponseBody
    public String insertOne(User user){

       if(userService.insertUser(user)==0)
           return "/Admin/index";
       else
           return "/Admin/index";
    }


    @PostMapping("/findUsersByRolePage")
    @ResponseBody
    @ApiOperation(value = "Admin/findUsersByRolePage")
    public List<User> findUsersByRolePage(int role,int pageIndex,int pageSize){
        List<User> users= userService.getUsersByRole(role,pageIndex,pageSize);
        return users;
    }


}
