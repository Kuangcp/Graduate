package top.kuangcp.graduate.service.crud;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.LeaderDao;
import top.kuangcp.graduate.dao.MajorDao;
import top.kuangcp.graduate.dao.SecretaryDao;
import top.kuangcp.graduate.dao.role.TeacherDao;
import top.kuangcp.graduate.domain.Leader;
import top.kuangcp.graduate.domain.Secretary;
import top.kuangcp.graduate.domain.doc.Major;
import top.kuangcp.graduate.domain.role.Teacher;
import top.kuangcp.graduate.domain.vo.TeacherVO;
import top.kuangcp.graduate.service.crud.base.CrudServiceCommon;
import top.kuangcp.graduate.util.JsonBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-1  下午11:46
 */
@Service
public class CrudTeacherService{

    private final TeacherDao teacherDao;
    private final LeaderDao leaderDao;
    private final MajorDao majorDao;
    private final SecretaryDao secretaryDao;


    @Autowired
    public CrudTeacherService(TeacherDao teacherDao, LeaderDao leaderDao, MajorDao majorDao, SecretaryDao secretaryDao) {
        this.teacherDao = teacherDao;
        this.leaderDao = leaderDao;
        this.majorDao = majorDao;
        this.secretaryDao = secretaryDao;
    }

    public String listAll(int page, int limit) {
        return null;
    }

    public String delete(String idList) {
        return null;
    }

    public String getOne(Long teacherId){
        return CrudServiceCommon.getOne(teacherId, teacherDao);
    }

    public String uploadFile(MultipartFile file, HttpServletRequest request) {
        return null;
    }

    public String saveOne(ExcelTransform target) {
        return null;
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

    /**
     * 找出所有角色
     * @return JSON
     */
    public String listRoles(Long teacherId){
        final StringBuilder builder = new StringBuilder();
        Optional<Teacher> teacher = teacherDao.findById(teacherId);

        if(teacher.isPresent()){
            builder.append("TEACHER#");
        }else{
            return JsonBuilder.buildCodeResult(ResponseCode.NOT_FOUND);
        }
        Optional<Leader> leader = leaderDao.findByTeacherId(teacherId);
        Optional<Secretary> secretary = secretaryDao.findByTeacherId(teacherId);
        leader.ifPresent(item-> builder.append("LEADER#"));
        secretary.ifPresent(item-> builder.append("SECRETARY#"));
        return JsonBuilder.buildSuccessResult("", builder.toString());
    }

    public String listTotal() {
        return CrudServiceCommon.listTotal(teacherDao);
    }
}
