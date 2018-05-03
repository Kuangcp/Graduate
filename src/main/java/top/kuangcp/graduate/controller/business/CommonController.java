package top.kuangcp.graduate.controller.business;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kuangcp.graduate.util.JsonBuilder;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-5-3  上午9:15
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @GetMapping(value = "/ping", produces = "application/json;charset=UTF-8")
    public String ping(){
        return JsonBuilder.buildSuccessCodeResult();
    }
}
