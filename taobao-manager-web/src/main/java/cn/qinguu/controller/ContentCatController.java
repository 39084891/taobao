package cn.qinguu.controller;

import cn.qinguu.common.pojo.TaoBaoResult;
import cn.qinguu.content.service.ContentCatService;
import cn.qinguu.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: ContentCatController
 * @Description 内容分类控制器
 * @Author cy
 * @Date 2019/4/718:08
 * @Version 1.0
 **/
@Controller
@RequestMapping("/content/category")
public class ContentCatController {
    @Autowired
    private ContentCatService contentCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<TbContentCategory> getContentCatByParentId(@RequestParam(value = "id", defaultValue = "0") Integer cid) {
        return contentCatService.getContentCatByParentId(cid);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaoBaoResult deleteContentCatByPrimaryKey(Long id) {
        return contentCatService.deleteContentCatByPrimaryKey(id);
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaoBaoResult addContentCatByParentId(Long parentId, String name) {
        return contentCatService.addContentCatByParentId(parentId, name);
    }

    @RequestMapping("/update")
    @ResponseBody
    public  TaoBaoResult updateContentCatByprimaryKey(Long id, String name){
        return contentCatService.updateContentCatByprimaryKey(id,name);
    }

}
