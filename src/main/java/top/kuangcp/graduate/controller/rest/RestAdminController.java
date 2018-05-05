package top.kuangcp.graduate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.kuangcp.graduate.domain.bo.PasswordPairBO;
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

    @PostMapping(value = "/password/{id}", produces = "application/json;charset=UTF-8")
    public String updatePassword(@PathVariable("id") Long adminId, @RequestBody PasswordPairBO passPair){
        String result = adminService.update(adminId, passPair);
        System.out.println(result);
        return result;
    }
}
