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
            username: '',
            password: '',
        };
    },
    created() {
        if (localStorage.getItem("user-data")) {
            this.$message.info("上次登录未及时退出，请记得使用结束后进行登出")
            this.$router.push('/user'); // 假设用户的主页面路由是 '/dashboard'
        }
    },
    methods: {
        saveLoginState(user) {
            localStorage.setItem('user-data', JSON.stringify(user));
            // ...其他相关逻辑
        },
        login(event) {
            event.preventDefault();
            axios.post('/auth/userLogin', {username: this.username, password: this.password})
                .then(response => {
                    // 处理登录成功的逻辑
                    const user = response.data;
                    if (user) {
                        this.saveLoginState(user);
                        this.$router.push('/user');
                    } else {
                        console.log("登录失败，未获取到用户信息");
                        this.$message.error('登录失败!请检查用户名和密码')
                    }
                })
                .catch(error => {
                    // 处理登录失败的逻辑
                    this.$message.error('登录失败：' + error.message);
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
