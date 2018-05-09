package top.kuangcp.graduate.domain.vo;

import lombok.Data;
import top.kuangcp.graduate.domain.TopicTask;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-8  下午7:41
 */
@Data
public class TopicTaskVO {
    private Long taskId;
    private String teacher;
    private String major;
//    private String field;
    private Long leaderId;
    private Integer num;
    private Integer year;

    public static TopicTaskVO of(TopicTask topicTask){
        TopicTaskVO result = new TopicTaskVO();
        result.setTaskId(topicTask.getTaskId());
        result.setLeaderId(topicTask.getLeaderId());
        result.setNum(topicTask.getNum());
        result.setYear(topicTask.getYear());
        return result;
    }
}
