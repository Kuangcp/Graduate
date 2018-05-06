package top.kuangcp.graduate.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-6  下午2:03
 */
@Data
@Entity
public class TopicComment {
    @Id
    @GeneratedValue
    private Long topicCommentId;
    private Long topicId;
    private String comment;
    private Long index;
}
