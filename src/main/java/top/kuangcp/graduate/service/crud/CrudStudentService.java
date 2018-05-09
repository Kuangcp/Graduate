package top.kuangcp.graduate.service.crud;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.TeamDao;
import top.kuangcp.graduate.dao.role.StudentDao;
import top.kuangcp.graduate.dao.role.TeacherDao;
import top.kuangcp.graduate.domain.role.Student;
import top.kuangcp.graduate.domain.role.Teacher;
import top.kuangcp.graduate.service.FileUploadService;
import top.kuangcp.graduate.service.crud.base.BaseCrud;
import top.kuangcp.graduate.service.crud.base.CrudServiceCommon;
import top.kuangcp.graduate.service.verify.CheckPage;
import top.kuangcp.graduate.util.JsonBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午9:38
 */
@Log4j2
@Service
public class CrudStudentService implements BaseCrud {

    private final StudentDao studentDao;
    private final TeacherDao teacherDao;
    private final FileUploadService uploadService;
    private final TeamDao teamDao;

    @Autowired
    public CrudStudentService(StudentDao studentDao, TeacherDao teacherDao, FileUploadService uploadService, TeamDao teamDao) {
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.uploadService = uploadService;
        this.teamDao = teamDao;
    }

    public String listTotal(){
        return CrudServiceCommon.listTotal(studentDao);
    }

    public String getOne(Long studentId){
        return CrudServiceCommon.getOne(studentId, studentDao);
    }

    @Override
    public String listAll(int page, int limit) {
        return CrudServiceCommon.listAll(page, limit, studentDao);
    }

    @Override
    public String delete(String idList) {
        return CrudServiceCommon.delete(idList, studentDao);
    }

    @Override
    public String uploadFile(MultipartFile file, HttpServletRequest request) {
        return CrudServiceCommon.uploadFile(uploadService, file, request, studentDao, Student.class);
    }

    /**
     * 更新指定对象，但是不更新教师
     * @param target 目标对象
     * @return JSON
     */
    public String saveOne(Student target) {
        Optional<Student> origin = studentDao.findById(target.getStudentId());
        target.setTeacherId(origin.get().getTeacherId());
        try {
            studentDao.save(target);
        } catch (Exception e) {
            return JsonBuilder.buildCodeResult(ResponseCode.ADD_ERROR);
        }
        return JsonBuilder.buildSuccessCodeResult();
    }

    @Override
    public String saveOne(ExcelTransform target) {
        return " ";
    }

    public String listByClassGroup(int page, int limit, Long classGroupId) {
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if (checkPageNum != null) {
            return checkPageNum;
        }
        Pageable pages = PageRequest.of(page, limit);
        List list = studentDao.findAllByClassGroupId(classGroupId, pages).getContent();
        return JsonBuilder.buildTableResult(0, " ", studentDao.findAllByClassGroupId(classGroupId).size(), list);
    }

    public String findByName(String name, int page, int limit, Long classGroupId) {
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if (checkPageNum != null) {
            return checkPageNum;
        }
        Page<Student> list = studentDao.findByUsernameContaining(name, PageRequest.of(page, limit));
        final List<Student> result = new ArrayList<>();
        // 过滤出相等的并添加到集合
        list.filter(item -> item.getClassGroupId().equals(classGroupId)).iterator().forEachRemaining(result::add);
        if (list.getTotalElements() == 0) {
            return JsonBuilder.buildSuccessTableResult("", 0, "");
        }
        return JsonBuilder.buildSuccessTableResult(" ", (int) list.getTotalElements(), result);
    }

    // TODO 查询所有学生
    public String listByTeamId(Long teamId) {
        List<Teacher> teacherList = teacherDao.findAllByTeamId(teamId);
        List result = new ArrayList();
        for(Teacher teacher: teacherList){
            List list = studentDao.findAllByTeacherId(teacher.getTeacherId());
            result.addAll(list);
        }
        return JsonBuilder.buildSuccessResult(" ", result);
    }
}
