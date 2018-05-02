package top.kuangcp.graduate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.domain.doc.ClassGroup;
import top.kuangcp.graduate.service.crud.CrudClassGroupService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  上午11:43
 */
@RestController
@RequestMapping("/rest/classGroup")
public class RestClassGroupController {

    private final CrudClassGroupService classGroupService;

    @Autowired
    public RestClassGroupController(CrudClassGroupService classGroupService) {
        this.classGroupService = classGroupService;
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public String getOne(@PathVariable("id") Long id) {
        return classGroupService.getOne(id);
    }

    @GetMapping(value = "/search/{id}", produces = "application/json;charset=UTF-8")
    public String findByName(String name, int page, int limit, @PathVariable("id") Long majorId) {
        return classGroupService.findByName(name, page - 1, limit, majorId);
    }

    @GetMapping(value = "/listTotal", produces = "application/json;charset=UTF-8")
    public String listTotal() {
        return classGroupService.listTotal();
    }
    @GetMapping(value = "/listTotalByMajor/{major}", produces = "application/json;charset=UTF-8")
    public String listTotalByCollege(@PathVariable("major") Long majorId) {
        return classGroupService.listTotalByMajor(majorId);
    }

    @GetMapping(value = "/listAll", produces = "application/json;charset=UTF-8")
    public String listAll(int page, int limit) {
        return classGroupService.listAll(page - 1, limit);
    }

    @GetMapping(value = "/listByMajor/{major}", produces = "application/json;charset=UTF-8")
    public String listAllByCollege(int page, int limit, @PathVariable("major") Long majorId) {
        return classGroupService.listByMajor(page - 1, limit, majorId);
    }

    @DeleteMapping(produces = "application/json;charset=UTF-8")
    public String delete(@RequestBody String idList) {
        return classGroupService.delete(idList);
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public String save(@RequestBody ClassGroup classGroup) {
        return classGroupService.saveOne(classGroup);
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return classGroupService.uploadFile(file, request);
    }
}
