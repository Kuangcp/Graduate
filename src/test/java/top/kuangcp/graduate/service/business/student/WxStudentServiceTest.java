package top.kuangcp.graduate.service.business.student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  上午12:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxStudentServiceTest {

    @Autowired
    WxStudentService wxStudentService;

    @Test
    public void testQueryPlace() throws Exception {
        String result = wxStudentService.queryPlace(1L);
        System.out.println(result);
    }
}