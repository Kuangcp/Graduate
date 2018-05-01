package top.kuangcp.graduate.service.curd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.role.TeacherDao;
import top.kuangcp.graduate.domain.role.Teacher;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-1  下午11:46
 */
@Service
public class CurdTeacherService {

    private final TeacherDao teacherDao;

    @Autowired
    public CurdTeacherService(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public String getOne(Long teacherId){
        Optional<Teacher> teacher = teacherDao.findById(teacherId);
        return teacher.map(teacher1 -> JsonBuilder.buildSuccessResult("", teacher1)).orElseGet(() -> JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND));
    }
}
