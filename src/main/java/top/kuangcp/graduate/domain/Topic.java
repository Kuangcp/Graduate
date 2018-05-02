package top.kuangcp.graduate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by https://github.com/kuangcp
 *  课题
 * @author kuangcp
 * @date 18-4-27  下午3:20
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Topic implements Serializable {
    @Id
    @GeneratedValue
    private Long topicId;
    private String name;
    private String attention;
    private int publishYear; // 自动生成
    private Long studentId; // 缺省为null
    private Long teacherId;

    private double guideScore;
    private String guideAdvise;
    private double judgeScore;
    private String judgeAdvise;

    private Integer status; // 0 1 2 缺省 通过 已提交审核

}
