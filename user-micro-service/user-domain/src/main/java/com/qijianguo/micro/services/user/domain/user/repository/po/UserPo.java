package com.qijianguo.micro.services.user.domain.user.repository.po;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity(name = "userPo")
@Table(name = "t_user")
@Data
public class UserPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nick_name", length = 20)
    private String nickName;
    @Column
    private String avatar;
    @Column
    private Integer gender;
    @Column
    private Integer age;
    @Column
    private Integer state;
    @Column
    private Integer role;
    @Column
    private Date createTime;
    @Column
    private Date modifyTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<AuthPo> authPos;

}
