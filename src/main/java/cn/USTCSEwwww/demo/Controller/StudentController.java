package cn.USTCSEwwww.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Student")
public class StudentController {

    @RequestMapping("/index")
    public String getIndex(){
        return "/Student/index";
    }

}
