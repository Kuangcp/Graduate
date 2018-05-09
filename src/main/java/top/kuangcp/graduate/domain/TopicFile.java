package top.kuangcp.graduate.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-9  下午12:34
 */
@Data
@Entity
public class TopicFile {
    @Id
    @GeneratedValue
    private Long fileId;
    private String path;
    private Long topicId;
    private Integer batch;
}
