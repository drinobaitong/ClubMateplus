package org.intership.clubmate.enums;
import lombok.Getter;
@Getter
public enum HttpCode {

    SUCCESS(200,"操作成功"),
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    CLUBNAME_EXIST(502,"社团名已存在"),
    LOGIN_ERROR(503,"用户名或密码错误"),
    CONTENT_NOT_NULL(504, "填写内容不能为空"),
    USER_NULL(505,"用户不存在");

    final int code;
    final String msg;
    HttpCode(int code, String message) {
        this.code = code;
        this.msg = message;
    }


}
