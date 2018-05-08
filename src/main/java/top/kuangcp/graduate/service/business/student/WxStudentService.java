package top.kuangcp.graduate.service.business.student;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.DefenseScheduleDao;
import top.kuangcp.graduate.dao.TeamDao;
import top.kuangcp.graduate.dao.TopicDao;
import top.kuangcp.graduate.dao.role.StudentDao;
import top.kuangcp.graduate.dao.role.TeacherDao;
import top.kuangcp.graduate.domain.DefenseSchedule;
import top.kuangcp.graduate.domain.Team;
import top.kuangcp.graduate.domain.Topic;
import top.kuangcp.graduate.domain.role.Student;
import top.kuangcp.graduate.service.crud.CrudTeacherService;
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
    private final CrudTeacherService teacherService;
    private final TeamDao teamDao;
    private final DefenseScheduleDao defenseScheduleDao;

    @Autowired
    public WxStudentService(TopicDao topicDao, StudentDao studentDao, TeacherDao teacherDao, CrudTeacherService teacherService, TeamDao teamDao, DefenseScheduleDao defenseScheduleDao) {
        this.topicDao = topicDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.teacherService = teacherService;
        this.teamDao = teamDao;
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
        log.info("topicid = "+topicId+" studnetid = "+studentId);
        Optional<Topic> topic = topicDao.findById(topicId);
        Optional<Student> student = studentDao.findById(studentId);
        if (topic.isPresent() && student.isPresent()) {
            topic.get().setStudentId(studentId);
            if (topicDao.findAllByStudentId(studentId).size() > 0) {
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
     * @param studentId 学生id
     * @return JSON
     */
    public String queryScore(Long studentId) {
        List<Topic> list = topicDao.findAllByStudentId(studentId);
        if (list.size() > 0) {
            list.get(0).setAttention("");
            return JsonBuilder.buildSuccessResult(" ", list.get(0));
        } else {
            return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
        }
    }
    public String queryScoreByWechat(String openId){
        List<Student> studentList = studentDao.findAllByOpenId(openId);
        if(studentList.size()==0){
            return "清先绑定账号";
        }
        List<Topic> list = topicDao.findAllByStudentId(studentList.get(0).getStudentId());
        if (list.size() > 0) {
            Topic topic = list.get(0);
            return "教师评分 "+topic.getGuideScore()+" 评委评分"+topic.getJudgeScore();

        } else {
            return "暂无成绩";
        }
    }

    /**
     * 查询答辩场地, 从学生id找到教师id， 找到团队id再找到安排表，对应年份，得到场地
     * @param studentId 学生id
     * @return JSON
     */
    public String queryPlace(Long studentId) {
        // 根据学生找到课题找到教师，找到团队找到场地
        DefenseSchedule result = defenseScheduleDao.selectByStudentId(studentId);
        if(result!=null && result.getPlace()!=null ){
            result.setAttention("");
            String json = JsonBuilder.buildSuccessResult("", result);
            Optional<Team> team = teamDao.findById(result.getTeamId());
            if(team.isPresent()){
                json = json.replace("\"teamId\":\""+result.getTeamId()+"\"", "\"team\":\""+team.get().getName()+"\"");
            }
            return json;
        }
        return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
    }
    public String queryPlaceByWechat(String openId){
        List<Student> studentList = studentDao.findAllByOpenId(openId);
        if(studentList.size()==0){
            return "清先绑定账号";
        }
        DefenseSchedule result = defenseScheduleDao.selectByStudentId(studentList.get(0).getStudentId());
        if(result!=null && result.getPlace()!=null ){
            Optional<Team> team = teamDao.findById(result.getTeamId());
            return team.map(team1 -> "场地 " + result.getPlace() + "\n日期 " + result.getDate() + "\n时间 " + result.getTime() + "\n团队 " + team1.getName()).orElseGet(() -> "场地 " + result.getPlace() + "\n日期 " + result.getDate() + "\n时间 " + result.getTime());

        }
        return "暂无安排";
    }

    public String queryTeacher(Long studentId){
        List<Topic> list = topicDao.findAllByStudentId(studentId);
        if(list.size() == 0){
            return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
        }
        return teacherService.getOneWithRefer(list.get(0).getTeacherId());
    }
}
