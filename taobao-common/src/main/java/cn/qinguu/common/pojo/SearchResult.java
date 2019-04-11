package cn.qinguu.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @program: SearchResult
 * @Description TOOD
 * @Author cy
 * @Date 2019/4/1021:53
 * @Version 1.0
 **/
public class SearchResult implements Serializable {

    private List<SearchItem> itemList;//搜索结果列表
    private long recordCount;//总记录数
    private long pageCount;//总页数

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
}
