package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Dao.RecordDao;
import cn.USTCSEwwww.demo.Model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("RecordService")
//@Transactional(rollbackFor = Exception.class)
public class RecordServiceImpl implements RecordService{
    @Autowired
    private RecordDao recordDao;

    @Override
    public int insertRecord(Record record) {
        return recordDao.insertRecord(record);
    }

    @Override
    public Record findRecordByRecord_id(String record_id) {
        return  recordDao.findRecordByRecord_id(record_id);
    }

    @Override
    public int deleteRecord(Record record) {
        try {
            Record getRecord=recordDao.findRecordByRecord_id(record.getRecord_id());
            if(getRecord==null)
                throw new Exception(record.getRecord_id()+"录播文件没有找到");
            return recordDao.deleteRecord(getRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
