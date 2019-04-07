package cn.qinguu.controller;

import cn.qinguu.common.pojo.EasyUIDataGridResult;
import cn.qinguu.common.pojo.TaoBaoResult;
import cn.qinguu.pojo.TbItem;
import cn.qinguu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    //url:/item/list
    //method:get
    //参数：page,rows
    //返回值：json
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page ,Integer rows){
        return itemService.getItemList(page,rows);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public TaoBaoResult saveItem(TbItem item ,String desc){
        return  itemService.saveItem(item,desc);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public TaoBaoResult deleteItem(Long[] ids) {
        return itemService.deleteItem(ids);
    }
}
