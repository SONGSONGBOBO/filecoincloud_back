package com.songbo.filecoincloud.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName HMAC
 * @Description TODO
 * @Author songbo
 * @Date 2020/4/2 下午2:55
 **/
public class HMAC {


    public static String HmacSHA512( String key, String data)
    {
        return hmacSHA512(key, data,"HmacSHA512");
    }
    /**
     * 与php中的hash_hmac('sha512', $data, $key)功能相同
     * @param data
     * @param key
     * @return
     */
    private static String hmacSHA512(String key, String data,String cate) {
        String result = "";
        byte[] bytesKey = key.getBytes();
        final SecretKeySpec secretKey = new SecretKeySpec(bytesKey, cate);
        try {
            Mac mac = Mac.getInstance(cate);
            mac.init(secretKey);
            final byte[] macData = mac.doFinal(data.getBytes());
            byte[] hex = new Hex().encode(macData);

            result = new String(hex, "ISO-8859-1");
        } catch (NoSuchAlgorithmException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
