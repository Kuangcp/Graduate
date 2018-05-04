package top.kuangcp.graduate.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.MajorDao;
import top.kuangcp.graduate.dao.role.TeacherDao;
import top.kuangcp.graduate.domain.doc.Major;
import top.kuangcp.graduate.domain.role.Teacher;
import top.kuangcp.graduate.domain.vo.TeacherVO;
import top.kuangcp.graduate.service.crud.base.CrudServiceCommon;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-1  下午11:46
 */
@Service
public class CrudTeacherService {

    private final TeacherDao teacherDao;
    private final MajorDao majorDao;

    @Autowired
    public CrudTeacherService(TeacherDao teacherDao, MajorDao majorDao) {
        this.teacherDao = teacherDao;
        this.majorDao = majorDao;
    }

    public String getOne(Long teacherId){
        return CrudServiceCommon.getOne(teacherId, teacherDao);
    }

    public String getOneWithRefer(Long teacherId){
        Optional<Teacher> teacher = teacherDao.findById(teacherId);
        if(teacher.isPresent()){
            Optional<Major> major =  majorDao.findById(teacher.get().getMajorId());
            if(major.isPresent()){
                TeacherVO teacherVO = TeacherVO.of(teacher.get());
                teacherVO.setMajor(major.get().getName());
                return JsonBuilder.buildSuccessResult("", teacherVO);
            }
        }
        return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
    }

    public String listTotalByMajor(Long majorId) {
        List<Teacher> list = teacherDao.findAllByMajorId(majorId);
        if(list == null){
            return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
        }else{
            return JsonBuilder.buildSuccessResult(" ", list);
        }
    }
}
