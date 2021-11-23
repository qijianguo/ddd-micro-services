package com.qijianguo.micro.services.user.domain.user.repository.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_user")
@Data
public class UserPo {

    @Id
    private Integer id;

    private String nickName;

    private String avatar;

    private Integer gender;

    private Integer age;

    private Integer state;

    private Integer role;

    private String phone;

    private Date createTime;

    private Date modifyTime;
}
