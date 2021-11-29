package com.qijianguo.micro.services.user.domain.user.repository.po;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qijianguo
 */
@Entity
@Table(name = "t_user_event")
@Data
public class UserEventPo {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    @Column(name = "user_id", length = 11)
    private Integer userId;
    @Column(name = "event_type", length = 20)
    private String eventType;
    @Column(name = "aggregate_id", length = 20)
    private String aggregateId;
    @Column(name = "aggregate_type", length = 20)
    private String aggregateType;
    @Column(name = "event_payload", length = 512)
    private String eventPayload;
    @Column(name = "create_time")
    private Date createTime;

}
