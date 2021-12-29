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
            if (existedLives.size() >= 1) {
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
        return liveDao.deleteLive(live);
    }

}
