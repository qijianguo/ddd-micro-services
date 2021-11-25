package com.qijianguo.micro.services.user.interfaces.assembler;

import com.qijianguo.micro.services.user.domain.user.entity.Phone;
import com.qijianguo.micro.services.user.domain.user.entity.PhonePolicy;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneCodeRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCodeResponse;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneCommitRequest;

import java.util.Date;

public class PhoneAssembler {

    public static Phone toDO(CaptchaPhoneCodeRequest request) {
        Phone phone = new Phone();
        phone.setNumber(request.getPhone());
        phone.setCreateTime(new Date());
        phone.setModifyTime(new Date());
        phone.setCount(0);
        return phone;
    }

    public static Phone toDO(CaptchaPhoneCommitRequest request) {
        Phone phone = new Phone();
        phone.setNumber(request.getPhone());
        return phone;
    }

    public static PhoneCodeResponse toDTO(Phone phone) {
        PhoneCodeResponse response = new PhoneCodeResponse(PhonePolicy.Config.LIMITED.getNum());
        return response;
    }
}
