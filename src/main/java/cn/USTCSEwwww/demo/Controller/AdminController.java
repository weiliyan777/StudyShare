package cn.USTCSEwwww.demo.Controller;

import cn.USTCSEwwww.demo.Dao.UserDao;
import cn.USTCSEwwww.demo.Model.User;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private UserDao userDao;

    @PostMapping("/insertOne")
    @ApiOperation(value = "Admin/insertOne")
    public String insertOne(User user){
       if(userDao.insertUser(user)==0)
           return "error";
       else
           return "success";
    }
}
