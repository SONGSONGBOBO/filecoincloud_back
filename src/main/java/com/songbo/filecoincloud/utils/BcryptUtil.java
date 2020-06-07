package com.songbo.filecoincloud.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @ClassName BcryptUtil
 * @Description TODO
 * @Author songbo
 * @Date 19-8-2 下午1:37
 **/
public class BcryptUtil {
    /*加密*/
    public static String encryption(String word){
        String hashed = BCrypt.hashpw(word,BCrypt.gensalt());
        return hashed;
    }
    /*解密*/
    public static boolean decryption(String word,String hashed){
        return BCrypt.checkpw(word,hashed);
    }

    public static void main(String[] args) {


    }
}
