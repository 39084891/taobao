package cn.qinguu.content.service.impl;

import cn.qinguu.common.pojo.EasyUIDataGridResult;
import cn.qinguu.common.pojo.TaoBaoResult;
import cn.qinguu.common.utils.JsonUtils;
import cn.qinguu.content.jedis.JedisClientCluster;
import cn.qinguu.content.service.ContentService;
import cn.qinguu.mapper.TbContentMapper;
import cn.qinguu.pojo.TbContent;
import cn.qinguu.pojo.TbContentExample;
import cn.qinguu.pojo.TbItem;
import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: ContentServiceImp
 * @Description TOOD
 * @Author cy
 * @Date 2019/4/721:24
 * @Version 1.0
 **/
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;

    //redis集群客户端
    @Autowired
    JedisClientCluster jedisClientCluster;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;
    /*
     * @Author cy
     * @Description 根据cid分页获取内容list
     * @Date 2019/4/9 10:18
     * @Param [cid, page, rows]
     * @return cn.qinguu.common.pojo.EasyUIDataGridResult
     **/
    @Override
    public EasyUIDataGridResult getContentList(Long cid, Integer page, Integer rows) {
        if (page == null) page = 1;
        if (rows == null) rows = 30;
        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> contents = tbContentMapper.selectByExample(example);
        PageInfo<TbContent> info = new PageInfo<>(contents);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(info.getList());
        result.setTotal((int) info.getTotal());
        return result;
    }
    /*
     * @Author cy
     * @Description 保存内容
     * @Date 2019/4/9 10:17
     * @Param [tbContent]
     * @return cn.qinguu.common.pojo.TaoBaoResult
     **/
    @Override
    public TaoBaoResult saveContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContentMapper.insert(tbContent);
        //删除缓存
        jedisClientCluster.hdel(CONTENT_KEY,tbContent.getCategoryId()+"");
        return TaoBaoResult.ok();
    }

    /*
     * @Author cy
     * @Description 更新内容
     * @Date 2019/4/9 10:17
     * @Param [tbContent]
     * @return cn.qinguu.common.pojo.TaoBaoResult
     **/
    @Override
    public TaoBaoResult updateContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setUpdated(date);
        tbContentMapper.updateByPrimaryKeySelective(tbContent);
        //删除缓存
        jedisClientCluster.hdel(CONTENT_KEY,tbContent.getCategoryId()+"");
        return TaoBaoResult.ok();
    }

    /*
     * @Author cy
     * @Description 根据内容id数组批量删除内容
     * @Date 2019/4/9 10:16
     * @Param [ids]
     * @return cn.qinguu.common.pojo.TaoBaoResult
     **/
    @Override
    public TaoBaoResult deleteContent(Long[] ids) {
        TbContent tbContent = tbContentMapper.selectByPrimaryKey(ids[0]);
        for (Long id :
                ids) {
            tbContentMapper.deleteByPrimaryKey(id);
        }
        //删除缓存
        jedisClientCluster.hdel(CONTENT_KEY,tbContent.getCategoryId()+"");
        return TaoBaoResult.ok();
    }

    /*
     * @Author cy
     * @Description 根据cid获取所有的内容广告
     * @Date 2019/4/9 10:16
     * @Param [cid]
     * @return java.util.List<cn.qinguu.pojo.TbContent>
     **/
    @Override
    public List<TbContent> getAd1Content(Long cid) {
        String json = jedisClientCluster.hget(CONTENT_KEY,cid+"");
        //判断json是否为空
        if (StringUtil.isNotEmpty(json)){
            List<TbContent> list = JsonUtils.jsonToList(json,TbContent.class);
            return  list;
        }
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> tbContents = tbContentMapper.selectByExample(example);
        //将pojo转换为json字符串
        json = JsonUtils.objectToJson(tbContents);
        //将json字符串发送到redis缓存中
        jedisClientCluster.hset(CONTENT_KEY,cid+"",json);
        return tbContents;
    }

}
