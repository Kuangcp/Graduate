package top.kuangcp.graduate.service.security;

import lombok.Data;

/**
 * Created by https://github.com/kuangcp
 * 认证基本要素 用户名密码和角色
 * @author kuangcp
 * @date 18-3-28  下午3:45
 */
@Data
public class AccountCredentials {
    private String username;
    private String password;
    private String role;
}