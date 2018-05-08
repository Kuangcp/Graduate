package top.kuangcp.graduate.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.FeedbackDao;
import top.kuangcp.graduate.domain.Feedback;
import top.kuangcp.graduate.util.JsonBuilder;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  上午7:53
 */
@Service
public class CrudFeedbackService {

    private final FeedbackDao feedbackDao;

    @Autowired
    public CrudFeedbackService(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    public String save(String content, Long studentId, String contact){
        Feedback feedback = new Feedback();
        feedback.setContact(contact);
        feedback.setContent(content);
        feedback.setStudentId(studentId);
        try {
            feedbackDao.save(feedback);
        }catch (Exception e){
            return JsonBuilder.buildCodeResult(ResponseCode.ADD_ERROR);
        }
        return JsonBuilder.buildSuccessCodeResult();
    }
}
