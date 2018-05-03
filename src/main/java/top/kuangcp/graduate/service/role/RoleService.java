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
 *
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

    public Long loginWithStudent(String username, String password) {
        Student student = studentDao.findByStudentNo(username);
        if (student == null) return null;
        if (password.equals(student.getPassword())) {
            return student.getStudentId();
        } else {
            return null;
        }
    }

    public Long loginWithTeacher(String username, String password) {
        Teacher teacher = teacherDao.findByTeacherNo(username);
        if (teacher == null) return null;
        if (password.equals(teacher.getPassword())) {
            return teacher.getTeacherId();
        } else {
            return null;
        }
    }

    public Long loginWithAdmin(String username, String password) {
        Admin admin = adminDao.findByUsername(username);
        if (admin == null) return null;
        if (password.equals(admin.getPassword())) {
            return admin.getAdminId();
        } else {
            return null;
        }
    }
}
