package top.kuangcp.graduate.domain.role;

import com.kuangcp.mythpoi.excel.base.ExcelTransform;
import com.kuangcp.mythpoi.utils.base.ExcelConfig;
import com.kuangcp.mythpoi.utils.base.ExcelSheet;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午5:18
 */
@Entity
@ExcelSheet(importTitle = "教师表", exportTitle = "教师表")
public class Teacher implements Serializable, ExcelTransform,Role {
    @Id
    @GeneratedValue
    @ExcelConfig("教师工号")
    private long teacherId;
    @ExcelConfig("姓名")
    private String username;
    @ExcelConfig("密码")
    private String password;
    @ExcelConfig("邮箱")
    private String email;
    @ExcelConfig("微信id")
    private String weixinId;

    //出题任务数
    private short amount;

    //是否为秘书 1/0 默认 0
    private short secretary;


    public Teacher(long teacherId){
        this.teacherId = teacherId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public short getAmount() {
        return amount;
    }

    public void setAmount(short amount) {
        this.amount = amount;
    }

    public short getSecretary() {
        return secretary;
    }

    public void setSecretary(short secretary) {
        this.secretary = secretary;
    }
}

