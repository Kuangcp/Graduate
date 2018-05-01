package top.kuangcp.graduate.domain;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import com.kuangcp.mythpoi.utils.base.ExcelConfig;
import com.kuangcp.mythpoi.utils.base.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by https://github.com/kuangcp on 17-11-26  下午9:27
 * 专业
 * @author kuangcp
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(importTitle = "专业表", exportTitle = "专业表")
public class Major implements Serializable, ExcelTransform{
    @Id
    @GeneratedValue
    private long majorId;
    @ExcelConfig("专业名")
    private String name;

    private Long collegeId;
}
