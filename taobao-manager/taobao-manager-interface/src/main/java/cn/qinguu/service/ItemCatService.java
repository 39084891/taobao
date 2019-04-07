package cn.qinguu.service;

import java.util.List;

/**
 * 分类相关的service
 */
public interface ItemCatService {
    public List getCatListByParentId(Integer parentId);
}
