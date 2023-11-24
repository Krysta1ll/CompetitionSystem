<template>
    <div class="user-login">
        <el-card class="login-card">
            <div class="title">用户登录</div>
            <el-form @submit="login">
                <el-form-item label="用户名">
                    <el-input v-model="username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="password" type="password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button native-type="submit" block>登录</el-button>
                </el-form-item>
                <el-form-item>
                    <div class="register-link">
                        没有账号？<el-button type="text" @click="goToRegister">立即注册</el-button>
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
            username: '',
            password: '',
        };
    },
    created() {
        if(localStorage.getItem("user-data")){
            this.$router.push('/dashboard'); // 假设用户的主页面路由是 '/dashboard'
        }
    },
    methods: {
        saveLoginState(user) {
            localStorage.setItem('user-data', JSON.stringify(user));
            // ...其他相关逻辑
        },
        login(event) {
            event.preventDefault();
            // 登录逻辑...
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
