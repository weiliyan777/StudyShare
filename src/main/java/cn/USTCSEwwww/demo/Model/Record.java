package cn.USTCSEwwww.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Record")
public class Record {
    @Id
    private String _id;

//    @Field("record_id")
//    private String record_id;

    @Field("course_id")
    private String course_id;

    @Field("describe")
    private String describe;

    @Field("file")
    private byte[] file;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

//    public String getRecord_id() {
//        return record_id;
//    }
//
//    public void setRecord_id(String record_id) {
//        this.record_id = record_id;
//    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
