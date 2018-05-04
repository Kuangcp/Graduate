package top.kuangcp.graduate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kuangcp.graduate.service.crud.CrudAdminService;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-4  下午10:14
 */
@RestController
@RequestMapping("/rest/admin")
public class RestAdminController {

    private final CrudAdminService adminService;

    @Autowired
    public RestAdminController(CrudAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public String getOne(@PathVariable("id") Long adminId){
        return adminService.getOne(adminId);
    }


}
