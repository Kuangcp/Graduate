package top.kuangcp.graduate.service.business.teacher;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.config.custom.TopicStatus;
import top.kuangcp.graduate.dao.DefenseScheduleDao;
import top.kuangcp.graduate.dao.TeamDao;
import top.kuangcp.graduate.dao.TopicCommentDao;
import top.kuangcp.graduate.dao.TopicDao;
import top.kuangcp.graduate.domain.DefenseSchedule;
import top.kuangcp.graduate.domain.Team;
import top.kuangcp.graduate.domain.Topic;
import top.kuangcp.graduate.domain.TopicComment;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-6  上午10:46
 */
@Log4j2
@Service
public class BusTeacherService {
    private final TopicDao topicDao;
    private final TopicCommentDao commentDao;
    private final DefenseScheduleDao defenseScheduleDao;
    private final TeamDao teamDao;

    @Autowired
    public BusTeacherService(TopicDao topicDao, TopicCommentDao commentDao, DefenseScheduleDao defenseScheduleDao, TeamDao teamDao) {
        this.topicDao = topicDao;
        this.commentDao = commentDao;
        this.defenseScheduleDao = defenseScheduleDao;
        this.teamDao = teamDao;
    }

    /**
     * TODO 添加注释, 方便负责人能够查看到
     * 提交课题
     * @return JSON
     */
    public String submitCheck(Long topicId, Long teacherId, String comment) {
//        log.info("收到注释 "+comment);
        try {
            Optional<Topic> result = topicDao.findById(topicId);
            if (result.isPresent()) {
                if (teacherId.equals(result.get().getTeacherId())) {
                    if(result.get().getStatus() >= TopicStatus.SUBMIT_NODE){
                        return JsonBuilder.buildCodeResult(ResponseCode.SUBMIT_REPEAT);
                    }
                    result.get().setStatus(TopicStatus.SUBMIT_NODE);
                    TopicComment comment1 = new TopicComment();

                    comment1.setTopicId(result.get().getTopicId());
                    List<TopicComment> resut = commentDao.findAllByTopicId(result.get().getTopicId());
                    comment1.setComment(comment.split("\"comment\":\"")[1].split("\"}")[0]);
                    comment1.setIndex(resut.size()+1);
                    comment1.setType(0);
                    commentDao.save(comment1);
                    topicDao.save(result.get());
                    return JsonBuilder.buildSuccessCodeResult();
                }
            }
            return JsonBuilder.buildCodeResult(ResponseCode.SUBMIT_ERROR);
        } catch (Exception e) {
            log.error("课题提交审核失败", e);
            return JsonBuilder.buildCodeResult(ResponseCode.SUBMIT_ERROR);
        }
    }

    public String queryPlace(Long teacherId) {
        // 根据学生找到课题找到教师，找到团队找到场地
        DefenseSchedule result = defenseScheduleDao.selectByTeacherId(teacherId);
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
}
