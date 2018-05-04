package top.kuangcp.graduate.service.crud;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.dao.MajorDao;
import top.kuangcp.graduate.dao.TeamDao;
import top.kuangcp.graduate.dao.role.TeacherDao;
import top.kuangcp.graduate.domain.Team;
import top.kuangcp.graduate.domain.doc.Major;
import top.kuangcp.graduate.domain.role.Teacher;
import top.kuangcp.graduate.domain.vo.TeamVO;
import top.kuangcp.graduate.service.crud.base.CrudServiceCommon;
import top.kuangcp.graduate.service.verify.CheckPage;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-3  上午10:09
 */
@Log4j2
@Service
public class CrudTeamService {
    private final TeamDao teamDao;
    private final MajorDao majorDao;
    private final TeacherDao teacherDao;

    public CrudTeamService(TeamDao teamDao, MajorDao majorDao, TeacherDao teacherDao) {
        this.teamDao = teamDao;
        this.majorDao = majorDao;
        this.teacherDao = teacherDao;
    }

    public String listAll(int page, int limit) {
        return CrudServiceCommon.listAll(page, limit, teamDao);
    }

    /**
     * 视图, 将外键装载进去 TODO 怎么用更简洁的方式处理,达到同样的功能
     */
    public String listAllWithRefer(int page, int limit) {
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if (checkPageNum != null) {
            return checkPageNum;
        }
        Pageable pages = PageRequest.of(page, limit);
        List<Team> list = teamDao.findAll(pages).getContent();
        List<TeamVO> result = teamToTeamVO(list);
        return JsonBuilder.buildTableResult(0, " ", (int) teamDao.count(), result);
    }

    public String delete(String idList) {
        return CrudServiceCommon.delete(idList, teamDao);
    }

    public String getOne(Long id) {
        return CrudServiceCommon.getOne(id, teamDao);
    }


    public String saveOne(Team target) {
        return CrudServiceCommon.saveOne(target, teamDao);
    }

    public String listTotal() {
        return CrudServiceCommon.listTotal(teamDao);
    }

    public String findByName(String name, int page, int limit) {
        String checkPageNum = CheckPage.checkPageNum(page, limit);
        if (checkPageNum != null) {
            return checkPageNum;
        }
        Page<Team> list = teamDao.findByNameContaining(name, PageRequest.of(page, limit));
        final List<TeamVO> result = teamToTeamVO(list.getContent());
        if (list.getTotalElements() == 0) {
            return JsonBuilder.buildSuccessTableResult("", 0, "");
        }
        return JsonBuilder.buildSuccessTableResult(" ", (int) list.getTotalElements(), result);
    }

    private List<TeamVO> teamToTeamVO(List<Team> list) {
        final List<TeamVO> result = new ArrayList<>();
        list.forEach(item -> {
            TeamVO teamVO = TeamVO.of(item);
            if (item.getAssistantId() != null) {
                Optional<Teacher> assistant = teacherDao.findById(item.getAssistantId());
                assistant.ifPresent(teacher -> teamVO.setAssistant(teacher.getUsername()));
            } else {
                teamVO.setAssistant("暂无");
            }
            if (item.getJudgeTeamId() != null) {
                Optional<Team> team = teamDao.findById(item.getJudgeTeamId());
                team.ifPresent(team1 -> teamVO.setJudgeTeam(team1.getName()));
            } else {
                teamVO.setJudgeTeam("暂无");
            }

            List<Teacher> teachers = teacherDao.findAllByTeamId(item.getTeamId());
            if (teachers == null || teachers.size() == 0) {
                teamVO.setTeachers("暂无");
            } else {
                final StringBuilder temp = new StringBuilder();
                teachers.forEach(teacher -> temp.append(teacher.getUsername()).append(","));
                teamVO.setTeachers(temp.toString().substring(0, temp.length() - 1));
            }
            // 专业是一定要有的,否则是新建的时候出问题了
            Optional<Major> major = majorDao.findById(item.getMajorId());
            major.ifPresent(major1 -> {
                teamVO.setMajor(major1.getName());
                if (major1.getLeaderId() != null) {
                    Optional<Teacher> teacher = teacherDao.findById(major1.getLeaderId());
                    if (teacher.isPresent()) {
                        teamVO.setLeader(teacher.get().getUsername());
                    } else {
                        teamVO.setLeader("暂无");
                    }
                } else {
                    teamVO.setLeader("暂无");
                }
            });
            result.add(teamVO);
        });
        return result;
    }

    public String listOtherAll(Long teamId) {
        final List<Team> list = new ArrayList<>(teamDao.findAll());

        if (list.size() == 0) {
            return JsonBuilder.buildCodeResult(ResponseCode.NOT_FOUND);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (teamId == list.get(i).getTeamId()) {
                    list.remove(i);
                }
            }
            return JsonBuilder.buildSuccessResult(" ", list);
        }
    }
}
