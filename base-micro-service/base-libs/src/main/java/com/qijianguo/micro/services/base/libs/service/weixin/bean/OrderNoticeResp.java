package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * @author qijianguo
 */
@Data
@JacksonXmlRootElement(localName = "xml")
public class OrderNoticeResp {

    private String return_code;

    private String return_msg;
}
