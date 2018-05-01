package top.kuangcp.graduate.service.business.student;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.DefenseScheduleDao;
import top.kuangcp.graduate.dao.TopicDao;
import top.kuangcp.graduate.dao.role.StudentDao;
import top.kuangcp.graduate.dao.role.TeacherDao;
import top.kuangcp.graduate.domain.DefenseSchedule;
import top.kuangcp.graduate.domain.Topic;
import top.kuangcp.graduate.domain.role.Student;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 * 学生操作课题逻辑
 *
 * @author kuangcp
 * @date 18-5-1  下午9:06
 */
@Log4j2
@Service
public class WxStudentService {

    private final TopicDao topicDao;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;
    private final DefenseScheduleDao defenseScheduleDao;

    @Autowired
    public WxStudentService(TopicDao topicDao, StudentDao studentDao, TeacherDao teacherDao, DefenseScheduleDao defenseScheduleDao) {
        this.topicDao = topicDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.defenseScheduleDao = defenseScheduleDao;
    }

    /**
     * 选择课题
     *
     * @param topicId   课题id
     * @param studentId 学生id
     * @return JSON
     */
    public String selectTopic(Long topicId, Long studentId) {
        Optional<Topic> topic = topicDao.findById(topicId);
        Optional<Student> student = studentDao.findById(studentId);
        if (topic.isPresent() && student.isPresent()) {
            topic.get().setStudentId(studentId);
            if (topicDao.findAllByStudentId(studentId).size() > 1) {
                return JsonBuilder.buildCodeResult(ResponseCode.ALREADY_SELECT);
            }
            topicDao.save(topic.get());
            return JsonBuilder.buildSuccessResult("", topic.get());
        } else {
            return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
        }
    }

    /**
     * 查询分数
     *
     * @param studentId 学生id
     * @return JSON
     */
    public String queryScore(Long studentId) {
        List<Topic> list = topicDao.findAllByStudentId(studentId);
        if (list.size() > 0) {
            return JsonBuilder.buildSuccessResult(" ", list.get(0));
        } else {
            return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
        }
    }

    /**
     * 查询答辩场地
     * TODO 使用SQL连接查询
     *
     * @param studentId 学生id
     * @return JSON
     */
    public String QueryPlace(Long studentId) {
        // 根据学生找到课题找到教师，找到团队找到场地

        DefenseSchedule result = defenseScheduleDao.queryByStudentId(studentId);
        if(result!=null && result.getPlace()!=null ){
            return JsonBuilder.buildSuccessResult("", result.getPlace());
        }
//        Optional<Student> student = studentDao.findById(studentId);
//        if (student.isPresent()) {
//            Optional<Teacher> teacher = teacherDao.findById(student.get().getTeacherId());
//            if (teacher.isPresent()) {
//                List<DefenseSchedule> defenseSchedules = defenseScheduleDao.findAllByTeamId(teacher.get().getTeamId());
//                if (defenseSchedules.size() != 0) {
//                    return JsonBuilder.buildSuccessResult(" ", defenseSchedules.get(0).getPlace());
//                }
//            }
//        }
        return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
    }
}
