package cn.qinguu.service.impl;

import cn.qinguu.common.pojo.EasyUIDataGridResult;
import cn.qinguu.mapper.TbItemMapper;
import cn.qinguu.pojo.TbItem;
import cn.qinguu.pojo.TbItemExample;
import cn.qinguu.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    TbItemMapper mapper;
    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        if(page==null)page=1;
        if(rows==null)rows=30;
        PageHelper.startPage(page,rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> items = mapper.selectByExample(example);
        PageInfo<TbItem> info = new PageInfo<>(items);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal((int) info.getTotal());
        result.setRows(info.getList());
        return result;
    }
}
