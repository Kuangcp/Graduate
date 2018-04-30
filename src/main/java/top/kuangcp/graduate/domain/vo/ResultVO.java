package top.kuangcp.graduate.domain.vo;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  上午10:14
 */
public class ResultVO implements Result{
    private Integer code;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }
    public ResultVO setCode(Integer code) {
        this.code = code;
        return this;
    }
    public String getMsg() {
        return msg;
    }

    public ResultVO setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultVO setData(Object data) {
        this.data = data;
        return this;
    }

    public ResultVO(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO(){
        System.out.println("实例化ResultVO");
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
