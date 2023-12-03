package csu.krystal.recommsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import csu.krystal.recommsys.entity.User;
import csu.krystal.recommsys.mapper.UserMapper;
import csu.krystal.recommsys.service.ITokenService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private UserMapper userMapper;

    private static final long EXPIRE_TIME=60*60*1000*24*7; //过期时间7天
    private static final String KEY = "huterox"; //加密秘钥

    /**
     * 生成token
     * 由于只有当账号密码正确之后才会生成token所以这边只需要用户名进行识别
     * @param userName  用户名
     * @return
     */
    @Override
    public String createToken(String userName) {
        Map<String,Object> header = new HashMap();
        header.put("typ","JWT");
        header.put("alg","HS256");
        JwtBuilder builder = Jwts.builder().setHeader(header)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .setSubject(userName)//设置信息，也就是用户名
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,KEY);//加密方式
        return builder.compact();
    }

    @Override
    public int verify(String token) {
        Claims claims = null;
        try {
            //token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期，
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return 2;
        }
        //从token中获取用户名，当用户查询通过后即可
        String username = claims.getSubject();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if(user != null){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int getUidFromToken(String token) {
        Claims claims = null;

        try {
            //token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期，
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            //其实能到这一步 说明 token没有过期
            e.printStackTrace();
        }
        //从token中获取用户名，当用户查询通过后即可
        String username = claims.getSubject();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        return user.getId();
    }
}
