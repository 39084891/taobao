package cn.qinguu.service;

import cn.qinguu.common.pojo.EasyUIDataGridResult;
import cn.qinguu.common.pojo.TaoBaoResult;
import cn.qinguu.pojo.TbItem;


/**
 * 商品相关的service
 */
public interface ItemService {

    public EasyUIDataGridResult getItemList(Integer page,Integer rows);
    public TaoBaoResult saveItem(TbItem item , String desc);
    public TaoBaoResult deleteItem(Long ids[]);
}
