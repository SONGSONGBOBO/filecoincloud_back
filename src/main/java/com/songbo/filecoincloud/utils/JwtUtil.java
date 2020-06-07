package com.songbo.filecoincloud.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TokenUtil
 * @Description TODO
 * @Author songbo
 * @Date 19-7-28 下午1:02
 **/
public class JwtUtil {
    //毫秒
    private static long EXPIRATION_TIME = 1000*60*60*24;
            //7*24*3600*1000;

    private static String SECRET = CommonUtil.JWTSECRET;// 秘钥

    public static SecretKey generalKey(){
        String stringKey = SECRET;//本地配置文件中加密的密文7786df7fc3a34e26a61c034d5ec8245d
        byte[] encodedKey = Base64.decodeBase64(stringKey);//本地的密码解码[B@152f6e2
        System.out.println(encodedKey);//[B@152f6e2
        System.out.println(Base64.encodeBase64URLSafeString(encodedKey));//7786df7fc3a34e26a61c034d5ec8245d
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");// 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。
        return key;
    }

    /*时间戳单位为毫秒，转化要×1000*/
    public static String createJWT(String username) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date now = new Date(nowMillis);
        Map<String,Object> claims = new HashMap<String,Object>();//创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        claims.put("username", username);
        //claims.put("createTime", createTime);
        SecretKey key = generalKey();//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                //.setId(id)                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           //iat: jwt的签发时间
                .setSubject(username)        //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, key);//设置签名使用的签名算法和签名使用的秘钥
        if (EXPIRATION_TIME >= 0) {
            long expMillis = nowMillis + EXPIRATION_TIME;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();           //就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
        //打印了一哈哈确实是下面的这个样子
        //eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiIiLCJ1c2VyX25hbWUiOiJhZG1pbiIsIm5pY2tfbmFtZSI6IkRBU0RBMTIxIiwiZXhwIjoxNTE3ODI4MDE4LCJpYXQiOjE1MTc4Mjc5NTgsImp0aSI6Imp3dCJ9.xjIvBbdPbEMBMurmwW6IzBkS3MPwicbqQa2Y5hjHSyo
    }


    public static Map validateToken(String token) {
        Map<String, Object> map = new HashMap<>();
        if (token != null) {

            try {
                Map<String, Object> body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
                String username = (String) (body.get("username"));
                //String email = (String) (body.get("email"));
                Integer exp = (Integer) body.get("exp");
                Integer iat = (Integer) body.get("iat");
                System.out.println("body:"+body);

                if (username == null || username.isEmpty()  ) {
                    map.put("code", 500);
                    map.put("msg", "非法token");
                    return map;
                } else {
                    map.put("username", username);
                    map.put("deadline", exp);
                    map.put("create", iat);
                    map.put("code", 200);
                    map.put("msg", "ok");
                    return map;
                }
            } catch (Exception e){
                map.put("code", 500);
                map.put("msg", "token已过期");
                return map;
            }
        } else {
            map.put("code", 400);
            map.put("msg", "本地未发现Token");
            return map;
        }
    }

    /*iat：发现token 的时间，exp：过期时间 都是timestamp秒*/
    public static void main(String[] args) throws Exception {
        //String test = JwtUtil.createJWT("醉酒青牛");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);
        //String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2OTU2OTM2MDZAcXEuY29tIiwiZXhwIjoxNTY0ODQyNTQ2LCJpYXQiOjE1NjQ4NDE5NDYsImVtYWlsIjoiNjk1NjkzNjA2QHFxLmNvbSIsInVzZXJuYW1lIjoidGVzdCJ9.saWXbfBAhymqJJieppyle0t0CSNzUFAADvxa5KNgayo";
        //Map map = JwtUtil.validateToken("");
        //Map<String, Object> body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        //long exp = (long) body.get("exp");
        //System.out.println(exp);
        /*时间戳验证*/
        //Date timestamp = new Date(Long.parseLong("1564994972")*1000);
        //System.out.println(timestamp);

        //System.out.println( new Integer((int) System.currentTimeMillis()));

    }
}
