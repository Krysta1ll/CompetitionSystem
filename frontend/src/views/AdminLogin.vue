<template>
    <div class="admin-login">
        <el-card class="login-card">
            <div class="title">管理员登录</div>
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
                role: 1
            }
        };
    },
    created() {
        const token = localStorage.getItem('userToken');
        // 检查是否已经登录
        if (token) {
            console.log("已登录")
            this.$message.info("上次登录未及时退出，请记得使用结束后进行登出")
            this.$router.push('/admin')
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
                        localStorage.setItem('userToken', response.data.data.token);
                        const userInfo = {
                            username: response.data.data.username,
                        };
                        localStorage.setItem('userInfo', JSON.stringify(userInfo));
                        console.log(response.data);
                        // 登录成功后的页面跳转
                        this.$router.push("/admin");
                    } else {
                        // 如果status不是0或者data.success不是1，视为登录失败
                        this.$message.error(response.data.msg || '登录失败');
                    }
                })
                .catch(error => {
                    console.error('登录错误', error);
                    this.$message.error('登录过程中发生错误');
                });

        }
    }
}
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
