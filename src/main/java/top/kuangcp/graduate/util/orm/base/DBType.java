package top.kuangcp.graduate.util.orm.base;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-21  下午7:08
 */
public enum DBType {
    MYSQL("mysql", "com.mysql.jdbc.Driver"), POSTGRESQL("postgresql", "org.postgresql.Driver");

    private String type;
    private String driver;

    DBType(String type, String driver) {
        this.type = type;
        this.driver = driver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
