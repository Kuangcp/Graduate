package top.kuangcp.graduate.domain.doc;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import com.kuangcp.mythpoi.utils.base.ExcelConfig;
import com.kuangcp.mythpoi.utils.base.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by https://github.com/kuangcp on 17-11-26  下午8:57
 * 班级 实体
 * @author kuangcp
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(importTitle = "班级表", exportTitle = "班级表")
public class ClassGroup implements Serializable, ExcelTransform{
    @Id
    @GeneratedValue
    private Long groupId;
    @ExcelConfig("名称")
    private String name;
}
