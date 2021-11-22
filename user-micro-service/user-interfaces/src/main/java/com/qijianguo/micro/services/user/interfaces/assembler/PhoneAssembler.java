package com.qijianguo.micro.services.user.interfaces.assembler;

import com.qijianguo.micro.services.user.domain.user.entity.Phone;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCodeRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCodeResponse;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCommitRequest;

import java.util.Date;

public class PhoneAssembler {

    public static Phone toDO(PhoneCodeRequest request) {
        Phone phone = new Phone();
        phone.setPhone(request.getPhone());
        phone.setCreateTime(new Date());
        phone.setModifyTime(new Date());
        phone.setCount(0);
        return phone;
    }

    public static Phone toDO(PhoneCommitRequest request) {
        Phone phone = new Phone();
        phone.setPhone(request.getPhone());
        return phone;
    }

    public static PhoneCodeResponse toDTO(Phone phone) {
        PhoneCodeResponse response = new PhoneCodeResponse(Phone.Config.LIMITED.getNum());
        return response;
    }
}
