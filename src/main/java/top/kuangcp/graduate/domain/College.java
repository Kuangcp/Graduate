package top.kuangcp.graduate.domain;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import com.kuangcp.mythpoi.utils.base.ExcelConfig;
import com.kuangcp.mythpoi.utils.base.ExcelSheet;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by https://github.com/kuangcp on 17-11-26  下午9:33
 * 学院 实体
 * TODO 拿到对应的Excel文件 做好建制的初始化 由自己来定 学生就基本信息 教师也是 其他的角色全都是教师
 * @author kuangcp
 */
@Data
@Entity
@ExcelSheet(importTitle = "学院表", exportTitle = "学院表")
public class College implements Serializable, ExcelTransform{
    @Id
    @GeneratedValue
    private long collegeId;
    @ExcelConfig("学院名")
    private String name;
}
