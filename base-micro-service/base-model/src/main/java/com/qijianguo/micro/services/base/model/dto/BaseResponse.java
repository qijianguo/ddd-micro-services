package com.qijianguo.micro.services.base.model.dto;

import com.qijianguo.micro.services.base.exception.CommonError;
import com.qijianguo.micro.services.base.exception.EmBusinessError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qijianguo
 */
@Data
@ApiModel("公共返回结果")
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    @ApiModelProperty(value = "成功：SUCCESS， 失败：FAIL", dataType = "String", example = "OK", position = 1)
    private Enum status;
    @ApiModelProperty(value = "状态码", dataType = "Integer", example = "200", position = 2)
    private Integer code;
    @ApiModelProperty(value = "描述信息", dataType = "String", position = 3)
    private String message;
    @ApiModelProperty(value = "返回结果", dataType = "Object", position = 4)
    private T result;

    /**
     * request success
     * @return default value is null
     */
    public static BaseResponse success() {
        return new BaseResponse(ResponseStatus.SUCCESS, EmBusinessError.SUCCESS.getErrCode(), EmBusinessError.SUCCESS.getErrMsg(), null);
    }

    public static <T>BaseResponse success(T data) {
        return new BaseResponse(ResponseStatus.SUCCESS, EmBusinessError.SUCCESS.getErrCode(), EmBusinessError.SUCCESS.getErrMsg(), data);
    }

    public static BaseResponse fail(Integer errCode, String errMsg) {
        return new BaseResponse(ResponseStatus.FAIL, errCode, errMsg, null);
    }

    public static BaseResponse fail(CommonError buzzError) {
        return new BaseResponse(ResponseStatus.FAIL, buzzError.getErrCode(), buzzError.getErrMsg(), null);
    }

    public static BaseResponse fail(CommonError buzzError, String errMsg) {
        return new BaseResponse(ResponseStatus.FAIL, buzzError.getErrCode(), errMsg, null);
    }

    /**
     * base response status.
     */
    enum ResponseStatus {
        SUCCESS,
        FAIL,
    }

}
