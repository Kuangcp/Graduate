package top.kuangcp.graduate.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import top.kuangcp.graduate.config.custom.ResponseCode;
import top.kuangcp.graduate.domain.vo.CodeResultVO;
import top.kuangcp.graduate.domain.vo.Result;
import top.kuangcp.graduate.domain.vo.ResultVO;
import top.kuangcp.graduate.domain.vo.TableResultVO;

/**
 * Created by https://github.com/kuangcp
 * 比起Spring的Bean 静态工具更简单
 * 自定义规则：
 * 所有数值不能为空, 字符串也不能为null, 可以为空串,有时必须要空串
 *
 * @author kuangcp
 * @date 18-4-22  下午3:38
 */
@Log4j2
public class JsonBuilder {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ResultVO resultVO = new ResultVO();
    private static final TableResultVO tableResultVO = new TableResultVO();
    private static final CodeResultVO codeResult = new CodeResultVO();

    static {
        mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
    }

    public static String buildSuccessResult(String msg, Object data) {
        return buildResult(0, msg, data);
    }

    public static String buildResult(Integer code, String msg, Object data) {
        return build(resultVO.setCode(code).setMsg(msg).setData(data));
    }

    public static String buildSuccessTableResult(String msg, Integer count, Object data) {
        return buildTableResult(0, msg, count, data);
    }

    public static String buildTableResult(Integer code, String msg, Integer count, Object data) {
        return build(tableResultVO.setCount(count).setCode(code).setMsg(msg).setData(data));
    }

    public static String buildSuccessCodeResult(){
        return build(codeResult.setCode(0));
    }
    public static String buildCodeResult(Integer code){
        return build(codeResult.setCode(code));
    }


    /**
     * 只根据接口, 将实例化的对象转成JSON
     * @param result result接口的实现对象
     * @return JSON
     */
    private static String build(Result result) {
        try {
            return cleanNull(mapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            log.error(" Create JSON Failed  ", e);
            return "{\"code\":\""+ResponseCode.SERIALIZATION_EROOR +"\"}";
        }
    }

    /**
     * 将null 更换为 "null"
     * @param target 目标JSON字符串
     * @return 修正后的JSON字符串
     */
    private static String cleanNull(String target) {
        return target.replace(":null", ":\"null\"");
    }
}
