package com.qijianguo.micro.services.base.model.dto;

import com.qijianguo.micro.services.base.exception.EmBusinessError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qijianguo
 */
@Data
@ApiModel("公共返回结果")
public class BaseResponse {

    @ApiModelProperty(value = "成功：ok， 失败：fail", dataType = "String", example = "ok", position = 1)
    private Enum status;
    @ApiModelProperty(value = "状态码", dataType = "Integer", position = 2)
    private Integer code;
    @ApiModelProperty(value = "描述信息", dataType = "String", position = 3)
    private String message;
    @ApiModelProperty(value = "返回结果", dataType = "Object", position = 5)
    private Object result;

    public BaseResponse() {
    }

    public BaseResponse(ResponseStatusEnum status, Integer code, String message, Object result) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    /**
     * request success
     * @return default value is null
     */
    public static BaseResponse success() {
        return new BaseResponse(ResponseStatusEnum.OK, EmBusinessError.SUCCESS.getErrCode(), EmBusinessError.SUCCESS.getErrMsg(), null);
    }

    public BaseResponse success(Object data) {
        return new BaseResponse(ResponseStatusEnum.OK, EmBusinessError.SUCCESS.getErrCode(), EmBusinessError.SUCCESS.getErrMsg(), data);
    }

    public static BaseResponse fail(EmBusinessError buzzError) {
        return new BaseResponse(ResponseStatusEnum.ERR, buzzError.getErrCode(), buzzError.getErrMsg(), null);
    }

    public static BaseResponse fail(EmBusinessError buzzError, String errMsg) {
        return new BaseResponse(ResponseStatusEnum.ERR, buzzError.getErrCode(), errMsg, null);
    }

}
