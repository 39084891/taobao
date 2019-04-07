package cn.qinguu.content.service;

import cn.qinguu.common.pojo.TaoBaoResult;

import java.util.List;

public interface ContentCatService {
    public List getContentCatByParentId(Integer parentId);
    public TaoBaoResult addContentCatByParentId(Long parentId,String name);
    public TaoBaoResult deleteContentCatByPrimaryKey(Long id);
    public TaoBaoResult updateContentCatByprimaryKey(Long id,String name);
}
