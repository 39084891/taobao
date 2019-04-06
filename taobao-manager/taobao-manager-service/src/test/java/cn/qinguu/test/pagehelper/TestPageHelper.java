package cn.qinguu.test.pagehelper;

import cn.qinguu.mapper.TbItemCatMapper;
import cn.qinguu.mapper.TbItemMapper;
import cn.qinguu.pojo.TbContentExample;
import cn.qinguu.pojo.TbItem;
import cn.qinguu.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestPageHelper {
    @Test
    public void testHelper() {
//
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        TbItemMapper itemMapper = context.getBean(TbItemMapper.class);
        PageHelper.startPage(1,3);
        TbItemExample itemExample = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(itemExample);
        List<TbItem> list2 = itemMapper.selectByExample(itemExample);
//        取分页信息
        PageInfo<TbItem> info = new PageInfo<>(list);
        System.out.println("第一个list总长度："+list.size());
        System.out.println("第二个list总长度："+list2.size());


        System.out.println("总条数："+info.getTotal());
        for (TbItem tbItem:
             list) {
            System.out.println(tbItem.getId()+">>>>mingchen>>>>"+tbItem.getTitle());
        }

    }
}
