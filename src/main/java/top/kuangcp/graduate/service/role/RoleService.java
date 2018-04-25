package top.kuangcp.graduate.service.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.dao.StudentDao;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午9:44
 */
@Service
public class RoleService {

    @Autowired
    StudentDao studentDao;


//    private MythORM orm = MythORM.build(DBType.POSTGRESQL);
//    private DBConfig config = new DBConfig();
//    {
//        config.setDatabase("graduate").setHost("127.0.0.1").setPort(5432)
//                .setUsername("postgres").setPassword("jiushi");
//    }
    private Logger log = LoggerFactory.getLogger(RoleService.class);

//    public boolean loginByStudent(String username, String password) {
//        List<Student> studentList = orm.query("select * from student where username = '"+username+"'", Student.class, config);
//        if(studentList.size() != 0){
//            return password.equals(studentList.get(0).getPassword());
//        }
//        return false;
//    }

    public boolean loginByStudent(String username, String password) {
        return password.equals(studentDao.findByUsername(username).getPassword());
    }

}
