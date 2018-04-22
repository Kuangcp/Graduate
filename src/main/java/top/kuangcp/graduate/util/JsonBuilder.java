package top.kuangcp.graduate.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.kuangcp.graduate.domain.vo.ResultVO;
import top.kuangcp.graduate.domain.vo.TableResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by https://github.com/kuangcp
 * 比起Spring的Bean 静态工具更简单
 * 自定义规则：
 *  整型 为空就置0
 *  字符串为空就置为 "null" 字符串
 * @author kuangcp
 * @date 18-4-22  下午3:38
 */
public class JsonBuilder {
    private static Logger log = LoggerFactory.getLogger(JsonBuilder.class);
    private static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
    }
    public static String buildResult(Integer code, String msg, Object data){
        code = checkInteger(code);
        try {
            return cleanNull(mapper.writeValueAsString(new ResultVO(code, msg, data)));
        } catch (JsonProcessingException e) {
            log.error(" Create JSON Failed  ", e);
            return "{\"code\":\"1\", \"msg\":\"error\", \"data\":\" \"}";
        }
    }
    public static String buildTableResult(Integer code, String msg, Integer count, Object data){
        code = checkInteger(code);
        count = checkInteger(count);
        try {
            return cleanNull(mapper.writeValueAsString(new TableResultVO(code, msg, count, data)));
        } catch (JsonProcessingException e) {
            log.error(" Create JSON Failed  ", e);
            return "{\"code\":\"1\", \"msg\":\"error\", \"data\":\" \"}";
        }
    }
    private static Integer checkInteger(Integer target){
        if(target == null){
            return 0;
        }else{
            return target;
        }
    }
    private static String cleanNull(String target){
        return target.replace("[^\"]null", "\"null\"");
    }
}
