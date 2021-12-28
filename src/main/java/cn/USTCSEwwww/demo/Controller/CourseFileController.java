package cn.USTCSEwwww.demo.Controller;


import cn.USTCSEwwww.demo.Model.CourseFile;
import cn.USTCSEwwww.demo.Service.CourseFileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/CourseFile")
@RestController
public class CourseFileController {

    @Autowired
    private CourseFileService courseFileService;

    @RequestMapping(value = "/insertOne",method = RequestMethod.POST)
    @ApiOperation(value = "/insertOne")
    public String insertOne(CourseFile courseFile){
        if(courseFileService.insertCourseFile(courseFile)==0)
            return "error";
        else
            return "success";
    }

    @RequestMapping(value = "/deleteOne",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "/deleteOne")
    @ResponseBody
    public String deleteOne( String _id){
        if(courseFileService.deleteCourseFile(_id)==0)
            return "error";
        else
            return "success";
    }

    @RequestMapping(value = "/updateOne",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "updateOne")
    @ResponseBody
    public String updateOne(CourseFile courseFile){
        if(courseFileService.updateCourseFile(courseFile)==0)
            return "error";
        else
            return "success";
    }

    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ApiOperation(value = "findOne")
    public CourseFile findOne(String _id){
        return courseFileService.findCourseFileBy_id(_id);
    }

    @RequestMapping(value = "/findMany",method = RequestMethod.GET)
    @ApiOperation(value = "findMany")
    @ResponseBody
    public List<CourseFile> findCourseFilesByCourse_id(String course_id) {
        return courseFileService.findCourseFileByCourse_id(course_id);
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ApiOperation(value = "findAll")
    public List<CourseFile> findAllCourseFiles() {
        return courseFileService.findAllCourseFile();
    }

    @RequestMapping(value = "/findCourseFileByCourse_id_Page",method = RequestMethod.GET)
    @ApiOperation(value = "findCourseFileByCourse_id_Page")
    public List<CourseFile> findCourseFileByCourse_id_Page(String course_id, int pageIndex, int pageSize) {
        return courseFileService.findCourseFileByCourse_id_Page(course_id,pageIndex,pageSize);
    }

    @RequestMapping(value = "/findCourseFileByUpload_user",method = RequestMethod.GET)
    @ApiOperation(value = "findCourseFileByUpload_user")
    public List<CourseFile> findCourseFilesByUpload_user(String upload_user) {
        return courseFileService.findCourseFileByUpload_user(upload_user);
    }

    @RequestMapping(value = "/findCourseFileByUpload_user_inPage",method = RequestMethod.GET)
    @ApiOperation(value = "findCourseFileByUpload_user_inPage")
    public List<CourseFile> findCourseFileByUpload_user(String upload_user, int pageIndex, int pageSize) {
        return courseFileService.findCourseFileByUpload_user(upload_user,pageIndex,pageSize);
    }

}
