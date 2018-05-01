package top.kuangcp.graduate.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-1  下午9:52
 */
@Data
@Entity
public class DefenseSchedule implements Serializable {
    @Id
    @GeneratedValue
    private long scheduleId;
    private String name;
    private String batch;// 一二三辩 批次
    private Date date;//答辩时间
    private String place;//地点
    private String attention;//注意事项
    private int status;//是否结束 0 1
    private Long teamId; // 团队
}
