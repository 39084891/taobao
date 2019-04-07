package cn.qinguu.content.service.impl;

import cn.qinguu.common.pojo.TaoBaoResult;
import cn.qinguu.content.service.ContentCatService;
import cn.qinguu.mapper.TbContentCategoryMapper;
import cn.qinguu.pojo.TbContentCategory;
import cn.qinguu.pojo.TbContentCategoryExample;
import cn.qinguu.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: ContentCatServiceImpl
 * @Description 内容分类服务
 * @Author cy
 * @Date 2019/4/717:23
 * @Version 1.0
 **/
@Service
public class ContentCatServiceImpl implements ContentCatService {
    @Autowired
    public TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List getContentCatByParentId(Integer parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return tbContentCategoryMapper.selectByExample(example);
    }

    /*
     * @Author cy
     * @Description 增加节点
     * @Date 2019/4/7 20:56
     * @Param [parentId, name]
     * @return cn.qinguu.common.pojo.TaoBaoResult
     **/
    @Override
    public TaoBaoResult addContentCatByParentId(Long parentId, String name) {
        Date date = new Date();
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setStatus(1);
        tbContentCategory.setName(name);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setUpdated(date);
        tbContentCategory.setCreated(date);
        tbContentCategoryMapper.insert(tbContentCategory);
        //给父节点的isParent属性改成true
        TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if(!parent.getIsParent()){
            parent.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(parent);
        }
        return TaoBaoResult.ok(tbContentCategory);
    }
    /*
     * @Author cy
     * @Description 删除节点
     * @Date 2019/4/7 20:56
     * @Param [id]
     * @return cn.qinguu.common.pojo.TaoBaoResult
     **/
    @Override
    public TaoBaoResult deleteContentCatByPrimaryKey(Long id) {
        //查询准备删除的分类是否是父节点
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        if(tbContentCategory.getIsParent()==true){
            return TaoBaoResult.forbidden();
        }
        tbContentCategoryMapper.deleteByPrimaryKey(id);
        //获取需要分类的父节点id
        Long parentId = tbContentCategory.getParentId();
        //生成查询条件：父节点的子节点数
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(Math.toIntExact(parentId));
        //查询父节点的子节点数
        int count = tbContentCategoryMapper.countByExample(tbContentCategoryExample);
        //如果父节点的子节点个数为0，则设置父节点的isParent为false
        if(count == 0){
            TbContentCategory parentContentCat = new TbContentCategory();
            parentContentCat.setIsParent(false);
            parentContentCat.setId(parentId);
            tbContentCategoryMapper.updateByPrimaryKeySelective(parentContentCat);
        }
        return  TaoBaoResult.ok();

    }

    /*
     * @Author cy
     * @Description 更新节点
     * @Date 2019/4/7 20:57
     * @Param [id, name]
     * @return cn.qinguu.common.pojo.TaoBaoResult
     **/
    @Override
    public TaoBaoResult updateContentCatByprimaryKey(Long id, String name) {
        //查找需要更新的TbContentCategory
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setName(name);
        //更新TbContentCategory
        tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);

        return TaoBaoResult.ok();
    }


}
