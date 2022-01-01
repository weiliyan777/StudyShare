package cn.USTCSEwwww.demo.Controller;

import cn.USTCSEwwww.demo.Model.Live;
import cn.USTCSEwwww.demo.Service.LiveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/Live")
@RestController
public class LiveController {

    @Autowired
    LiveService liveService;

    @RequestMapping(value = "insertOne",method = RequestMethod.POST)
    @ApiOperation(value = "insertOne")
    public int insertLive(Live live){
        return liveService.insertLive(live);
    }

    @RequestMapping(value = "deleteOne",method = RequestMethod.POST)
    @ApiOperation(value = "deleteOne")
    public int deleteLive(Live live){
        return liveService.deleteLive(live);
    }

    @RequestMapping(value = "findLivesByCourse_id",method = RequestMethod.GET)
    @ApiOperation(value = "findLivesByCourse_id")
    public List<Live> findLivesByCourse_id(String course_id){
        return liveService.findLivesByCourse_id(course_id);
    }



}
