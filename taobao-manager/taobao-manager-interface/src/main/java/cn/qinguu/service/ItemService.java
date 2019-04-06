package cn.qinguu.service;

import cn.qinguu.common.pojo.EasyUIDataGridResult;

/**
 * 商品相关的service
 */
public interface ItemService {

    public EasyUIDataGridResult getItemList(Integer page,Integer rows);

}
