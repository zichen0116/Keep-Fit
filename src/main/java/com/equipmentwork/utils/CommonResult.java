package com.equipmentwork.utils;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description ="通用响应结果",name="CommonResult")
public class CommonResult {
    @Schema(name="code",description = "响应状态码")
    private int code;
    @Schema(name="msg",description = "响应信息")
    private String msg;
    @Schema(name="data",description = "响应数据")
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

