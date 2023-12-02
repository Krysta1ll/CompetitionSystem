package csu.krystal.recommsys.controller;


import csu.krystal.recommsys.common.util.ResponseVo;
import csu.krystal.recommsys.dto.LoginRequest;
import csu.krystal.recommsys.dto.RegisterRequest;
import csu.krystal.recommsys.dto.TokenPassJson;
import csu.krystal.recommsys.entity.User;
import csu.krystal.recommsys.service.ITokenService;
import csu.krystal.recommsys.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "用户管理接口")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ITokenService tokenService;

    @GetMapping("")
    @Operation(summary = "查询所有用户")
    public ResponseVo<List<User>> getUserList() {
        List<User> userList = userService.getUserList();
        for(User user: userList){
            //防止用户密码泄露
            user.setPassword("");
        }
        return ResponseVo.success("获取用户列表成功", userList);
    }

    @PostMapping("/login")
    @Operation(summary = "登录")
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
    @Operation(summary = "注册接口")
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
