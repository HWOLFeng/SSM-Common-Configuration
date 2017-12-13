package cn.hwolf.common.entity;

/**
 * @author hwolf
 * @email h.wolf@qq.com
 * @date 2017/12/2.
 */
public enum ErrorCodeEnum {
    NO_POWER(1000, "权限不足"),
    USER_EXIST(1001, "登录名已经存在"),
    USERNAME_PASSSWORD_ERROR(1002, "用户名或密码错误"),
    DUPLICATE_DELETE(1003, "重复删除"),
    PASSWORD_ERROR(1004, "旧密码错误"),
    USER_NOT_USE(1005, "您当前用户被禁用，请与管理员联系"),
    VERIFICATE_ERRO(1006,"短信发送失败"),
    SYSTEM_ERROR(9999, "系统错误");



    private int status;
    private String msg;

    ErrorCodeEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ErrorCodeEnum getByCode(int code) {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if (errorCodeEnum.status == code) {
                return errorCodeEnum;
            }
        }
        throw new IllegalArgumentException("No element matches " + code);
    }

    public int getStatus() {
        return status;
    }


    public String getMsg() {
        return msg;
    }
}

