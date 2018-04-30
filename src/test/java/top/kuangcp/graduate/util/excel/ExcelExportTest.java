package top.kuangcp.graduate.util.excel;

import com.kuangcp.mythpoi.excel.ExcelExport;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.kuangcp.graduate.dao.CollegeDao;
import top.kuangcp.graduate.dao.role.StudentDao;
import top.kuangcp.graduate.domain.College;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    CollegeDao collegeDao;

    @Test
    @Ignore
    public void testStudent() {
        ExcelExport.exportExcel("/home/kcp/test/employee.xls", studentDao.findAll());
    }

    @Test
    public void testCollege(){
        List<College> colleges = new ArrayList<>();
        collegeDao.findAll().forEach(colleges::add);
        ExcelExport.exportExcel("/home/kcp/test/employee.xls", colleges);
    }
}
