package cn.USTCSEwwww.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Course")
public class Course {
    @Id
    private String _id;

    @Field("course_id")
    private String course_id;

    @Field("name")
    private String name;

    @Field("teacherId")
    private String teacherId;

    @Field("permission")
    private int permission;

    @Field("startDate")
    private String startDate;

    @Field("permission_students")
    private List<String> permission_students;

    @Field("files")
    private List<String> files;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<String> getPermission_students() {
        return permission_students;
    }

    public void setPermission_students(List<String> permission_students) {
        this.permission_students = permission_students;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }


    @Override
    public String toString() {
        return "Course{" +
                "_id='" + _id + '\'' +
                ", course_id='" + course_id + '\'' +
                ", name='" + name + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", permission=" + permission +
                ", startDate='" + startDate + '\'' +
                ", permission_students=" + permission_students +
                ", files=" + files +
                '}';
    }

}
