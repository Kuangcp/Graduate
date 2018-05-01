package top.kuangcp.graduate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kuangcp.graduate.service.curd.CurdTopicService;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-1  下午8:35
 */
@RestController
@RequestMapping("/rest/topic")
public class RestTopicController {

    // TODO 为什么IDEA建议使用构造器注入
    private final CurdTopicService topicService;

    @Autowired
    public RestTopicController(CurdTopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping(value = "/listAll", produces = "application/json;charset=UTF-8")
    public String listAll() {
        return topicService.listAll();
    }
}
