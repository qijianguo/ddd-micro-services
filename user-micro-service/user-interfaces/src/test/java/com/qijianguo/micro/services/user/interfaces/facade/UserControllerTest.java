package com.qijianguo.micro.services.user.interfaces.facade;

import com.alibaba.fastjson.JSON;
import com.qijianguo.micro.services.base.exception.EmBusinessError;
import com.qijianguo.micro.services.user.application.service.CaptchaAppService;
import com.qijianguo.micro.services.user.domain.verification.entity.Verification;
import com.qijianguo.micro.services.user.domain.verification.entity.Phone;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import com.qijianguo.micro.services.user.interfaces.assembler.CaptchaAssembler;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneVerifyRequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(value = NAME_ASCENDING)
public class UserControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CaptchaAppService captchaAppService;

    private static String number;

    private static Integer code;

    @Test
    public void test01_getPhoneCode() {
        // 获取验证码
        PhoneRequest request = new PhoneRequest();
        request.setPhone("17521226607");
        request.setCaptchaImage("");
        Verification verification = CaptchaAssembler.toDO(request);
        Phone p = captchaAppService.create(verification).getPhone();
        number = p.getNumber();
        code = p.getCode();
        Assert.isTrue(p != null && p.getCode() != null, "获取验证码失败");
        logger.info("test001_loginByPhone finished");
    }

    @Test
    public void test02_loginByPhone() throws Exception {
        // 正常请求
        ResultActions result = byPhone();
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(EmBusinessError.SUCCESS.getErrCode()));
        logger.info("test002_loginByPhone finished");
    }

    @Test
    public void test03_loginByPhoneFrequency() throws Exception {
        // 重复请求
        ResultActions repeatResult = byPhone();
        repeatResult.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(UserEmBusinessError.CODE_EXPIRED.getErrCode()))
        ;
        logger.info("test003_loginByPhone finished");
    }

    private ResultActions byPhone() throws Exception {
        PhoneVerifyRequest request = new PhoneVerifyRequest();
        request.setPhone(number);
        request.setCode(code);

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders
                .post("/user/phone").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(request))
        ).andDo(MockMvcResultHandlers.print());
        return perform;
    }




}
