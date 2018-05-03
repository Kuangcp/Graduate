package top.kuangcp.graduate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kuangcp.graduate.service.crud.CrudTeamService;

/**
 * Created by https://github.com/kuangcp
 * TODO 删除
 * @author kuangcp
 * @date 18-5-3  上午10:12
 */
@RestController
@RequestMapping("/rest/team")
public class RestTeamController {

    private final CrudTeamService teamService;

    @Autowired
    public RestTeamController(CrudTeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(value = "/listTotal", produces = "application/json;charset=UTF-8")
    public String listTotal() {
        return teamService.listTotal();
    }

    @GetMapping(value = "/listAll", produces = "application/json;charset=UTF-8")
    public String listAll(int page, int limit) {
        return teamService.listAllWithRefer(page - 1, limit);
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public String getOne(@PathVariable("id") Long id) {
        return teamService.getOne(id);
    }

    @GetMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String findByName(String name, int page, int limit) {
        return teamService.findByName(name, page - 1, limit);
    }

}
