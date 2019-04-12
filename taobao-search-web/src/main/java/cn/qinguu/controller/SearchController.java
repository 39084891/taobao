package cn.qinguu.controller;

import cn.qinguu.common.pojo.SearchResult;
import cn.qinguu.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: SearchController
 * @Description TOOD
 * @Author cy
 * @Date 2019/4/1021:37
 * @Version 1.0
 **/
@Controller
public class SearchController {
    @Autowired
    private SearchItemService searchService;
    @Value("${ITEM_ROWS}")
    private Integer ITEM_ROWS;


    @RequestMapping("/search")
    public String search(@RequestParam("q")String queryString,
                         @RequestParam(defaultValue="1")Integer page, Model model) throws Exception {
        //处理乱码
        queryString = new String(queryString.getBytes("iso-8859-1"),"utf-8");

        SearchResult result = searchService.search(queryString, page, ITEM_ROWS);

        //传递给页面
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", result.getPageCount());
        model.addAttribute("itemList", result.getItemList());
        model.addAttribute("page", page);
        //返回逻辑视图
        return "search";

    }

}
