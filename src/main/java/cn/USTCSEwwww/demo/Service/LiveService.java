package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Model.Live;

import java.util.List;

public interface LiveService {
    /**
     * 已有使用address的Live的时候，删除原来使用address的Live
     * @param live
     * @return
     */
    public int insertLive(Live live);

    /**
     * 根据Live的address删除Live
     * @param live
     * @return
     */
    public int deleteLive(Live live);

    public List<Live> findLivesByCourse_id(String course_id);
}
