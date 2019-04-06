package cn.qinguu.controller;

import cn.qinguu.common.pojo.EasyUIDataGridResult;
import cn.qinguu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    //url:/item/list
    //method:get
    //参数：page,rows
    //返回值：json
    @RequestMapping(value = "/item/list",method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page ,Integer rows){
        return itemService.getItemList(page,rows);
    }
}
