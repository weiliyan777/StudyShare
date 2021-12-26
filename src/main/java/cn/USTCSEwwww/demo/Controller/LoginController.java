package cn.USTCSEwwww.demo.Controller;

import cn.USTCSEwwww.demo.Model.User;
import cn.USTCSEwwww.demo.Service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Login")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    @ApiOperation(value = "index")
    public String getIndex(){
        return "/Login/index";
    }

    @RequestMapping("/checkLogin")
    @ApiOperation(value = "checkLogin")
    public String checkLogin(String user_id,String password){
        User user=new User();
        user.setUser_id(user_id);
        user.setPassword(password);
        int res= userService.checkRoleAndPassword(user);
        if(res==0){
            return "/Admin/index";
        }else  if(res==1){
            return "/Teacher/index";
        }
        else if(res==2){
            return "/Student/index";
        }
        return "/Login/index";
    }
}
