package top.kuangcp.graduate.service.business.secretary;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.dao.DefenseScheduleDao;
import top.kuangcp.graduate.domain.DefenseSchedule;
import top.kuangcp.graduate.service.crud.base.CrudServiceCommon;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-9  上午10:05
 */
@Log4j2
@Service
public class BusSecretaryService {

    private final DefenseScheduleDao defenseScheduleDao;

    public BusSecretaryService(DefenseScheduleDao defenseScheduleDao) {
        this.defenseScheduleDao = defenseScheduleDao;
    }

    public String saveOne(DefenseSchedule defenseSchedule) {
        return CrudServiceCommon.saveOne(defenseSchedule, defenseScheduleDao);
    }
}
