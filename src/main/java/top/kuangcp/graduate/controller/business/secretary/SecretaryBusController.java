package top.kuangcp.graduate.controller.business.secretary;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kuangcp.graduate.domain.DefenseSchedule;
import top.kuangcp.graduate.domain.Team;
import top.kuangcp.graduate.service.business.secretary.BusSecretaryService;

/**
 * Created by https://github.com/kuangcp
 * TODO 安排场地， 记录成绩
 * @author kuangcp
 * @date 18-5-9  上午10:01
 */
@RestController
@RequestMapping("/bus/secretary")
public class SecretaryBusController {

    private final BusSecretaryService secretaryService;

    public SecretaryBusController(BusSecretaryService secretaryService) {
        this.secretaryService = secretaryService;
    }

    @PostMapping(value = "/assistant", produces = "application/json;charset=UTF-8")
    public String updateLeader(@RequestBody DefenseSchedule defenseSchedule) {
        return secretaryService.saveOne(defenseSchedule);
    }


}
