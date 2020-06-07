package com.songbo.filecoincloud.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.songbo.filecoincloud.bean.rpcresult.CurrentResult;
import com.songbo.filecoincloud.service.RPCRequestService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RPCRequestServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2019/12/26 下午12:44
 **/
@Service
@Slf4j
public class RPCRequestServiceImpl implements RPCRequestService {



    @Override
    public <T> T rpcRequest(String url, String method,Map<String, String> header, Object data, Class<T> tClass) {
        return null;
    }

    @Override
    public <T> T currentRequest(String url, String method, Headers headers, Object data, Class<T> tClass) throws Exception {


        OkHttpClient okHttpClient = new OkHttpClient();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(1000, TimeUnit.SECONDS);
        builder.readTimeout(1000, TimeUnit.SECONDS);
        MediaType type = MediaType.parse("Content-type; "+headers.get("Content-Type"));

        Request request;
        if ("get".equals(method)) {
            request = new Request.Builder()
                    .url(url)
                    .headers(headers)
                    .build();
        } else {
            RequestBody requestBody = RequestBody.create(type, JSONObject.toJSONString(data));
            log.warn("requestBody: " + JSONObject.toJSONString(data) + requestBody.toString(), requestBody);
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader(headers.name(0),headers.value(0))
                    .addHeader(headers.name(1), headers.value(1))
                    .build();

        }
        System.out.println(request.headers());
        Call call = okHttpClient.newCall(request);
        log.warn("request.body: " + request.toString(), request);
        try {
            Response response = call.execute();
            if (response.code() != 200) {
                log.error(response.message());
                throw new Exception(response.message());
            }
            String res = response.body().string();
            log.warn("response.body: " + res, response);

            JSONObject jsonObject = (JSONObject) JSONObject.parse(res);
            return jsonObject.toJavaObject(tClass);
        } catch (Exception e) {
            log.warn("GET_fail " + e);
            throw new Exception(e.getMessage());
        }
    }
}
