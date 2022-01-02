package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Dao.LiveDao;
import cn.USTCSEwwww.demo.Model.Live;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("LiveService")
@Transactional(rollbackFor = Exception.class)
public class LiveServiceImpl implements LiveService {
    @Autowired
    private LiveDao liveDao;

    @Override
    public int insertLive(Live live) {
        try {
            List<Live> existedLives = liveDao.findLiveByCourse_id(live.getCourse_id());
            if (existedLives.size() > 0) {
                for (int i = 0; i < existedLives.size(); i++) {
                    liveDao.deleteLive(existedLives.get(i));
                }
            }
            List<Live> existsAddressLive = liveDao.findLiveByAddress(live.getAddress());
            if (existsAddressLive.size() >= 1) {
                throw new Exception(live.getAddress() + "Address has used");
            }
            return liveDao.insertLive(live);
        }catch (Exception e){
            System.out.println(e.toString());
            return 0;
        }
    }

    @Override
    public int deleteLive(Live live) {
        try {
            List<Live> getlives = liveDao.findLiveByAddress(live.getAddress());
            if(getlives.size()==0||getlives.size()>1) {
                throw new Exception(live.getAddress()+"查询得一个或者多个记录");
            }
            live.set_id(getlives.get(0).get_id());
            return liveDao.deleteLive(live);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Live> findLivesByCourse_id(String course_id) {
        return liveDao.findLiveByCourse_id(course_id);
    }
}
