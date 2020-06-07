package com.songbo.filecoincloud.service;

import com.alibaba.fastjson.JSONObject;

import com.songbo.filecoincloud.bean.rpcresult.CurrentResult;
import okhttp3.Headers;

import java.util.Map;


/**
 * @ClassName RPCRequestService
 * @Description TODO
 * @Author songbo
 * @Date 19-9-21 下午1:58
 **/

public interface RPCRequestService {

    <T> T rpcRequest(String url, String method, Map<String, String> header, Object data, Class<T> tClass);

    <T> T currentRequest(String url, String method, Headers headers, Object data, Class<T> tClass) throws Exception;


}
