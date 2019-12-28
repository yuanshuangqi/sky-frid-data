package com.ylj.sky.boot.manager.security.filter;

import cn.hutool.json.JSONUtil;
import com.ylj.sky.boot.common.constants.ResultCodeEnum;
import com.ylj.sky.boot.common.entity.Payload;
import com.ylj.sky.boot.common.util.ExceptionUtil;
import com.ylj.sky.boot.common.util.JwtUtils;
import com.ylj.sky.boot.common.util.ResponseUtil;
import com.ylj.sky.boot.manager.model.user.service.IUserService;
import com.ylj.sky.boot.manager.security.config.RsaKeyProperties;
import com.ylj.sky.boot.manager.security.entity.SecurityUser;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class JwtVerifyFilter extends BasicAuthenticationFilter {

    private IUserService userService;

    private AuthenticationManager authenticationManager;
    private RsaKeyProperties prop;
    public JwtVerifyFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop, IUserService userService) {
        super(authenticationManager);
        this.prop=prop;
        this.userService = userService;
    }
    @Transactional
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer")){
            chain.doFilter(request, response);
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            Map<String, Serializable> resultMap = new HashMap<>();
            resultMap.put("code", HttpServletResponse.SC_FORBIDDEN);
            resultMap.put("message", "请登录");
            PrintWriter out = response.getWriter();
            out.write(JSONUtil.parse(resultMap).toString());
            out.flush();
            out.close();
        }else {
            String token = header.replace("Bearer", "");
            Payload<SecurityUser> payload = null;
            try {
                payload = JwtUtils.getInfoFromToken(token, prop.getPublicKey(), SecurityUser.class);
            } catch (ExpiredJwtException e) { //令牌过期
                log.error(ExceptionUtil.getMessage(e));
                ResponseUtil.response(response,200, ResultCodeEnum.LOGIN_TIMEOUT);
                return;
            }
            // 查询用户权限
            SecurityUser securityUser = userService.getUserRolesByUsername(payload.getUserInfo().getUsername());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUser.getPassword(), null, securityUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }


    }
}
