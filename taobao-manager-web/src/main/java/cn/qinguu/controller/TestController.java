package cn.qinguu.controller;

import cn.qinguu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @Autowired
    private TestService service;

    @ResponseBody
    @RequestMapping("test/queryNow")
    public String queryNow(){

        return service.queryNow();
    }
}
