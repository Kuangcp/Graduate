package top.kuangcp.graduate.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-1  下午10:33
 */
@Data
@Entity
public class Leader implements Serializable {

    @Id
    @GeneratedValue
    private Long leaderId;
    private Long teacherId;
    private Long majorId;
    private Integer year;
}
