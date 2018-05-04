package top.kuangcp.graduate.service.crud;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.dao.role.AdminDao;
import top.kuangcp.graduate.service.crud.base.CrudServiceCommon;

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
}
