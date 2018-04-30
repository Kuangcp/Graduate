package top.kuangcp.graduate.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by https://github.com/kuangcp
 * 只返回错误码
 * @author kuangcp
 * @date 18-4-30  下午8:53
 */
@Data
@AllArgsConstructor
public class CodeResultVO {
    Integer code;

    public CodeResultVO(){
        System.out.println("实例化CodeResultVO");
    }
}
