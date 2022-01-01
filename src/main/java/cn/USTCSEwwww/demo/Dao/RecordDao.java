package cn.USTCSEwwww.demo.Dao;

import cn.USTCSEwwww.demo.Model.Record;

import java.util.List;

public interface RecordDao {
    public int insertRecord(Record record);

    public int deleteRecord(Record record);

    public Record findRecordByRecord_id(String Record_id);

    public List<Record> findRecordByCourseId(String course_id);

    public List<Record> findRecordByCourseIdPage(String course_id,int pageIndex,int pageSize);
}
