package cn.USTCSEwwww.demo.Controller;

import cn.USTCSEwwww.demo.Model.Live;
import cn.USTCSEwwww.demo.Service.LiveService;
import cn.USTCSEwwww.demo.vm.id;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "findOneLiveByCourse_id",method = RequestMethod.POST)
    @ApiOperation(value = "findLivesByCourse_id")
    public Live findOneLiveByCourse_id(@RequestBody id item){
        String course_id=item.getId();
        List<Live> lives= liveService.findLivesByCourse_id(course_id);
        if(lives.size()>0){
            return lives.get(0);
        }
        else return null;
    }

}
