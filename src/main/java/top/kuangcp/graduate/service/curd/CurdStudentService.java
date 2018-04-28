package top.kuangcp.graduate.service.curd;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.dao.role.StudentDao;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午9:38
 */
@Log4j2
@Service
public class CurdStudentService {

    @Autowired
    StudentDao studentDao;

}
