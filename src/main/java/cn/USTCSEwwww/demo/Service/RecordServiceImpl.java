package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Dao.RecordDao;
import cn.USTCSEwwww.demo.Model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("RecordService")
@Transactional(rollbackFor = Exception.class)
public class RecordServiceImpl implements RecordService{
    @Autowired
    private RecordDao recordDao;

    @Override
    public int insertRecord(Record record) {
        return recordDao.insertRecord(record);
    }

    @Override
    public int deleteRecord(Record record) {
        return recordDao.deleteRecord(record);
    }

    @Override
    public List<Record> findRecordsByCourse_id(String course_id) {
        return recordDao.findRecordByCourseId(course_id);
    }

    @Override
    public List<Record> findRecordsByCourse_idPage(String course_id, int pageIndex, int pageSize) {
        return recordDao.findRecordByCourseIdPage(course_id,pageIndex,pageSize);
    }
}
