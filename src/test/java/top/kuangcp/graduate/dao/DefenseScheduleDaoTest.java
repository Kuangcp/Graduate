package top.kuangcp.graduate.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.kuangcp.graduate.domain.DefenseSchedule;

/**
 * Created by https://github.com/kuangcp
 * TODO 为什么不能使用 final 然后用构造器注入
 * @author kuangcp
 * @date 18-5-2  上午12:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefenseScheduleDaoTest {

    @Autowired
    private DefenseScheduleDao defenseScheduleDao;

    @Test
    public void TestQuery(){
        DefenseSchedule defenseSchedule = defenseScheduleDao.selectByStudentId(1001L);
        System.out.println(defenseSchedule.toString());
    }
}