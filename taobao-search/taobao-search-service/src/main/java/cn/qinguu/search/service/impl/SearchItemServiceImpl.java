package cn.qinguu.search.service.impl;

import cn.qinguu.common.pojo.SearchItem;
import cn.qinguu.common.pojo.SearchResult;
import cn.qinguu.common.pojo.TaoBaoResult;
import cn.qinguu.search.dao.SearchDao;
import cn.qinguu.search.mapper.SearchItemMapper;
import cn.qinguu.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @program: SearchItemServiceImpl
 * @Description TOOD
 * @Author cy
 * @Date 2019/4/1018:54
 * @Version 1.0
 **/
@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    SearchItemMapper searchItemMapper;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private SearchDao searchDao;
    @Override
    public TaoBaoResult importAllIndex() throws Exception {
        List<SearchItem> searchItemList = searchItemMapper.getSearchItemList();
        int i = 0;
        //遍历所有的 searchitem并添加到solr索引中
        for (SearchItem searchItem:
             searchItemList) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",searchItem.getId());
            document.addField("item_category_name",searchItem.getCategory_name());
            document.addField("item_desc",searchItem.getItem_desc());
            document.addField("item_image",searchItem.getImage());
            document.addField("item_price",searchItem.getPrice());
            document.addField("item_sell_point",searchItem.getSell_point());
            document.addField("item_title",searchItem.getTitle());
            solrServer.add(document);
            System.out.println(i++);
        }
        solrServer.commit();
        return TaoBaoResult.ok();
    }

    @Override
    public SearchResult search(String query, Integer page, Integer rows) throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setStart((page-1)*rows);
        solrQuery.setQuery(query);
        solrQuery.setRows(rows);
        //设置默认搜索域
        solrQuery.set("df","item_title");
        //设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");
        //启动搜索
        SearchResult searchResult = searchDao.search(solrQuery);
        long recordCount = searchResult.getRecordCount();
        long pageCount = recordCount/rows;
        if(recordCount%rows>0){
            pageCount++;
        }
        searchResult.setPageCount(pageCount);
        return searchResult;
    }
}
