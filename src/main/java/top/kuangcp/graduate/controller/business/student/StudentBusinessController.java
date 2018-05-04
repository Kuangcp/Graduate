package top.kuangcp.graduate.controller.business.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/query/score/{id}", produces = "application/json;charset=UTF-8")
    public String queryScore(@PathVariable("id") Long studentId){
        return wxStudentService.queryScore(studentId);
    }

    @GetMapping(value = "/query/place/{id}", produces = "application/json;charset=UTF-8")
    public String queryPlace(@PathVariable("id") Long studentId){
        return wxStudentService.queryPlace(studentId);
    }

    @PostMapping(value = "/feedback", produces = "application/json;charset=UTF-8")
    public String save(Feedback feedback){
        return feedbackService.save(feedback);
    }

}
