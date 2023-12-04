<template>
    <div class="user-login">
        <el-card class="login-card">
            <div class="title">用户登录</div>
            <el-form @submit.native.prevent="login" ref="loginForm" :model="loginForm" >
                <el-form-item label="用户名">
                    <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="loginForm.password" type="password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button native-type="submit" block>登录</el-button>
                </el-form-item>
                <el-form-item>
                    <div class="register-link">
                        没有账号？
                        <el-button type="text" @click="goToRegister">立即注册</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            loginForm: {
                username: '',
                password: '',
                role: 0
            }
        };
    },
    created() {
        const token = localStorage.getItem('userToken_user');
        // 检查是否已经登录
        if (token) {
            console.log("已登录")
            this.$message.info("上次登录未及时退出，请记得使用结束后进行登出")
            this.$router.push('/user')
        }
    },
    methods: {
        login() {
            this.$axios.post('/users/login', this.loginForm)
                .then(response => {
                    if (response.data.status === 0 && response.data.data && response.data.data.success === 1) {
                        this.$message.success(response.data.msg);
                        console.log('Token:', response.data.data.token);
                        // 保存 token 到 localStorage
                        localStorage.setItem('userToken_user', response.data.data.token);
                        // 设置全局 Axios 请求头
                        const token = localStorage.getItem('userToken_user');
                        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

                        // 登录成功后的页面跳转
                        this.$router.push("/user");
                    } else {
                        // 如果status不是0或者data.success不是1，视为登录失败
                        this.$message.error(response.data.msg || '登录失败');
                    }
                })
                .catch(error => {
                    console.error('登录错误', error);
                    this.$message.error('登录过程中发生错误');
                });


        },
        goToRegister() {
            this.$router.push('/userRegister'); // 注册页面的路由是 '/register'
        }
    }
}
</script>

<style scoped>
.user-login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background: #f5f5f5;
}

.login-card {
    width: 400px;
    padding: 20px;
}

.title {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
}
</style>
