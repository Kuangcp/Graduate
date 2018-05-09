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

    // TODO 为什么用PUT收不到数据
    @PostMapping(value = "/submitCheck/{id}/{who}", produces = "application/json;charset=UTF-8")
    public String submitCheck(@PathVariable("id") Long topicId, @PathVariable("who") Long teacherId, @RequestBody String comment){
        return teacherService.submitCheck(topicId, teacherId, comment);
    }

    @GetMapping(value = "/query/place/{id}", produces = "application/json;charset=UTF-8")
    public String queryPlace(@PathVariable("id") Long teacherId){
        return teacherService.queryPlace(teacherId);
    }



}
