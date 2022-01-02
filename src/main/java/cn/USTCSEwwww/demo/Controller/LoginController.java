package cn.USTCSEwwww.demo.Controller;

import cn.USTCSEwwww.demo.Model.User;
import cn.USTCSEwwww.demo.Service.UserService;
import cn.USTCSEwwww.demo.vm.LoginReq;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

@RestController
@RequestMapping("/Login")
public class LoginController {
    @Autowired
    private UserService userService;

//    @RequestMapping("/index")
//    @ApiOperation(value = "index")
//    public String getIndex(){
//        return "/Login/index";
//    }

//    @RequestMapping("/indexTest")
//    public String getIndexTest(){
//        return "/Course-System-front-master/login";
//    }

//    @RequestMapping("/checkLogin")
//    @ApiOperation(value = "checkLogin")
//    public String checkLogin(String user_id,String password,int role){
//        User user=new User();
//        user.setUser_id(user_id);
//        user.setPassword(password);
//        user.setRole(role);
//        int res= userService.checkRoleAndPassword(user);
//        if(res==0){
//            return "/Admin/index";
//        }else  if(res==1){
//            return "/Teacher/index";
//        }
//        else if(res==2){
//            return "/Student/index";
//        }
//
//        return "/Login/index/error";
//    }

    @RequestMapping("/checkLogin")
    @ApiOperation(value = "checkLogin")
    public User checkLogin(@RequestBody LoginReq loginReq){
        User user=new User();
        user.setUser_id(loginReq.getUsername());
        user.setPassword(loginReq.getPassword());
        user.setRole(1);
        int res= userService.checkRoleAndPassword(user);
        return user;
    }

//    @RequestMapping("/checkLoginTest")
//    @ResponseBody
//    @ApiOperation(value = "checkLoginTest")
//    public String checkLoginTest(String user_id, String password, String ident){
//        User user=new User();
//        user.setUser_id(user_id);
//        user.setPassword(password);
//        user.setRole(Integer.parseInt(ident));
//        int res=userService.checkRoleAndPassword(user);
//        if(res==1)
//            return "success";
//        else
//            return "error";
//    }

}
