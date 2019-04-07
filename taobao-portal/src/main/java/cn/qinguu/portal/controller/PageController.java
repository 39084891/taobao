package cn.qinguu.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @Author cy
 * @Description 展示首页
 * @Date 2019/4/7 16:30
 * @Param
 * @return
 **/
@Controller
public class PageController {
    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }
}
