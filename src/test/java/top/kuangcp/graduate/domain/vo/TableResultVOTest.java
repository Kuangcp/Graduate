package top.kuangcp.graduate.domain.vo;

import org.junit.Test;
import top.kuangcp.graduate.domain.vo.result.TableResultVO;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午4:31
 */
public class TableResultVOTest {
    TableResultVO tableResultVO = new TableResultVO();

    @Test
    public void testBuild(){
        tableResultVO.setCount(1).setCode(0).setMsg("msg").setData("ui");
        System.out.println(tableResultVO.toString());
    }
}