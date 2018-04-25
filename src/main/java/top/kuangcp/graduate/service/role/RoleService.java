package top.kuangcp.graduate.service.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.kuangcp.graduate.domain.role.Student;
import top.kuangcp.graduate.util.orm.DBConfig;
import top.kuangcp.graduate.util.orm.MythORM;
import top.kuangcp.graduate.util.orm.base.DBType;

import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午9:44
 */
public class RoleService {

    private MythORM orm = MythORM.build(DBType.POSTGRESQL);
    private DBConfig config = new DBConfig();
    {
        config.setDatabase("graduate").setHost("127.0.0.1").setPort(5432)
                .setUsername("postgres").setPassword("jiushi");
    }
    private Logger log = LoggerFactory.getLogger(RoleService.class);

    public boolean loginByStudent(String username, String password) {
        List<Student> studentList = orm.query("select * from student where username = '"+username+"'", Student.class, config);
        if(studentList.size() != 0){
            return password.equals(studentList.get(0).getPassword());
        }
        return false;
    }

}
