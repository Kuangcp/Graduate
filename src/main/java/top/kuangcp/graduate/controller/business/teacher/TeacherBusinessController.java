package top.kuangcp.graduate.controller.business.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.kuangcp.graduate.service.business.teacher.BusTeacherService;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-6  上午10:44
 */

@RestController
@RequestMapping("/bus/teacher")
public class TeacherBusinessController {

    private final BusTeacherService teacherService;

    @Autowired
    public TeacherBusinessController(BusTeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PutMapping("/submitCheck/{id}/{who}")
    public String submitCheck(@PathVariable("id") Long topicId, @PathVariable("who") Long teacherId, String comment){
        return teacherService.submitCheck(topicId, teacherId, comment);
    }
}
