package cn.USTCSEwwww.demo.Controller;

import cn.USTCSEwwww.demo.Model.Record;
import cn.USTCSEwwww.demo.Service.RecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Repository("/Record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PostMapping("/insertRecord")
    @ApiOperation("/Record/insertRecord")
    public int insertRecord(Record record){
        return recordService.insertRecord(record);
    }

    @PostMapping("/deleteRecord")
    @ApiOperation("/Record/deleteRecord")
    public int deleteRecord(Record record){
        return recordService.deleteRecord(record);
    }

    @PostMapping("/getRecordsByCourse_id")
    @ApiOperation("/Record/getRecordsByCourse_id")
    public List<Record> getRecordsByCourse_id(String course_Id,int pageIndex,int pageSize){
        return recordService.findRecordsByCourse_idPage(course_Id,pageIndex,pageSize);
    }


    @PostMapping("/getRecordByRecord_id")
    @ApiOperation("/Record/getRecordByRecord_id")
    public Record getRecordByRecord_id(String record_id){
        return recordService.findRecordByRecord_id(record_id);
    }

}
