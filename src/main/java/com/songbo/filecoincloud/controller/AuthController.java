package com.songbo.filecoincloud.controller;

import com.alibaba.fastjson.JSONObject;

import com.songbo.filecoincloud.entity.FccUser;
import com.songbo.filecoincloud.mapper.FccUserMapper;
import com.songbo.filecoincloud.service.IFccUserService;
import com.songbo.filecoincloud.utils.BcryptUtil;
import com.songbo.filecoincloud.utils.JwtUtil;
import com.songbo.filecoincloud.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ClassName AuthController
 * @Description TODO
 * @Author songbo
 * @Date 2020/4/22 下午8:57
 **/
@RestController
@RequestMapping("/auth")
@Slf4j
@Api(value = "登录注册", tags = {"登录注册"})
public class AuthController {
    @Autowired
    private IFccUserService userService;
    @Resource
    private FccUserMapper userMapper;


    @PostMapping("/login")
    @ApiOperation(value = "login， 成功返回Token字符串")
    public ResultUtil login(@RequestParam("name") @ApiParam("用户名") String name,
                            @RequestParam("pwd") @ApiParam("密码") String pwd) {

        FccUser user = userMapper.getByName(name);
        if (null == user) {
            return ResultUtil.result400("当前用户未注册！", user);
        }

        if (BcryptUtil.decryption(pwd, user.getFccUserPwd())) {
            try {
                String jwt = JwtUtil.createJWT(user.getFccUserName());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", jwt);
                jsonObject.put("userId", user.getFccUserId());
                return ResultUtil.result200("验证通过", jsonObject);
            }
            catch (Exception e) {
                log.error("create jwt fail", e);
                return ResultUtil.result500("生成token失败！", e);
            }
        } else {
            return ResultUtil.result400("密码错误！", pwd);
        }
    }

    @PostMapping("/register")
    @ApiOperation(value = "register")
    public ResultUtil register(@RequestParam("name") @ApiParam("用户名") String name,
                               @RequestParam("pwd") @ApiParam("密码") String pwd
                               /*@RequestParam(value = "code", required = false) @ApiParam("邀请码") String code,
                               @RequestParam(value = "mail", required = false) @ApiParam("邮箱") String mail*/) {

        FccUser user = userMapper.getByName(name);
        if (null != user) {
            return ResultUtil.result400("当前用户已注册！", name);
        }
        try {
            userService.save(new FccUser(name, BcryptUtil.encryption(pwd)));
        } catch (Exception e) {
            return ResultUtil.result500("注册失败！", e.getMessage());
        }
        return ResultUtil.result200("注册成功!", name);

    }

}
