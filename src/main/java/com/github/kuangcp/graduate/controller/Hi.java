package com.github.kuangcp.graduate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-3-27  上午10:57
 */
@RestController
@RequestMapping("/hi")
public class Hi {

    @GetMapping()
    public String hi(){
        return "11111111";
    }


}
