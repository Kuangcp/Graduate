package top.kuangcp.graduate.domain.role;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import com.kuangcp.mythpoi.utils.base.ExcelConfig;
import com.kuangcp.mythpoi.utils.base.ExcelSheet;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午5:18
 */
@Data
@Entity
@ExcelSheet(importTitle = "教师表", exportTitle = "教师表")
public class Teacher implements Serializable, ExcelTransform {
    @Id
    @GeneratedValue

    private long teacherId;
    @ExcelConfig("姓名")
    private String username;
    @ExcelConfig("教师工号")
    private String teacherNo;
    @ExcelConfig("密码")
    private String password;
    @ExcelConfig("邮箱")
    private String email;
//    @ExcelConfig("微信id")
//    private String weixinId;
    private Long teamId;


    public Teacher(){}
    public Teacher(long teacherId){
        this.teacherId = teacherId;
    }
}

