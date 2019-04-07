package cn.qinguu.service.impl;

import cn.qinguu.mapper.TbItemCatMapper;
import cn.qinguu.pojo.TbItemCat;
import cn.qinguu.pojo.TbItemCatExample;
import cn.qinguu.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper mapper;
    @Override
    public List getCatListByParentId(Integer parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = mapper.selectByExample(example);
        return list;
    }
}
