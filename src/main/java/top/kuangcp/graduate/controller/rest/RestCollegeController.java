package top.kuangcp.graduate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.domain.doc.College;
import top.kuangcp.graduate.service.crud.CrudCollegeService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-30  上午9:10
 */
@RestController
@RequestMapping("/rest/college")
public class RestCollegeController {

    private final CrudCollegeService collegeService;

    @Autowired
    public RestCollegeController(CrudCollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping(value = "/listTotal", produces = "application/json;charset=UTF-8")
    public String listTotal(){
        return collegeService.listTotal();
    }
    @GetMapping(value = "/listAll", produces = "application/json;charset=UTF-8")
    public String listAll(int page, int limit) {
        return collegeService.listAll(page - 1, limit);
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public String getOne(@PathVariable("id") Long id) {
        return collegeService.getOne(id);
    }

    @GetMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String findByName(String name, int page, int limit) {
        return collegeService.findByName(name, page - 1, limit);
    }

    @DeleteMapping(produces = "application/json;charset=UTF-8")
    public String delete(@RequestBody String idList) {
        return collegeService.delete(idList);
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public String save(@RequestBody College college) {
        return collegeService.saveOne(college);
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return collegeService.uploadFile(file, request);
    }
}
