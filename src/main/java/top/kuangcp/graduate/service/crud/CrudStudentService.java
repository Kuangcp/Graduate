package top.kuangcp.graduate.service.crud;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.role.StudentDao;
import top.kuangcp.graduate.domain.role.Student;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午9:38
 */
@Log4j2
@Service
public class CrudStudentService {

    private final StudentDao studentDao;

    @Autowired
    public CrudStudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public String getOne(Long studentId){
        Optional<Student> student = studentDao.findById(studentId);
        if(student.isPresent()){
            return JsonBuilder.buildSuccessResult(" ", student);
        }else {
            return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
        }
    }

}
