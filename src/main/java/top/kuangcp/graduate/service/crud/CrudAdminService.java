package top.kuangcp.graduate.service.crud;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.role.AdminDao;
import top.kuangcp.graduate.domain.bo.PasswordPairBO;
import top.kuangcp.graduate.domain.role.Admin;
import top.kuangcp.graduate.service.crud.base.CrudServiceCommon;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-4  下午10:13
 */
@Log4j2
@Service
public class CrudAdminService {
    private final AdminDao adminDao;

    @Autowired
    public CrudAdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public String getOne(Long adminId){
        return CrudServiceCommon.getOne(adminId, adminDao);
    }

    public String update(Long id, PasswordPairBO passPair){

        if(passPair == null){
            return JsonBuilder.buildCodeResult(ResponseCode.UPDATE_ERROR);
        }
        log.info(passPair.toString());
        String oldPass = passPair.getOldPass();
        String newPass = passPair.getNewPass();
        Optional<Admin> admin =  adminDao.findById(id);
        if(oldPass==null || newPass == null){
            return JsonBuilder.buildCodeResult(ResponseCode.UPDATE_ERROR);
        }
        if (admin.isPresent()){
            if(oldPass.equals(admin.get().getPassword())){
                admin.get().setPassword(newPass);
                adminDao.save(admin.get());
                return JsonBuilder.buildSuccessCodeResult();
            }else{
                return JsonBuilder.buildCodeResult(ResponseCode.PASSWORD_VALID);
            }
        }else{
            return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
        }
    }
}
