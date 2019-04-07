package cn.qinguu.service.impl;

import cn.qinguu.common.pojo.EasyUIDataGridResult;
import cn.qinguu.common.pojo.TaoBaoResult;
import cn.qinguu.common.utils.IDUtils;
import cn.qinguu.mapper.TbItemDescMapper;
import cn.qinguu.mapper.TbItemMapper;
import cn.qinguu.pojo.TbItem;
import cn.qinguu.pojo.TbItemDesc;
import cn.qinguu.pojo.TbItemExample;
import cn.qinguu.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    TbItemMapper tbItemMapper;
    @Autowired
    TbItemDescMapper tbItemDescMapper;

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        if (page == null) page = 1;
        if (rows == null) rows = 30;
        PageHelper.startPage(page, rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> items = tbItemMapper.selectByExample(example);
        PageInfo<TbItem> info = new PageInfo<>(items);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal((int) info.getTotal());
        result.setRows(info.getList());
        return result;
    }

    //保存商品方法
    @Override
    public TaoBaoResult saveItem(TbItem item, String desc) {
        //取当前系统时间
        Date date = new Date();

        //设置商品创建时间
        item.setCreated(date);
        //设置商品更新时间
        item.setUpdated(date);
        //设置商品状态
        item.setStatus((byte) 1);
        //设置商品id
        item.setId(IDUtils.genItemId());
        //插入商品
        tbItemMapper.insert(item);


        TbItemDesc tbItemDesc = new TbItemDesc();
        //设置商品id
        tbItemDesc.setItemId(item.getId());
        //设置商品描述
        tbItemDesc.setItemDesc(desc);
        //设置描述创建时间
        tbItemDesc.setCreated(date);
        //设置描述更新时间
        tbItemDesc.setUpdated(date);
        //插入描述
        tbItemDescMapper.insert(tbItemDesc);

        return TaoBaoResult.ok();
    }

    //删除商品方法
    @Override
    public TaoBaoResult deleteItem(Long[] ids) {
        //遍历数组ids删除商品
        for (Long id :
                ids) {
            tbItemMapper.deleteByPrimaryKey(id);
        }
        return TaoBaoResult.ok();
    }
}
