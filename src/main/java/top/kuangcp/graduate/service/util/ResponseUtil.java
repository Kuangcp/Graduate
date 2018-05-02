package top.kuangcp.graduate.service.util;

import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.util.JsonBuilder;

import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-2  上午8:15
 */
public class ResponseUtil {
    public static String handlerList(List list){
        if(list == null || list.size() == 0){
            return JsonBuilder.buildCodeResult(ResponseCode.POJO_NOT_FOUND);
        }else{
            return JsonBuilder.buildSuccessResult("", list);
        }
    }
}
