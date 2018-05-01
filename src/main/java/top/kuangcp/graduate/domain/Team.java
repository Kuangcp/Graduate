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
 * @date 18-5-1  下午10:15
 */
@Data
@Entity
public class Team implements Serializable {
    @Id
    @GeneratedValue
    private long teamId;
    private String name;
    private String field;//研究领域
    private String year;
    private Long  majorId;
    private Long judgeTeamId;// 评委团队
    //负责人  负责审题
    private Long leaderId;
    // 秘书
    private Long assistantId;
}
