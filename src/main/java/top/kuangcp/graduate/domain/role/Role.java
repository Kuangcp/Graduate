package top.kuangcp.graduate.domain.role;

/**
 * Created by https://github.com/kuangcp
 * 接口 登录的用户
 * @author kuangcp
 * @date 18-4-22  下午5:18
 */
public interface Role {
    /**
     *用户名
     */
    String getUsername();

    /**
     * 口令
     */
    String getPassword();
}
