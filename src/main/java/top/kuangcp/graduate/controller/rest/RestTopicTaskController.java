package top.kuangcp.graduate.controller.rest;

import org.springframework.web.bind.annotation.*;
import top.kuangcp.graduate.domain.TopicTask;
import top.kuangcp.graduate.service.crud.CrudTopicTaskService;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-8  下午7:14
 */
@RestController
@RequestMapping("/rest/topicTask")
public class RestTopicTaskController {
private final CrudTopicTaskService topicTaskService;

    public RestTopicTaskController(CrudTopicTaskService topicTaskService) {
        this.topicTaskService = topicTaskService;
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public String getOne(@PathVariable("id") Long id) {
        return topicTaskService.getOne(id);
    }

    @GetMapping(value = "/listAll", produces = "application/json;charset=UTF-8")
    public String listAll(int page, int limit) {
        return topicTaskService.listAll(page - 1, limit);
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public String save(@RequestBody TopicTask task) {
        return topicTaskService.saveOne(task);
    }
}
