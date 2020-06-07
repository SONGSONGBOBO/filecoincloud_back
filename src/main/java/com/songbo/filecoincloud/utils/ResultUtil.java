package com.songbo.filecoincloud.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName ResultUtil
 * @Description TODO
 * @Author songbo
 * @Date 19-9-20 下午10:13
 **/
@Setter
@Getter
@ToString
public  class ResultUtil implements Serializable {

    private String code;
    private String msg;
    private Object data;

    public ResultUtil(){}

    public ResultUtil(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultUtil result200(String msg, Object data){
        return new ResultUtil("200",msg, data);
    }
    //请求错误
    public static ResultUtil result400(String msg, Object data){
        return new ResultUtil("400",msg, data);
    }
    //服务器错误
    public static ResultUtil result500(String msg, Object data){
        return new ResultUtil("500",msg, data);
    }
    //token错误
    public static ResultUtil result401(String msg, Object data){
        return new ResultUtil("401",msg, data);
    }
}
