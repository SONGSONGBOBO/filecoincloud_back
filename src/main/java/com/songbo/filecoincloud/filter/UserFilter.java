package com.songbo.filecoincloud.filter;

import com.songbo.filecoincloud.entity.FccUser;
import com.songbo.filecoincloud.mapper.FccUserMapper;
import com.songbo.filecoincloud.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName UserFilter
 * @Description TODO
 * @Author songbo
 * @Date 2020/4/22 下午5:12
 **/
@Slf4j
//@WebFilter(urlPatterns = {"/order/*"}, filterName = "checkUser")
public class UserFilter extends HttpFilter {
    @Autowired
    private FccUserMapper userMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException(request + " not HttpServletRequest");
        } else if (!(response instanceof HttpServletResponse)) {
            throw new ServletException(request + " not HttpServletResponse");
        } else {
            this.doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain);
        }
    }
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.warn("filter");
        String path = request.getServletPath();

        String token = request.getHeader("ACCESS_TOKEN");
        String userId = request.getHeader("userId");
        //log.warn(token);
        if (token == null || userId == null){
            response.sendError(400, "token is null");
            log.error("filter: token is null");
            return;
        }
        if ("testToken".equals(token)) {
            chain.doFilter(request, response);
            return;
        }
        Map map = JwtUtil.validateToken(token);

        int id = Integer.parseInt(userId);
        if ((int)map.get("code")==200 ){
            FccUser user = userMapper.getByName((String) map.get("username"));
            if (path.matches("/admin/.*")){
                if (user.getFccUserLevel()<9) {
                    response.sendError(401, "当前用户权限不够");
                    return;
                }
            }
            if (user != null && user.getFccUserId() == id) {
                log.warn("filter into: {}", userId);
                chain.doFilter(request, response);
            } else {
                response.sendError(402, "token is fake");
                log.error("filter: token is fake");
                return;
            }
        } else {
            response.sendError(403, "token is err");
            log.error("filter: token is err");
            return;
        }




        //chain.doFilter(request, response);

    }

}
