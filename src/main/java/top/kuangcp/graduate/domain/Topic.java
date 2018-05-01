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
    private int publishYear;
    private Long studentId;
    private double guideScore;
    private String guideAdvise;
    private double judgeScore;
    private String judgeAdvise;
    private Long teacherId;
}
