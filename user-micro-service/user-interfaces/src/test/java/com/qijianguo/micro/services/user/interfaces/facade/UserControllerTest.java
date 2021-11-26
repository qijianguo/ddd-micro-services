package com.qijianguo.micro.services.user.interfaces.facade;

import com.alibaba.fastjson.JSON;
import com.qijianguo.micro.services.base.exception.EmBusinessError;
import com.qijianguo.micro.services.user.application.service.CaptchaAppService;
import com.qijianguo.micro.services.user.domain.captcha.entity.Captcha;
import com.qijianguo.micro.services.user.domain.captcha.entity.Phone;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import com.qijianguo.micro.services.user.interfaces.assembler.CaptchaAssembler;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneCodeRequest;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneCommitRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CaptchaAppService captchaAppService;

    @Test
    public void loginByPhone() throws Exception {
        // 获取验证码
        Phone p = initPhoneCode();
        // 正常请求
        ResultActions result = byPhone(p);
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(EmBusinessError.SUCCESS.getErrCode()))
        ;
        // 重复请求
        ResultActions repeatResult = byPhone(p);
        repeatResult.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(UserEmBusinessError.CODE_EXPIRED.getErrCode()))
        ;

    }

    private Phone initPhoneCode() {
        CaptchaPhoneCodeRequest request = new CaptchaPhoneCodeRequest();
        request.setPhone("17521226605");
        request.setCaptchaImage("");
        Captcha captcha = CaptchaAssembler.toDO(request);
        Phone p = captchaAppService.create(captcha).getPhone();
        Assert.isTrue(p != null && p.getCode() != null, "获取验证码失败");
        return p;
    }

    private ResultActions byPhone(Phone phone) throws Exception {
        CaptchaPhoneCommitRequest request = new CaptchaPhoneCommitRequest();
        request.setPhone(phone.getNumber());
        request.setCode(phone.getCode());

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders
                .post("/user/phone").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(request))
        ).andDo(MockMvcResultHandlers.print());
        return perform;
    }




}
