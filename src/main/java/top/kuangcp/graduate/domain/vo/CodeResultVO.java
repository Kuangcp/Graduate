package top.kuangcp.graduate.domain.vo;

import lombok.AllArgsConstructor;

/**
 * Created by https://github.com/kuangcp
 * 只返回错误码
 * @author kuangcp
 * @date 18-4-30  下午8:53
 */
@AllArgsConstructor
public class CodeResultVO implements Result{
    Integer code;

    public CodeResultVO(){
        System.out.println("实例化CodeResultVO");
    }

    public Integer getCode() {
        return code;
    }

    public CodeResultVO setCode(Integer code) {
        this.code = code;
        return this;
    }
}
