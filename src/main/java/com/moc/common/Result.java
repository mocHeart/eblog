package com.moc.common;

import lombok.Data;

@Data
public class Result {

    private String code;

    private String msg;

    private Object data;

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode("0");
        result.setData(data);
        result.setMsg("操作成功");
        return result;
    }

    public static Result success(String message, Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg(message);
        return m;
    }

    public static Result fail(String message) {
        Result result = new Result();
        result.setCode("-1");
        result.setData(null);
        result.setMsg(message);
        return result;
    }

    public static Result fail(String message, Object data) {
        Result result = new Result();
        result.setCode("-1");
        result.setData(data);
        result.setMsg(message);
        return result;
    }

    public static Result fail(int code, String message, Object data) {
        Result result = new Result();
        result.setCode(String.valueOf(code));
        result.setData(data);
        result.setMsg(message);
        return result;
    }
}
