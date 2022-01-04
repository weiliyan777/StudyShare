package cn.USTCSEwwww.demo.Controller;

import cn.USTCSEwwww.demo.Model.Course;
import cn.USTCSEwwww.demo.Service.CourseService;
import cn.USTCSEwwww.demo.vm.PageEntity;
import cn.USTCSEwwww.demo.vm.id;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/insertOne",method = RequestMethod.POST)
    @ApiOperation(value = "/insertOne")
    public int insertCourse(Course course){
        return courseService.insertCourse(course);
    }

    @RequestMapping(value = "deleteOne",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "deleteOne")
    public  int deleteCourse(Course course){
        return courseService.deleteCourse(course);
    }

    @RequestMapping(value = "updateOne",method = {RequestMethod.POST})
    @ApiOperation(value = "updateOne")
    public int updateCourse(Course course){
        return courseService.updateCourse(course);
    }

    @RequestMapping(value = "findCoursesByTeacherId",method = RequestMethod.POST)
    @ApiOperation(value = "findCoursesByTeacherId")
    public List<Course> findCoursesByTeacherId(String teacherId,int pageIndex,int pageSize){
        return courseService.findCoursesByTeacherId(teacherId,pageIndex,pageSize);
    }

//    @RequestMapping(value = "findCoursesByCourse_Id",method = RequestMethod.POST)
//    @ApiOperation(value = "findCoursesByCourse_Id")
//    public Course findCoursesByCourse_Id(@RequestBody String course_id){
//        return courseService.findCoursesByCourse_Id(course_id);
//    }

    @RequestMapping(value = "findCoursesByCourse_Id",method = RequestMethod.POST)
    @ApiOperation(value = "findCoursesByCourse_Id")
    public Course findCoursesByCourse_Id(@RequestBody id item){

        Course res= courseService.findCoursesByCourse_Id(item.getId());
        return res;
    }

    @RequestMapping(value = "findAllCourses",method = RequestMethod.POST)
    @ApiOperation(value = "findAllCourses")
    public List<Course> findCoursesAll(int pageIndex,int pageSize){
        return courseService.findCoursesAll(pageIndex,pageSize);
    }

//    @RequestMapping(value = "findPublicCourses",method = RequestMethod.GET)
//    @ApiOperation(value = "findPublicCourses")
//    public List<Course> findCoursesPublic(@RequestBody int pageIndex, int pageSize){
//        return courseService.findCoursesPublic(pageIndex,pageSize);
//    }


    @RequestMapping(value = "findPublicCourses",method = RequestMethod.POST)
    @ApiOperation(value = "findPublicCourses")
    public List<Course> findCoursesPublic(@RequestBody PageEntity pageEntity){
            return courseService.findCoursesPublic(pageEntity.getPageIndex(),pageEntity.getPageSize());
    }

    @RequestMapping(value = "selectPublicCourse",method = RequestMethod.POST)
    @ApiOperation(value = "selectPublicCourse")
    public int SelectPublicCourse(String user_id,String course_id){
        return courseService.SelectPublicCourse(user_id,course_id);
    }

    @RequestMapping(value = "userDeletePublicCourse",method = RequestMethod.POST)
    @ApiOperation(value = "userDeletePublicCourse")
    public int deleteSelectedPublicCourse(String user_id,String course_id){
        return courseService.deleteSelectedPublicCourse(user_id,course_id);
    }

    @RequestMapping(value = "findSelectCoursesByUser_idAndPermission",method = RequestMethod.POST)
    public List<Course> findSelectCoursesByUser_idAndPermission(String user_id,int permission,int pageIndex,
                                                                int pageSize){
        return courseService.findSelectCoursesByUser_idAndPermission(user_id,permission,pageIndex,pageSize);
    }

    @RequestMapping(value = "/findSelectedPrivateCourse",method = RequestMethod.POST)
    @ApiOperation(value = "findSelectedPrivateCourse")
    public List<Course> findSelectedPrivateCourse(@RequestBody id item){
        String user_id=item.getId();
        List<Course> res= courseService.findSelectCoursesByUser_idAndPermission(user_id,1,0,10);
        return  res;
    }
//@RequestMapping(value = "/findSelectedPrivateCourse",method = RequestMethod.POST)
//@ApiOperation(value = "findSelectedPrivateCourse")
//public List<Course> findSelectedPrivateCourse(@RequestBody String user_id){
////    String user_id=item.getId();
//    List<Course> res= courseService.findSelectCoursesByUser_idAndPermission(user_id,1,0,10);
//    return  res;
//}
}
