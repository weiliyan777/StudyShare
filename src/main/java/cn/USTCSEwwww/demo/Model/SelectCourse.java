package cn.USTCSEwwww.demo.Model;

import com.mongodb.internal.connection.Time;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "Select_Course")
public class SelectCourse {
    @Id
    private String _id;

    @Field("user_id")
    private String user_id;

    @Field("Private_Courses")
    private List<String> Private_Courses;

    @Field("Public_Courses")
    private List<String> Public_Courses;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<String> getPrivate_Courses() {
        return Private_Courses;
    }

    public void setPrivate_Courses(List<String> private_Courses) {
        Private_Courses = private_Courses;
    }

    public List<String> getPublic_Courses() {
        return Public_Courses;
    }

    public void setPublic_Courses(List<String> public_Courses) {
        Public_Courses = public_Courses;
    }
}
