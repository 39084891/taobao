package cn.qinguu.controller;

import cn.qinguu.common.pojo.EasyUIDataGridResult;
import cn.qinguu.common.pojo.TaoBaoResult;
import cn.qinguu.content.service.ContentService;
import cn.qinguu.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: ContentController
 * @Description 内容控制器
 * @Author cy
 * @Date 2019/4/721:36
 * @Version 1.0
 **/
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(Long categoryId,Integer page,Integer rows){
        return contentService.getContentList(categoryId,page,rows);
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaoBaoResult saveContent(TbContent tbContent){
        return contentService.saveContent(tbContent);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public TaoBaoResult updateContent(TbContent tbContent){
        return contentService.updateContent(tbContent);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaoBaoResult deleteContent(Long[] ids){
        return contentService.deleteContent(ids);
    }
}
