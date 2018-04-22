package top.kuangcp.graduate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.dao.StudentDao;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午9:38
 */
@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

}
