package top.kuangcp.graduate.service.crud;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.kuangcp.graduate.dao.MajorDao;
import top.kuangcp.graduate.dao.TopicTaskDao;
import top.kuangcp.graduate.dao.role.TeacherDao;
import top.kuangcp.graduate.domain.TopicTask;
import top.kuangcp.graduate.domain.role.Teacher;
import top.kuangcp.graduate.domain.vo.TopicTaskVO;
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
 * @date 18-5-8  下午7:26
 */
@Service
public class CrudTopicTaskService  {
    private final TopicTaskDao topicTaskDao;
    private final TeacherDao teacherDao;
    private final MajorDao majorDao;

    public CrudTopicTaskService(TopicTaskDao topicTaskDao, TeacherDao teacherDao, MajorDao majorDao) {
        this.topicTaskDao = topicTaskDao;
        this.teacherDao = teacherDao;
        this.majorDao = majorDao;
    }

    public String listAll(int page, int limit) {
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if (checkPageNum != null) {
            return checkPageNum;
        }
        Pageable pages = PageRequest.of(page, limit);
        List<TopicTask> list = topicTaskDao.findAll(pages).getContent();
        final List<TopicTaskVO> result = new ArrayList<>();
        list.forEach(item -> {
            TopicTaskVO temp = TopicTaskVO.of(item);
            Optional<Teacher> teacher = teacherDao.findById(item.getTeacherId());
            teacher.ifPresent(teacher1 -> {
                temp.setTeacher(teacher.get().getUsername());
                majorDao.findById(teacher.get().getMajorId()).ifPresent(major -> temp.setMajor(major.getName()));
            });
            result.add(temp);
        });
        return JsonBuilder.buildTableResult(0, " ", (int) topicTaskDao.count(), result);


//        return CrudServiceCommon.listAll(page, limit, topicTaskDao);
    }

    public String delete(String idList) {
        return null;
    }

    public String getOne(Long id) {
        return CrudServiceCommon.getOne(id, topicTaskDao);
    }

    public String uploadFile(MultipartFile file, HttpServletRequest request) {
        return null;
    }

    public String saveOne(TopicTask target) {
        return CrudServiceCommon.saveOne(target, topicTaskDao);
    }
}
