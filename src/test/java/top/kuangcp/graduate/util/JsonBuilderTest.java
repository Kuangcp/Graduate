package top.kuangcp.graduate.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-22  下午4:23
 */
public class JsonBuilderTest {

    @Test
    public void testBuildResult() {
        String result = JsonBuilder.buildResult(null, null, null);
        System.out.println(result);
        Assert.assertEquals("{\"code\":\"0\",\"msg\":\" \",\"data\":\" \"}", result);

        List<String> lists = new ArrayList<>();
        lists.add("1");
        lists.add("2");
        result = JsonBuilder.buildResult(null, null, lists);
        System.out.println(result);
        Assert.assertEquals("{\"code\":\"0\",\"msg\":\" \",\"data\":[\"1\",\"2\"]}", result);

    }

    @Test
    public void testBuildTableResult() {
        String result = JsonBuilder.buildTableResult(0, "msg", 0, null);
        System.out.println(result);
        Assert.assertEquals("{\"code\":\"0\",\"msg\":\"msg\",\"data\":\" \",\"count\":\"0\"}", result);
    }
}