package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.Live;

import java.util.List;

public interface LiveDao {
    public int insertLive(Live live);

    public int deleteLive(Live live);

    public List<Live> findLiveByCourse_id(String course_id);

    public List<Live> findLiveByCourse_id(String course_id,int pageIndex,int pageSize);

    public List<Live> findLiveByCourse_idAndStatus(String course_id,int status);

    public List<Live> findLiveByCourse_idAndStatusPage(String course_id,int status,int pageIndex,int pageSize);

    public List<Live> findLiveByAddress(String address);
}
