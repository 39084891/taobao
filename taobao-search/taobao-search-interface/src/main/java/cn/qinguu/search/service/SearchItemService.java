package cn.qinguu.search.service;

import cn.qinguu.common.pojo.SearchResult;
import cn.qinguu.common.pojo.TaoBaoResult;


public interface SearchItemService {
    public TaoBaoResult importAllIndex() throws Exception;
    public SearchResult search(String query,Integer page,Integer rows) throws Exception;
}
