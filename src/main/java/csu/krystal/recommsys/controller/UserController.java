package csu.krystal.recommsys.controller;


import csu.krystal.recommsys.common.util.ResponseVo;
import csu.krystal.recommsys.dto.LoginRequest;
import csu.krystal.recommsys.dto.RegisterRequest;
import csu.krystal.recommsys.dto.TokenPassJson;
import csu.krystal.recommsys.entity.User;
import csu.krystal.recommsys.common.service.ITokenService;
import csu.krystal.recommsys.common.service.IUserService;
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
@Tag(name = "用户管理接口",description = "实现登录,注册,用户查询")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ITokenService tokenService;

    @GetMapping("")
    @Operation(summary = "查询用户列表", description = "返回密码为空的用户列表数据")
    public ResponseVo<List<User>> getUserList() {
        List<User> userList = userService.getUserList();
        for(User user: userList){
            //防止用户密码泄露
            user.setPassword("");
        }
        return ResponseVo.success("获取用户列表成功", userList);
    }

    @PostMapping("/login")
    @Operation(summary = "登录", description = "根据传递的用户名,密码和role,登录成功返回 token")
    public ResponseVo<TokenPassJson> login(@RequestBody LoginRequest request) {
        TokenPassJson tokenPassJson = new TokenPassJson();
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
    @Operation(summary = "注册", description = "根据传递的用户名,密码,email,role,返回数据")
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

    @GetMapping("/{username}")
    @Operation(summary = "查询用户", description = "根据username获取用户信息")
    public ResponseVo<User> getUserByName(@PathVariable String username) {
        // 更安全的角度应该 再验证一下 token中的username对应的 权限或者是否是本人
        // 管理员可以查看所有用户信息， 普通用户只能查看自己的信息
        User user = userService.selectUserByName(username);
        if(user != null){
            user.setPassword("");
            return ResponseVo.success("获取用户信息", user);
        }
        return ResponseVo.error("获取用户信息失败");
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新用户信息", description = "此接口用于更新用户信息, 调用接口时传递参数id和修改后的user信息,注意此接口并不能修改密码，\" +\n" +
            "            \"用户名最好设置不可修改，或者前端设置修改了用户名也需要用户重新登录")
    public ResponseVo<User> updateUser(@PathVariable int id, @RequestBody User user) {

        if(userService.updateUserInfoById(id, user)){
            User newUser = userService.selectUserById(id);
            newUser.setPassword("");
            return ResponseVo.success("更新用户信息成功", newUser);
        }
        return ResponseVo.error("更新用户信息失败");
    }

    @GetMapping("/token/{token}")
    @Operation(summary = "从token获取用户信息", description = "实现从token中获取用户名进一步获取用户信息, 路径参数为token")
    public ResponseVo<User> getUserByToken(@PathVariable  String token) {
        //从 token中获取 user
        User user = tokenService.getUserFromToken(token);

        if(user != null){
            return ResponseVo.success("token获取用户信息成功", user);
        }
        return ResponseVo.error("token获取用户信息失败");

    }

    //删除好像没必要实现
//    @Delete("/{id}")
//    @ApiOperation(notes = "更新用户信息",value = "此接口用于更新用户信息, 调用接口时传递参数id和修改后的user信息,注意此接口并不能修改密码，" +
//            "用户名最好设置不可修改，或者前端设置修改了用户名就需要用户重新登录，原因是：token是通过username生成的")
//    public ResponseVo<User> deleteUser(@PathVariable int id) {
//        if(userService.deleteUserById(id)){
//            return ResponseVo.success("删除用户信息成功");
//        }
//        return ResponseVo.error("删除用户信息失败");
//    }

}
