package top.kuangcp.graduate.domain.role;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import com.kuangcp.mythpoi.utils.base.ExcelConfig;
import com.kuangcp.mythpoi.utils.base.ExcelSheet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-26  上午8:41
 */
@Entity
@ExcelSheet(importTitle = "管理员表", exportTitle = "管理员表")
public class Admin implements Serializable, ExcelTransform {
    @Id
    @GeneratedValue
    private long adminId;

    @ExcelConfig("用户名")
    private String username;
    @ExcelConfig("密码")
    private String password;

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

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
}