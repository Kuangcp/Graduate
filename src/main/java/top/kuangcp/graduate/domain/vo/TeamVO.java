package top.kuangcp.graduate.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.kuangcp.graduate.domain.Team;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-3  上午10:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamVO {
    private long teamId;
    private String name;
    private String field;//研究领域
    private String year;

    // 外键
    private String major;
    private String judgeTeam;// 评委团队
    private String leader;
    private String assistant;

    public static TeamVO of(Team team){
        TeamVO teamVO = new TeamVO();
        teamVO.setName(team.getName());
        teamVO.setYear(team.getYear());
        teamVO.setTeamId(team.getTeamId());
        teamVO.setField(team.getField());
        return teamVO;
    }
}
