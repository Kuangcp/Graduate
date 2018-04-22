package top.kuangcp.graduate.domain.role;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import com.kuangcp.mythpoi.utils.base.ExcelConfig;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-3-28  下午3:32
 */
@Entity
public class Student implements Serializable, ExcelTransform, Role {
    @Id
    @GeneratedValue
    private Long studentId;
    @ExcelConfig("姓名")
    private String username;
    @ExcelConfig("学号")
    private String studentNo;
    @ExcelConfig("密码")
    private String password;
    @ExcelConfig("邮箱")
    private String email;
    @ExcelConfig("届数")
    private Integer year;
    private String openId;


    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", username='" + username + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", year=" + year +
                ", openId='" + openId + '\'' +
                '}';
    }
}
