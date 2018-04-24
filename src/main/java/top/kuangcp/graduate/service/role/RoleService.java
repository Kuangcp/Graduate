package top.kuangcp.graduate.service.role;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.dao.StudentDao;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午9:44
 */
@Service
public class RoleService {

    private Logger log = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    StudentDao studentDao;

    public boolean loginByStudent(String username, String password){
        log.info("收到"+username+" "+password);


        return password.equals(studentDao.findByUsername(username).getPassword());
    }

}
