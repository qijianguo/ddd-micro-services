package com.qijianguo.micro.services.user.domain.user.repository.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "userPo")
@Table(name = "t_auth")
@Data
public class AuthPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private UserPo userPo;

}
