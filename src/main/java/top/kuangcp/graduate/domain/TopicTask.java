package top.kuangcp.graduate.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-1  下午10:32
 */
@Data
@Entity
public class TopicTask {

    @Id
    @GeneratedValue
    private Long taskId;
    private Long teacherId;
    private Long leaderId;
    private Integer num;
}
