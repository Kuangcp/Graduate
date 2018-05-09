package top.kuangcp.graduate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.domain.role.Student;
import top.kuangcp.graduate.service.crud.CrudStudentService;

import javax.servlet.http.HttpServletRequest;

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


    @GetMapping(value = "/search/{id}", produces = "application/json;charset=UTF-8")
    public String findByName(String name, int page, int limit, @PathVariable("id") Long classGroupId) {
        return studentService.findByName(name, page - 1, limit, classGroupId);
    }

    @GetMapping(value = "/listTotal", produces = "application/json;charset=UTF-8")
    public String listTotal() {
        return studentService.listTotal();
    }

    @GetMapping(value = "/listAll", produces = "application/json;charset=UTF-8")
    public String listAll(int page, int limit) {
        return studentService.listAll(page - 1, limit);
    }


    @GetMapping(value = "/listByTeamId/{teamId}", produces = "application/json;charset=UTF-8")
    public String listAllByCollege(@PathVariable("teamId") Long teamId) {
        return studentService.listByTeamId(teamId);
    }

    @GetMapping(value = "/listByClassGroup/{classGroup}", produces = "application/json;charset=UTF-8")
    public String listAllByCollege(int page, int limit, @PathVariable("classGroup") Long classGroupId) {
        return studentService.listByClassGroup(page - 1, limit, classGroupId);
    }

    @DeleteMapping(produces = "application/json;charset=UTF-8")
    public String delete(@RequestBody String idList) {
        return studentService.delete(idList);
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public String save(@RequestBody Student student) {
        return studentService.saveOne(student);
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return studentService.uploadFile(file, request);
    }
}
