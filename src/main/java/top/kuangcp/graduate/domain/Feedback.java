package top.kuangcp.graduate.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  上午7:50
 */
@Data
@Entity
public class Feedback {
    @Id
    @GeneratedValue
    private Long feedbackId;
    private Long studentId;
    private String content;
    private String contact;// 联系方式
}
