package csu.krystal.recommsys.controller;


import csu.krystal.recommsys.common.util.ResponseVo;
import csu.krystal.recommsys.dto.LoginRequest;
import csu.krystal.recommsys.dto.RegisterRequest;
import csu.krystal.recommsys.dto.TokenPassJson;
import csu.krystal.recommsys.entity.User;
import csu.krystal.recommsys.service.ITokenService;
import csu.krystal.recommsys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
@RestController
@RequestMapping("/users")
@Api(tags = "用户管理接口",description = "实现登录,注册,用户查询")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ITokenService tokenService;

    @GetMapping("")
    @ApiOperation(notes = "查询用户列表",value = "返回密码为空的用户列表数据")
    public ResponseVo<List<User>> getUserList() {
        List<User> userList = userService.getUserList();
        for(User user: userList){
            //防止用户密码泄露
            user.setPassword("");
        }
        return ResponseVo.success("获取用户列表成功", userList);
    }

    @PostMapping("/login")
    @ApiOperation(notes = "登录",value = "根据传递的用户名,密码和role,登录成功返回 token")
    public ResponseVo<TokenPassJson> login(@RequestBody LoginRequest request) {
        TokenPassJson tokenPassJson = new TokenPassJson();
        String username = request.getUsername();
        if(userService.login(request)){
            String token = tokenService.createToken(request.getUsername());
            tokenPassJson.setToken(token);
            tokenPassJson.setSuccess(1);
            return ResponseVo.success("用户登录成功", tokenPassJson);
        }
        tokenPassJson.setSuccess(0);
        tokenPassJson.setToken(null);
        return ResponseVo.error("用户登录失败", tokenPassJson);
    }


    @PostMapping("/register")
    @ApiOperation(notes = "注册",value = "根据传递的用户名,密码,email,role,返回数据")
    public ResponseVo<User> register(@RequestBody RegisterRequest request) {
        User user = userService.selectUserByName(request.getUsername());
        if(user != null){
            return ResponseVo.error("该用户名已存在");
        }
        else if (userService.register(request)){
            return ResponseVo.success("用户注册成功");
        }else {
            return ResponseVo.error("用户注册失败");
        }
    }

}
