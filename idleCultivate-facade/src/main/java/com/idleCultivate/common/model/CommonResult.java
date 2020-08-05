package com.idleCultivate.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResult<T> implements Serializable {
    /**
     * 状态编码（0-失败，1-成功）
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回对象
     */
    private T data;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(Integer code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public boolean isSuccess() {
        return this.code.equals(1);
    }

    public static CommonResult success() {
        return new CommonResult(1, "处理成功");
    }

    public static CommonResult success(String message) {
        return new CommonResult(1, message);
    }

    public static <T> CommonResult success(String message, T data) {
        return new CommonResult(1, message, data);
    }

    public static CommonResult fail() {
        return new CommonResult(0, "处理失败");
    }

    public static CommonResult fail(String message) {
        return new CommonResult(0, message);
    }

    public static <T> CommonResult fail(String message, T data) {
        return new CommonResult(0, message, data);
    }
}
