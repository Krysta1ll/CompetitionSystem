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
    created() {
        console.log("test")

        // 检查是否已经登录
        if (localStorage.getItem("admin-data")) {
            console.log("已登录")
            this.$message.info("上次登录未及时退出，请记得使用结束后进行登出")
            this.$router.push('/admin')
        }
    },
    methods: {
        saveLoginState(admin) {
            // 在这里实现保存登录状态的逻辑
            localStorage.setItem('admin-data', JSON.stringify(admin));
            // ...其他相关逻辑
        },
        login(event) {
            event.preventDefault();
            axios.post('/auth/login', {username: this.username, password: this.password})
                .then(response => {
                    // 处理登录成功的逻辑
                    const admin = response.data;
                    if (admin) {
                        this.saveLoginState(admin);
                        this.$router.push('/admin');
                    } else {
                        console.log("登录失败，未获取到管理员信息");
                        this.$message.error('登录失败!请检查用户名和密码')
                    }
                })
                .catch(error => {
                    // 处理登录失败的逻辑
                    this.$message.error('登录失败：' + error.message);
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
