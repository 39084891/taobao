package cn.qinguu.controller;

import cn.qinguu.common.pojo.TaoBaoResult;
import cn.qinguu.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SearchItemController
 * @Description TOOD
 * @Author cy
 * @Date 2019/4/1019:18
 * @Version 1.0
 **/
@Controller
public class SearchItemController {
    @Autowired
    SearchItemService searchItemService;

    @RequestMapping("/index/importall")
    @ResponseBody
    public TaoBaoResult importAllIndex() throws Exception {
        return searchItemService.importAllIndex();
    }
}
