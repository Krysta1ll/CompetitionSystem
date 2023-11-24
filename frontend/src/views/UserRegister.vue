<template>
    <div class="register-page">
        <el-card class="register-card">
            <div class="title">用户注册</div>
            <el-form ref="registerForm" :model="registerForm" @submit.native.prevent="register">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="registerForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="registerForm.password"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="registerForm.email"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" native-type="submit">注册</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
export default {
    data() {
        return {
            registerForm: {
                username: '',
                password: '',
                email: ''
            }
        };
    },
    methods: {
        register() {
            console.log('注册信息:', this.registerForm);
            // 在这里添加注册逻辑
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
