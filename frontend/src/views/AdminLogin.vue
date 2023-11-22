<template>
    <div class="admin-login">
        <el-card class="login-card">
            <div class="title">管理员登录</div>
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
    methods: {
        login(event) {
            event.preventDefault();
            axios.post('/auth/login', { username: this.username, password: this.password })
                .then(response => {
                    // 处理登录成功的逻辑
                    if(response.data.admin) {
                        this.saveLoginState(response.data.admin);
                        this.$router.push('/admin'); // 假设你的管理页面的路由是 '/admin'
                    } else {
                        console.log("登录失败，未获取到管理员信息");
                    }
                })
                .catch(error => {
                    // 处理登录失败的逻辑

                });}}}
</script>

<style scoped>
.admin-login {
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
