<template>
    <div class="register-page">
        <el-card class="register-card">
            <div class="title">用户注册</div>
            <el-form ref="registerForm" @submit.native.prevent="register">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="password"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="email"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" native-type="submit">注册</el-button>
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
            email: ''

        };
    },
    methods: {
        register(event) {
            event.preventDefault();
            axios.post('/user/register', {username: this.username, password: this.password, email: this.email})
                .then(response => {
                    // 处理注册成功的逻辑
                    const user = response.data;
                    if (user) {
                        this.$message.success('注册成功!')
                        this.$router.push('/userLogin');
                    } else {
                        console.log("注册失败，未获取到用户信息");
                        this.$message.error('注册失败!请检查用户名和密码')
                    }

                })

        }
    }
};
</script>

<style scoped>
.register-page {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background: #f5f5f5;
}

.register-card {
    width: 400px;
    padding: 20px;
}

.title {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
}
</style>
