package top.kuangcp.graduate.domain.role;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import com.kuangcp.mythpoi.utils.base.ExcelConfig;
import com.kuangcp.mythpoi.utils.base.ExcelSheet;
import lombok.Data;

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
@Data
@Entity
@ExcelSheet(importTitle = "学生表", exportTitle = "学生表")
public class Student implements Serializable, ExcelTransform {
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
    private Long teacherId;
}
