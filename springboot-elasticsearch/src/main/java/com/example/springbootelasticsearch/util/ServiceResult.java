package com.example.springbootelasticsearch.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Description:服务器统一返回对象
 * @Author LinJia
 * @Date 2020/5/20 16:09
 * @Param
 * @return
 **/
public class ServiceResult {
    /**
     * SUCCESS 成功
     * FAILURE 失败
     */
    public enum STATUS {
        SUCCESS, FAILURE
    }

    private STATUS status;
    private String message = "";
    private Object data;

    private ServiceResult() {
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @JsonIgnore
    public static ServiceResult success(String message) {
        ServiceResult info = new ServiceResult();
        info.setStatus(STATUS.SUCCESS);
        info.setMessage(message);
        return info;
    }

    @JsonIgnore
    public static ServiceResult success(String message, Object data) {
        ServiceResult info = new ServiceResult();
        info.setStatus(STATUS.SUCCESS);
        info.setMessage(message);
        info.setData(data);
        return info;
    }

    @JsonIgnore
    public static ServiceResult failure(String message) {
        ServiceResult info = new ServiceResult();
        info.setStatus(STATUS.FAILURE);
        info.setMessage(message);
        return info;
    }
}
