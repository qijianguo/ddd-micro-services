package com.qijianguo.micro.services.base.model.event;

import lombok.Data;

import java.util.Date;

/**
 * Root of Event
 * @author qijianguo
 */
@Data
public class BaseEvent<T> {

    /** 事件ID */
    private String id;

    private String eventType;

    private String aggregateId;

    private String aggregateType;

    private T eventPayload;

    private Date createTime;

}
