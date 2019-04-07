package cn.qinguu.controller;

import cn.qinguu.pojo.TbItemCat;
import cn.qinguu.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;


    @RequestMapping(value = "/list")
    @ResponseBody
    public List<TbItemCat> getCatListByParentId(@RequestParam(value = "id",defaultValue = "0") Integer parentId){
        return itemCatService.getCatListByParentId(parentId);
    }
}
