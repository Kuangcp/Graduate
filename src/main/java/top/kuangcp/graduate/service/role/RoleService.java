package top.kuangcp.graduate.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.dao.StudentDao;

/**
 * Created by https://github.com/kuangcp
 * 处理角色的登录服务，
 * @author kuangcp
 * @date 18-4-22  下午9:44
 */
@Service
public class RoleService {

    @Autowired
    StudentDao studentDao;

    public boolean loginByStudent(String username, String password) {
        return password.equals(studentDao.findByUsername(username).getPassword());
    }
}
