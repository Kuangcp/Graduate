package top.kuangcp.graduate.controller;

import top.kuangcp.graduate.util.JsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-3-28  下午12:29
 */

@RestController
class TestJwtController {
    /**
     *  路由映射到/users 并且限制请求类型仅仅为json
     * @return
     */
    @RequestMapping(value = "/users", produces = "application/json;charset=UTF-8")
    public String usersList() {
        ArrayList<String> users = new ArrayList<String>() {{
            add("freewolf");
            add("tom");
            add("jerry");
        }};
        return JsonBuilder.buildResult(0, "", users);
    }

    @RequestMapping(value = "/hello", produces = "application/json;charset=UTF-8")
    public String hello() {
        ArrayList<String> users = new ArrayList<String>() {{
            add("hello");
        }};
        return JsonBuilder.buildResult(0, "", users);
    }

    @RequestMapping(value = "/world", produces = "application/json;charset=UTF-8")
    public String world() {
        ArrayList<String> users = new ArrayList<String>() {{
            add("world");
        }};
        return JsonBuilder.buildResult(0, "", users);
    }

}