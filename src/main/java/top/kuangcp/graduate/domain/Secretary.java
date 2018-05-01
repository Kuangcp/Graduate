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
public class Secretary implements Serializable {

    @Id
    @GeneratedValue
    private Long secretaryId;
    private Long teacherId;
    private Long teamId;
    private Integer year;
}
