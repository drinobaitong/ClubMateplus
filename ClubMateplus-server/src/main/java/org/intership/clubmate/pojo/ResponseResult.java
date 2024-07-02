package org.intership.clubmate.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.intership.clubmate.enums.HttpCode;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult implements Serializable {

    private Integer code;
    private String msg;
    private Object data;

    public ResponseResult() {
        this.code = HttpCode.SUCCESS.getCode();
        this.msg = HttpCode.SUCCESS.getMsg();
    }

    public ResponseResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseResult error(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.errorMsg(code, msg);
    }
    public static ResponseResult success() {
        return new ResponseResult();
    }
    public static ResponseResult success(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.okMsg(code, null, msg);
    }

    public static ResponseResult success(Object data) {
        ResponseResult result = setAppHttpCodeEnum(HttpCode.SUCCESS, HttpCode.SUCCESS.getMsg());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }

    public static ResponseResult error(HttpCode enums){
        return setAppHttpCodeEnum(enums,enums.getMsg());
    }

    public static ResponseResult error(HttpCode enums, String msg){
        return setAppHttpCodeEnum(enums,msg);
    }

    public static ResponseResult setAppHttpCodeEnum(HttpCode enums){
        return success(enums.getCode(),enums.getMsg());
    }

    public static ResponseResult setAppHttpCodeEnum(HttpCode enums, String msg){
        return success(enums.getCode(),msg);
    }

    public ResponseResult errorMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = "出现异常";
        return this;
    }

    public ResponseResult successMsg(Integer code, Object data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult okMsg(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    public ResponseResult okMsg(Object data) {
        this.data = data;
        return this;
    }
}
