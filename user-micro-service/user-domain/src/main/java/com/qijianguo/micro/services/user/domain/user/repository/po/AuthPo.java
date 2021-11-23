package com.qijianguo.micro.services.user.domain.user.repository.po;

import com.qijianguo.micro.services.user.domain.user.entity.Auth;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_auth")
@Data
public class AuthPo {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "user_id", length = 11)
    private Integer userId;
    @Column
    private String type;
    @Column(name = "union_key", length = 50)
    private String unionKey;
    @Column(name = "union_val", length = 50)
    private String unionVal;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "modify_time")
    private Date modifyTime;

}
