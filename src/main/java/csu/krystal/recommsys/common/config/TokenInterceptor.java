package csu.krystal.recommsys.common.config;

import com.alibaba.fastjson.JSON;

import csu.krystal.recommsys.dto.TokenPassJson;
import csu.krystal.recommsys.common.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private ITokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        int result = 0;
        if(token != null){
            result = tokenService.verify(token);
//            result = TokenProccessor.verify(token);
            if(result == 1){
                System.out.println("通过拦截器");
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try{
            TokenPassJson json = new TokenPassJson();
            json.setSuccess(result);//0表示验证失败,2表示过期
            response.getWriter().append(JSON.toJSONString(json));
            System.out.println("认证失败，未通过拦截器");
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}

