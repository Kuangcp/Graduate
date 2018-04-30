package top.kuangcp.graduate.controller.rest;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.kuangcp.graduate.controller.util.HttpServletUtil;
import top.kuangcp.graduate.domain.College;
import top.kuangcp.graduate.service.curd.CurdCollegeService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-30  上午9:10
 */
@Log4j2
@RestController
@RequestMapping("/rest/college")
public class RestCollegeController {

    @Autowired
    CurdCollegeService collegeService;

    @GetMapping(value = "/listAll", produces="application/json;charset=UTF-8")
    public String listAll(int page, int limit, HttpServletResponse response){
        HttpServletUtil.configResponse(response);
        return collegeService.listAll(page-1, limit);
    }

    @GetMapping(value = "/{id}", produces="application/json;charset=UTF-8")
    public String getOne(@PathVariable("id") Long id){
        return collegeService.getOne(id);
    }

    @GetMapping(value = "/search", produces="application/json;charset=UTF-8")
    public String findByName(String name, int page, int limit){
        return collegeService.findByName(name, page-1, limit);
    }

    @DeleteMapping(produces="application/json;charset=UTF-8")
    public String delete(@RequestBody String idList){
        return collegeService.delete(idList);
    }

    @PostMapping(produces="application/json;charset=UTF-8")
    public String save(@RequestBody  College college) {
        log.info(college.toString());
        return collegeService.saveOne(college);
    }

}
