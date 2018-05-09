package top.kuangcp.graduate.service.business.leader;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.config.custom.TopicStatus;
import top.kuangcp.graduate.dao.TopicCommentDao;
import top.kuangcp.graduate.dao.TopicDao;
import top.kuangcp.graduate.domain.Topic;
import top.kuangcp.graduate.domain.TopicComment;
import top.kuangcp.graduate.domain.bo.ValidateTopic;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-9  上午7:52
 */
@Log4j2
@Service
public class BusLeaderService {
    private final TopicDao topicDao;
    private final TopicCommentDao commentDao;

    public BusLeaderService(TopicDao topicDao, TopicCommentDao commentDao) {
        this.topicDao = topicDao;
        this.commentDao = commentDao;
    }

    public String submitValidate(Long topicId, Long teacherId, ValidateTopic comment) {
        try {
            Optional<Topic> result = topicDao.findById(topicId);
            if (result.isPresent()) {
                if (teacherId.equals(result.get().getTeacherId())) {
                    if(result.get().getStatus() >= TopicStatus.SUBMIT_PASS_NODE){
                        return JsonBuilder.buildCodeResult(ResponseCode.SUBMIT_REPEAT);
                    }
                    TopicComment comment1 = new TopicComment();

                    comment1.setTopicId(result.get().getTopicId());
                    List<TopicComment> resut = commentDao.findAllByTopicId(result.get().getTopicId());
                    comment1.setComment(comment.getComment());
                    // TODO
                    if(comment.getStatus()==0){
                        result.get().setStatus(TopicStatus.SUBMIT_PASS_NODE);
                    }else{
                        result.get().setStatus(TopicStatus.SUBMIT_REJECT_NODE);
                    }
                    comment1.setIndex(resut.size()+1);
                    comment1.setType(1);
                    commentDao.save(comment1);
                    topicDao.save(result.get());
                    return JsonBuilder.buildSuccessCodeResult();
                }
            }
            return JsonBuilder.buildCodeResult(ResponseCode.SUBMIT_ERROR);
        } catch (Exception e) {
            log.error("课题提交审核失败", e);
            return JsonBuilder.buildCodeResult(ResponseCode.SUBMIT_ERROR);
        }
    }
}
