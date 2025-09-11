package com.equipmentwork.utils;

public class DeepSeekCommonResult<T> {
    private int code; // 状态码
    private String msg; // 消息
    private T data;    // 数据（使用泛型）

    // 添加静态工厂方法，方便创建结果对象
    public static <T> DeepSeekCommonResult<T> success(T data) {
        DeepSeekCommonResult<T> result = new DeepSeekCommonResult<>();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    public static <T> DeepSeekCommonResult<T> success() {
        return success(null);
    }

    public static <T> DeepSeekCommonResult<T> error(int code, String msg) {
        DeepSeekCommonResult<T> result = new DeepSeekCommonResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> DeepSeekCommonResult<T> error(String msg) {
        return error(500, msg);
    }

    // 添加无参构造
    public DeepSeekCommonResult() {
    }

    // 添加全参构造（可选）
    public DeepSeekCommonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
}
