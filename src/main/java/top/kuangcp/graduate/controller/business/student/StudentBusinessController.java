package top.kuangcp.graduate.controller.business.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kuangcp.graduate.domain.Feedback;
import top.kuangcp.graduate.service.business.student.WxStudentService;
import top.kuangcp.graduate.service.crud.CrudFeedbackService;

/**
 * Created by https://github.com/kuangcp
 * 学生在移动端业务操作的类
 * @author kuangcp
 * @date 18-5-1  下午9:03
 */

@RestController
@RequestMapping("/bus/student")
public class StudentBusinessController {

    private final WxStudentService wxStudentService;
    private final CrudFeedbackService feedbackService;

    @Autowired
    public StudentBusinessController(WxStudentService wxStudentService, CrudFeedbackService feedbackService) {
        this.wxStudentService = wxStudentService;
        this.feedbackService = feedbackService;
    }

    @PostMapping(value = "/topic/select", produces = "application/json;charset=UTF-8")
    public String selectTopic(Long topicId, Long studentId){
        return wxStudentService.selectTopic(topicId, studentId);
    }

    @GetMapping(value = "/query/score", produces = "application/json;charset=UTF-8")
    public String queryScore(Long studentId){
        return wxStudentService.queryScore(studentId);
    }

    @GetMapping(value = "/query/place", produces = "application/json;charset=UTF-8")
    public String queryPlace(Long studentId){
        return wxStudentService.QueryPlace(studentId);
    }

    @PostMapping(value = "", produces = "application/json;charset=UTF-8")
    public String save(Feedback feedback){
        return feedbackService.save(feedback);
    }

}
