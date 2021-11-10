package com.qijianguo.micro.services.base.model.event;

import java.util.Date;

/**
 * Root of Event
 * @author qijianguo
 */
public class BaseEvent<T> {

    private Long eventId;

    private String eventType;

    private Long aggregateId;

    private String aggregateType;

    private T eventPayload;

    private Date occuredOn;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(Long aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public T getEventPayload() {
        return eventPayload;
    }

    public void setEventPayload(T eventPayload) {
        this.eventPayload = eventPayload;
    }

    public Date getOccuredOn() {
        return occuredOn;
    }

    public void setOccuredOn(Date occuredOn) {
        this.occuredOn = occuredOn;
    }
}
