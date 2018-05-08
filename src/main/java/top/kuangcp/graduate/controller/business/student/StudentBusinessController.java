package top.kuangcp.graduate.controller.business.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kuangcp.graduate.service.business.student.WxStudentService;
import top.kuangcp.graduate.service.crud.CrudFeedbackService;
import top.kuangcp.graduate.service.crud.CrudTopicService;

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
    private final CrudTopicService topicService;

    @Autowired
    public StudentBusinessController(WxStudentService wxStudentService, CrudFeedbackService feedbackService, CrudTopicService topicService) {
        this.wxStudentService = wxStudentService;
        this.feedbackService = feedbackService;
        this.topicService = topicService;
    }

    @GetMapping(value = "/topic/select", produces = "application/json;charset=UTF-8")
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

    @GetMapping(value = "/feedback", produces = "application/json;charset=UTF-8")
    public String save(String content, Long studentId, String contact){
        return feedbackService.save(content, studentId, contact);
    }
    @GetMapping(value = "/listTopic", produces = "application/json;charset=UTF-8")
    public String listTopic(){
        return topicService.listNoSelected();
    }
    @GetMapping(value = "/query/teacher/{studentId}", produces = "application/json;charset=UTF-8")
    public String queryTeacher(@PathVariable("studentId") Long studentId){
        return wxStudentService.queryTeacher(studentId);
    }
}
