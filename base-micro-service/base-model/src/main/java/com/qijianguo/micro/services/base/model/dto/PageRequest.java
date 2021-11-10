package com.qijianguo.micro.services.base.model.dto;

/**
 * @author qijianguo
 */
public class PageRequest {

    private long pageNum;

    private int pageSize;

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
