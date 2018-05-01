package top.kuangcp.graduate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kuangcp.graduate.service.curd.CurdTeacherService;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-1  下午11:43
 */
@RestController
@RequestMapping("/rest/teacher")
public class RestTeacherController {

    private final CurdTeacherService teacherService;

    @Autowired
    public RestTeacherController(CurdTeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/{teacherId}", produces = "application/json;charset=UTF-8")
    public String getOne(@PathVariable("teacherId") Long teacherId){
        return teacherService.getOne(teacherId);
    }
}
