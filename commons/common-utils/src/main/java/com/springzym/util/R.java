package com.springzym.util;

import java.util.HashMap;
import java.util.Map;
import com.springzym.exception.*;

/**
 * 统一返回值管理
 * @author springzym
 */
public class R {

    /**
     * 固定状态码接口
     * @author springzym
     */
    interface Code {
        /**
         * 成功
         */
        int SUCCESS = 200;
        /**
         * 失败
         */
        int ERROR = 500;
        /**
         * 未登录
         */
        int NOT_LOGGED_IN = 401;
        /**
         * 没有权限
         */
        int UNAUTHORIZED = 403;
        /**
         * 请求参数错误
         */
        int NOT_FOUND = 404;
        /**
         * 出现异常
         */
        int EXCEPTION = 001;
    }

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述信息
     */
    private String msg;
    /**
     * 请求是否成功
     */
    private boolean success;
    /**
     * 返回的数据
     */
    private Map<String, Object> data;

    private R(Integer code, String msg, boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    private R(){
        new GlobalException("不允许通过构造方法创建 R 对象");
    }

    public static R ok() {
        return new R(Code.SUCCESS,"操作成功", true);
    }

    public static R ok(String msg) {
        return new R(Code.SUCCESS, msg, true);
    }

    public static R ifSuccess(Boolean success) {
        if (success){
            return new R(Code.SUCCESS,"操作成功", success);
        }else {
            return new R(Code.ERROR,"操作失败", success);
        }
    }

    public static R error() {
        return new R(Code.ERROR,"操作失败", false);
    }

    public static R error(String msg) {
        return new R(Code.ERROR, msg, false);
    }


    /**
     * getter setter toString
     */
    public Integer getCode() {
        return code;
    }

    public R setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public R setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public R setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Map getData() {
        return data;
    }

    public R setData(String key, Object value) {
        if (data == null) {
            data = new HashMap(1);
        }
        data.put(key, value);
        return this;
    }

    public R Data(Map data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
