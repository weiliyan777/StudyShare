package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.SelectCourse;

import java.util.List;

public interface SelectCourseDao {
    public int insertSelectCourse(SelectCourse selectCourse);

    public int deleteSelectCourse(SelectCourse selectCourse);

    public List<SelectCourse> findSelectCourseByUser_id(String user_id);

    public List<SelectCourse> findSelectCourseByUser_idPage(String user_id,int pageIndex,int pageSize);
}
