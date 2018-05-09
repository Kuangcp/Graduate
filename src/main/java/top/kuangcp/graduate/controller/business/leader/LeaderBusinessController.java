package top.kuangcp.graduate.controller.business.leader;

import org.springframework.web.bind.annotation.*;
import top.kuangcp.graduate.domain.bo.ValidateTopic;
import top.kuangcp.graduate.service.business.leader.BusLeaderService;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-9  上午7:29
 */
@RestController
@RequestMapping("/bus/leader")
public class LeaderBusinessController {
    private final BusLeaderService leaderService;

    public LeaderBusinessController(BusLeaderService leaderService) {
        this.leaderService = leaderService;
    }


    @PostMapping(value = "/submitCheck/{id}/{who}", produces = "application/json;charset=UTF-8")
    public String submitCheck(@PathVariable("id") Long topicId, @PathVariable("who") Long teacherId, @RequestBody ValidateTopic comment){
        return leaderService.submitValidate(topicId, teacherId, comment);
    }

}
