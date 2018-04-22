package com.github.kuangcp.graduate.domain.vo;

import lombok.Data;
import lombok.Getter;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  上午10:14
 */
@Data
@Getter
public class ResultVO {
    private Integer code;
    private String msg;
    private Object data;

}
