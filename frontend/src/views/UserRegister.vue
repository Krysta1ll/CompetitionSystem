<template>
    <div class="register-page">
        <el-card class="register-card">
            <div class="title">用户注册</div>
            <el-form ref="registerForm" @submit.native.prevent="register" :model="this">
                <el-form-item label="用户名" prop="username" :rules="{ required: true, message: '请输入用户名', trigger: 'blur' }">
                    <el-input v-model="username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password" :rules="{ required: true, message: '请输入密码', trigger: 'blur' }">
                    <el-input type="password" v-model="password"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email" :rules="{ required: true, message: '请输入邮箱', trigger: 'blur' }">
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
            email: '',
            role:0,

        };
    },
    methods: {

            register() {
                // 前端简单验证
                if (!this.username || !this.password || !this.email) {
                    this.$message.error('请填写所有必填信息');
                    return;
                }
                // 发送注册请求
                axios.post('/users/register', {
                    username: this.username,
                    password: this.password,
                    email: this.email,
                    role: 0
                })
                    .then(response => {
                        if (response.data && response.data.status===0) {
                            // 假设后端返回的成功状态字段是 success
                            this.$message.success('注册成功!');
                            this.$router.push('/userLogin'); // 注册成功后跳转到登录页面
                        } else {
                            this.$message.error(response.data.msg || '注册失败，请重试');
                        }
                    })
                    .catch(error => {
                        console.error('注册错误:', error);
                        this.$message.error('注册过程中发生错误');

                    });
        }

    }};
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
