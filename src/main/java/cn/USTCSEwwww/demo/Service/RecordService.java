package cn.USTCSEwwww.demo.Service;

import cn.USTCSEwwww.demo.Model.Record;

import java.util.List;

public interface RecordService {
    public int insertRecord(Record record);
    public int deleteRecord(Record record);
    public List<Record> findRecordsByCourse_id(String course_id);
    public List<Record> findRecordsByCourse_idPage(String course_id,int pageIndex,int pageSize);
}
