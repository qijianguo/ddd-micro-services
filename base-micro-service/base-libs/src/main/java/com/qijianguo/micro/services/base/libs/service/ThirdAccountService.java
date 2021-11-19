package com.qijianguo.micro.services.base.libs.service;

import com.qijianguo.micro.services.base.libs.bean.ThirdAccountResponse;
import com.qijianguo.micro.services.base.libs.bean.ThirdAccoutRequest;

/**
 * 第三方账号服务
 * @author qijianguo
 */
public interface ThirdAccountService {

    ThirdAccountResponse getAccount(ThirdAccoutRequest request);
}
