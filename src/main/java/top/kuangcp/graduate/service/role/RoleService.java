package top.kuangcp.graduate.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.dao.role.AdminDao;
import top.kuangcp.graduate.dao.role.StudentDao;
import top.kuangcp.graduate.dao.role.TeacherDao;

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
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    AdminDao adminDao;

    public boolean loginWithStudent(String username, String password) {
        return password.equals(studentDao.findByUsername(username).getPassword());
    }
    public boolean loginWithTeacher(String username, String password){
        return password.equals(teacherDao.findByUsername(username).getPassword());
    }
    public boolean loginWithAdmin(String username, String password){
        return password.equals(adminDao.findByUsername(username).getPassword());
    }
}
