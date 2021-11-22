package com.qijianguo.micro.services.base.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author qijianguo
 */
@Data
public class PageResponse<T> {

    private long pageNum;

    private int pageSize;

    private long totalSize;

    private long totalPage;

    private List<T> records;

}
