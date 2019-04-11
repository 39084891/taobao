package cn.qinguu.search.dao;

import cn.qinguu.common.pojo.SearchItem;
import cn.qinguu.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: SearchDao
 * @Description TOOD
 * @Author cy
 * @Date 2019/4/1022:00
 * @Version 1.0
 **/
@Repository
public class SearchDao {
    @Autowired
    private SolrServer solrServer;
    public SearchResult search(SolrQuery query) throws SolrServerException {
        QueryResponse response = solrServer.query(query);
        SolrDocumentList results = response.getResults();
        List<SearchItem> itemList = new ArrayList<>();
        //设置高亮
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        for (SolrDocument document:
             results) {
            SearchItem item = new SearchItem();
            item.setId((String) document.get("id"));
            item.setCategory_name((String) document.get("item_category_name"));
            item.setImage((String) document.get("item_image"));
            item.setPrice((long) document.get("item_price"));
            item.setSell_point((String) document.get("item_sell_point"));
            List<String> highligthList = highlighting.get(document.get("id")).get("item_title");
            if(highligthList!=null && highligthList.size()>0){
                item.setTitle(highligthList.get(0));
            } else {
                item.setTitle((String) document.get("item_title"));
            }
            itemList.add(item);
        }
        SearchResult searchResult = new SearchResult();
        searchResult.setItemList(itemList);
        searchResult.setRecordCount(results.getNumFound());
        return  searchResult;
    }
}
