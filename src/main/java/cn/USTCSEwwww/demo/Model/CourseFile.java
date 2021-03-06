package cn.USTCSEwwww.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;

@Document(collection = "Course_file")
public class CourseFile {
    @Id
    private String _id;

    @Field("cf_id")
    private String cf_id;

    @Field("name_id")
    private int name_id;

    @Field("name")
    private String name;

    @Field("upload_user")
    private String upload_user;

    @Field("role")
    private int role;

    @Field("course_id")
    private String course_id;

    @Field("course_name")
    private String course_name;

    @Field("type")
    private String type;

    @Field("upload_time")
    private String upload_time;

    @Field("score")
    private double score;

    @Field("file")
    private byte[] file;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCf_id() {
        return cf_id;
    }

    public void setCf_id(String cf_id) {
        this.cf_id = cf_id;
    }

    public int getName_id() {
        return name_id;
    }

    public void setName_id(int name_id) {
        this.name_id = name_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpload_user() {
        return upload_user;
    }

    public void setUpload_user(String upload_user) {
        this.upload_user = upload_user;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }

    public double getScore() {
        return score;
    }

     public void setScore(double score) {
        this.score = score;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "CourseFile{" +
                "_id='" + _id + '\'' +
                ", cf_id='" + cf_id + '\'' +
                ", name_id=" + name_id +
                ", name='" + name + '\'' +
                ", upload_user='" + upload_user + '\'' +
                ", role=" + role +
                ", course_id='" + course_id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", type='" + type + '\'' +
                ", upload_time='" + upload_time + '\'' +
                ", score=" + score +
                ", file=" + Arrays.toString(file) +
                '}';
    }
}
