package com.yao.ssyx.common.exception;

import com.yao.ssyx.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @className: SsyxException
 * @Description: 自定义异常处理
 * @author: long
 * @date: 2023-10-15 12:23
 */
@Data
public class SsyxException extends RuntimeException {

    //异常状态码
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public SsyxException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public SsyxException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
