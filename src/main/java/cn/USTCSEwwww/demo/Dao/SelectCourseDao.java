package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.SelectCourse;

import java.util.List;

public interface SelectCourseDao {
    public int insertSelectCourse(SelectCourse selectCourse);

    public int deleteSelectCourse(SelectCourse selectCourse);

    public int deleteCourseIdInUserPublicCourse(String user_id, String course_id);

    public int updateSelectCourse(SelectCourse selectCourse);

    public List<SelectCourse> findSelectCourseByUser_id(String user_id);

    public List<String> findCoursesByUser_idAndPermissionInPage(String user_id,int permission,int pageIndex,int pageSize);

}
