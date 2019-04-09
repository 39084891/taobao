package cn.qinguu.portal.controller;

import cn.qinguu.content.service.ContentService;
import cn.qinguu.pojo.TbContent;
import cn.qinguu.portal.pojo.Ad1Node;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author cy
 * @Description 展示首页
 * @Date 2019/4/7 16:30
 * @Param
 * @return
 **/
@Controller
public class PageController {
    @Autowired
    private ContentService service;
    @Value("${AD1_HEIGTH}")
    private String AD1_HEIGTH;
    @Value("${AD1_WIDTH}")
    private String AD1_WIDTH;
    @Value("${AD1_HEIGTH_B}")
    private String AD1_HEIGTH_B;
    @Value("${AD1_WIDTH_B}")
    private String AD1_WIDTH_B;
    @Value("${AD1_CAT_ID}")
    private Long AD1_CAT_ID;


    @RequestMapping("/index")
    public String showIndex(Model model){
//        封装为json的对象
        List<Ad1Node> ad1NodeList = new ArrayList<Ad1Node>();
//        未封装的对象
        List<TbContent> ad1ContentList = service.getAd1Content(AD1_CAT_ID);
        for (TbContent ad1Content:
             ad1ContentList) {
            Ad1Node ad1Node = new Ad1Node();
            ad1Node.setAlt(ad1Content.getTitle());
            ad1Node.setHeigth(AD1_HEIGTH);
            ad1Node.setHeigthB(AD1_HEIGTH_B);
            ad1Node.setHref(ad1Content.getUrl());
            ad1Node.setSrc(ad1Content.getPic());
            ad1Node.setSrcB(ad1Content.getPic2());
            ad1Node.setWidth(AD1_WIDTH);
            ad1Node.setWidthB(AD1_WIDTH_B);
            ad1NodeList.add(ad1Node);
        }
        String json = JSON.toJSONString(ad1NodeList);
        model.addAttribute("content_ad1",json);
        return "index";
    }
}
