package top.kuangcp.graduate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kuangcp.graduate.service.crud.CrudStudentService;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  上午7:40
 */
@RestController
@RequestMapping("/rest/student")
public class RestStudentController {
    private final CrudStudentService studentService;

    @Autowired
    public RestStudentController(CrudStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public String getOne(@PathVariable("id") Long studentId){
        return studentService.getOne(studentId);
    }
}
