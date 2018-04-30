package top.kuangcp.graduate.service.verify;

import top.kuangcp.graduate.util.JsonBuilder;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-30  上午10:12
 */
public class CheckPage {

    /**
     * 校验分页参数的合理性
     * @return JSON
     */
    public static String checkPageNum(int page, int limit){
        if (page < 0 || limit < 0){
            return JsonBuilder.buildResult(1, "page error", " ");
        }else{
            return null;
        }
    }
}
