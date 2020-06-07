package com.songbo.filecoincloud.bean.rpcresult;

import lombok.Data;

/**
 * @ClassName CurrentResult
 * @Description TODO
 * @Author songbo
 * @Date 2019/12/26 下午2:01
 **/
@Data
public class CurrentResult<T,V> {

    private T result;
    private V error;
    private String id;

    public CurrentResult(){}

    public CurrentResult(T result, V error, String id) {
        this.result = result;
        this.error = error;
        this.id = id;
    }
}
