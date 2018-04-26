package top.kuangcp.graduate.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.dao.role.AdminDao;
import top.kuangcp.graduate.dao.role.StudentDao;
import top.kuangcp.graduate.dao.role.TeacherDao;
import top.kuangcp.graduate.domain.role.Admin;
import top.kuangcp.graduate.domain.role.Student;
import top.kuangcp.graduate.domain.role.Teacher;

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
        Student student = studentDao.findByUsername(username);
        if(student == null) return false;
        return password.equals(student.getPassword());
    }
    public boolean loginWithTeacher(String username, String password){
        Teacher teacher = teacherDao.findByUsername(username);
        if(teacher == null) return false;
        return password.equals(teacher.getPassword());
    }
    public boolean loginWithAdmin(String username, String password){
        Admin admin = adminDao.findByUsername(username);
        if(admin == null) return false;
        return password.equals(admin.getPassword());
    }
}
