package top.kuangcp.graduate.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import top.kuangcp.graduate.config.custom.CoreConfig;
import top.kuangcp.graduate.domain.role.Role;
import top.kuangcp.graduate.domain.role.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午4:23
 */
@Ignore
public class JsonBuilderTest {

    @Test
    public void testBuildResult() {
        // Null
        String result = JsonBuilder.buildResult(null, null, null);
        System.out.println(result);
        Assert.assertEquals("{\"code\":\"0\",\"msg\":\"null\",\"data\":\"null\"}", result);

        // List
        List<String> lists = new ArrayList<>();
        lists.add("1");
        lists.add("2");
        result = JsonBuilder.buildResult(null, null, lists);
        System.out.println(result);
        Assert.assertEquals("{\"code\":\"0\",\"msg\":\"null\",\"data\":[\"1\",\"2\"]}", result);

        // Role
        List<Student> studentList = new ArrayList<>();
        Student student = new Student();
        student.setUsername("sdf");
        student.setPassword("sdf");
        student.setStudentId(12L);
        studentList.add(student);
        result = JsonBuilder.buildResult(null, null, studentList);
        System.out.println(result);
    }

    @Test
    public void testBuildTableResult() {
        String result = JsonBuilder.buildTableResult(0, "msg", 0, null);
        System.out.println(result);
        Assert.assertEquals("{\"code\":\"0\",\"msg\":\"msg\",\"data\":\" \",\"count\":\"0\"}", result);
    }

    /**
     * 原本想通过统一实现接口， 然后统一解析， 发现这个JSON解析还是很严格的， 这种方案行不通了
     * @throws IOException
     */
    @Test
    public void testRead() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Student student = new Student();
        student.setUsername("sdf");
        student.setPassword("sdf");
        student.setStudentId(12L);
        Role result = mapper.readValue(mapper.writeValueAsString(student), Role.class);
        System.out.println(result.toString());
    }

    @Test
    public void testSplit(){
        String [] list = "3N4N5N".split(CoreConfig.DELIMITER_NUM);
        for(String a : list){
            System.out.println("<<"+a);
        }
    }
}