package top.kuangcp.graduate.service.business.teacher;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.config.custom.TopicStatus;
import top.kuangcp.graduate.dao.TopicDao;
import top.kuangcp.graduate.domain.Topic;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-6  上午10:46
 */
@Log4j2
@Service
public class BusTeacherService {
    private final TopicDao topicDao;

    @Autowired
    public BusTeacherService(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    /**
     * TODO 添加注释, 方便负责人能够查看到
     * 提交课题
     * @return JSON
     */
    public String submitCheck(Long topicId, Long teacherId, String comment) {
        try {
            Optional<Topic> result = topicDao.findById(topicId);
            if (result.isPresent()) {
                if (teacherId.equals(result.get().getTeacherId())) {
                    result.get().setStatus(TopicStatus.SUBMIT_NODE);
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
