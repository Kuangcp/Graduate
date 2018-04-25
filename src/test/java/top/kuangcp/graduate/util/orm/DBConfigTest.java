package top.kuangcp.graduate.util.orm;

import com.kuangcp.mythpoi.utils.config.YamlUtil;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 * TODO 失败
 * @author kuangcp
 * @date 18-4-25  上午9:44
 */
public class DBConfigTest {
    DBConfig dBConfig = new DBConfig();


    @Test
    public void testCreateYaml(){
        dBConfig.setDatabase("graduate").setHost("127.0.0.1").setPort(5432).setUsername("postgres").setPassword("jiushi");
        System.out.println(dBConfig.toString());
        YamlUtil.createFile(dBConfig, "mysql.yml");
    }
    @Test
    public void testBuildByYaml() throws Exception {
        Optional<DBConfig> result = DBConfig.buildByYaml();
        System.out.println(result.toString());
    }

}