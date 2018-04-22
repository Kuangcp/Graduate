package top.kuangcp.graduate.service.security;

/**
 * Created by https://github.com/kuangcp
 * 认证基本要素 用户名密码和角色
 * @author kuangcp
 * @date 18-3-28  下午3:45
 */
public class AccountCredentials {
    private String username;
    private String password;
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AccountCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}