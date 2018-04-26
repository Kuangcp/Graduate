package top.kuangcp.graduate.util.excel;

import com.kuangcp.mythpoi.excel.ExcelImport;
import org.junit.Test;
import top.kuangcp.graduate.domain.role.Student;

import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-26  下午8:38
 */
public class ExcelImportTest {

    @Test
    public void testImportStudent(){
        List<Student> result = ExcelImport.importExcel("/home/kcp/test/employee.xls", Student.class);
        result.forEach(item -> System.out.println(item.toString()));
    }
}
