package top.kuangcp.graduate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.domain.doc.Major;
import top.kuangcp.graduate.service.crud.CrudMajorService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  上午8:03
 */
@RestController
@RequestMapping("/rest/major")
public class RestMajorController {

    private final CrudMajorService majorService;

    @Autowired
    public RestMajorController(CrudMajorService majorService) {
        this.majorService = majorService;
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public String getOne(@PathVariable("id") Long id) {
        return majorService.getOne(id);
    }

    @GetMapping(value = "/search/{id}", produces = "application/json;charset=UTF-8")
    public String findByName(String name, int page, int limit, @PathVariable("id") Long collegeId) {
        return majorService.findByName(name, page - 1, limit, collegeId);
    }

    @GetMapping(value = "/listTotal", produces = "application/json;charset=UTF-8")
    public String listTotal() {
        return majorService.listTotal();
    }
    @GetMapping(value = "/listAll", produces = "application/json;charset=UTF-8")
    public String listAll(int page, int limit) {
        return majorService.listAll(page - 1, limit);
    }


    @GetMapping(value = "/listByCollege/{college}", produces = "application/json;charset=UTF-8")
    public String listAllByCollege(int page, int limit, @PathVariable("college") Long collegeId) {
        return majorService.listByCollege(page - 1, limit, collegeId);
    }
    @GetMapping(value = "/listTotalByCollege/{college}", produces = "application/json;charset=UTF-8")
    public String listTotalByCollege(@PathVariable("college") Long collegeId) {
        return majorService.listTotalByCollege(collegeId);
    }


    @DeleteMapping(produces = "application/json;charset=UTF-8")
    public String delete(@RequestBody String idList) {
        return majorService.delete(idList);
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public String save(@RequestBody Major major) {
        return majorService.saveOne(major);
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return majorService.uploadFile(file, request);
    }

}
