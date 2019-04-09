package cn.qinguu.content.service;


import cn.qinguu.common.pojo.EasyUIDataGridResult;
import cn.qinguu.common.pojo.TaoBaoResult;
import cn.qinguu.pojo.TbContent;

import java.util.List;

public interface ContentService {
    public EasyUIDataGridResult getContentList(Long cid,Integer page,Integer rows);
    public TaoBaoResult saveContent(TbContent tbContent);
    public TaoBaoResult updateContent(TbContent tbContent);
    public TaoBaoResult deleteContent(Long[] ids);
    public List<TbContent> getAd1Content(Long cid);
}
