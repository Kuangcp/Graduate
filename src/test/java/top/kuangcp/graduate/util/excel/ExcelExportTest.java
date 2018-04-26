package top.kuangcp.graduate.util.excel;

import com.kuangcp.mythpoi.excel.ExcelExport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.kuangcp.graduate.dao.role.StudentDao;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-26  下午8:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelExportTest {

    @Autowired
    StudentDao studentDao;

    @Test
    public void testStudent() {
        ExcelExport.exportExcel("/home/kcp/test/employee.xls", studentDao.findAll());
    }


}
