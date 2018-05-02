package top.kuangcp.graduate.domain.vo.result;

/**
 * Created by https://github.com/kuangcp
 * 表格数据的VO对象
 * @author kuangcp
 * @date 18-4-22  下午4:15
 */
public class TableResultVO extends ResultVO{
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public TableResultVO setCount(Integer count) {
        this.count = count;
        return this;
    }


    public TableResultVO(){
        System.out.println("实例化TableResultVO");
    }
    public TableResultVO(Integer code, String msg, Integer count, Object data) {
        super(code, msg, data);
        this.count = count;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "code=" + super.getCode() +
                ", msg='" + super.getMsg() + '\'' +
                ", data=" + super.getData() +
                ", count=" + count +
                '}';
    }
}
