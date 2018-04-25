package top.kuangcp.graduate.util.orm;


import com.kuangcp.mythpoi.utils.config.YamlUtil;

import java.util.Optional;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-21  下午6:19
 */
public class DBConfig {
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private String driver;


    /**
     * 从yml加载配置,
     *
     * @return Optional<DBConfig> 可能为空
     */
    public static Optional<DBConfig> buildByYaml() {
        return Optional.ofNullable(YamlUtil.readFile(DBConfig.class, "src/main/resources/mysql.yml"));
    }

    public String getHost() {
        return host;
    }

    public DBConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public DBConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public DBConfig setDatabase(String database) {
        this.database = database;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DBConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DBConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDriver() {
        return driver;
    }

    public DBConfig setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    @Override
    public String toString() {
        return "DBConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", database='" + database + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driver='" + driver + '\'' +
                '}';
    }
}
