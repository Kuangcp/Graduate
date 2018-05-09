package top.kuangcp.graduate.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.kuangcp.graduate.domain.Topic;
import top.kuangcp.graduate.service.crud.CrudTopicService;

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
    private final CrudTopicService topicService;

    @Autowired
    public RestTopicController(CrudTopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping(value = "/get/{id}", produces = "application/json;charset=UTF-8")
    public String getOne(@PathVariable("id") Long topicId){
        return topicService.getOne(topicId);
    }
    @GetMapping(value = "/list/{id}", produces = "application/json;charset=UTF-8")
    public String listByTeacher(@PathVariable("id") Long teacherId){
        return topicService.listByTeacher(teacherId);

    }
    @GetMapping(value = "/listAll", produces = "application/json;charset=UTF-8")
    public String listAll() {
        return topicService.listAll();
    }

    // 所有未经审核
    @GetMapping(value = "/listValidate", produces = "application/json;charset=UTF-8")
    public String listAllNoValidate() {
        return topicService.listAllNoValidate();
    }


    @GetMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public String findByName(String name) {
        return topicService.findByName(name);
    }
    // POST 一定采用JSON交互,
    @PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
    public String save(@RequestBody Topic topic){
        return topicService.save(topic);
    }
}
