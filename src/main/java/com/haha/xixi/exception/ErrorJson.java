package com.haha.xixi.exception;

/**
 * @author admin
 * @date 2019/5/3113:46
 * @description: TODO
 */
public class ErrorJson {

    private Integer code;

    private Boolean success = false;

    private String method;

    private String path;

    private String msg;

    private Long timestamp = System.currentTimeMillis();
    ;

    private String tips = "Custom exception by admin";

    public ErrorJson build(Integer code, String method, String path, String msg) {
        this.code = code;
        this.method = method;
        this.path = path;
        this.msg = msg;
        return this;
    }

    public ErrorJson build(Integer code, Boolean success, String method, String path, String msg) {
        this.code = code;
        this.success = success;
        this.method = method;
        this.path = path;
        this.msg = msg;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public String toString() {
        return "Ajaxjson{" +
                "code=" + code +
                ", success=" + success +
                ", method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", msg='" + msg + '\'' +
                ", timestamp=" + timestamp +
                ", tips='" + tips + '\'' +
                '}';
    }
}
