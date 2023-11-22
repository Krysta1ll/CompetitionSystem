<template>
    <div class="login-container">
        <h2>Login</h2>
        <form @submit="login">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" v-model="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" v-model="password" required>
            </div>
            <button type="submit">Login</button>
        </form>
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
                    if(response.data === null){
                        console.log("sad")
                    }else console.log(response.data)
                })
                .catch(error => {
                    // 处理登录失败的逻辑

                });

            // 这里调用后端API进行登录验证，可以使用axios库
            // 例如：axios.post('/api/login', { username: this.username, password: this.password })
            // 处理登录成功和失败的逻辑
        },
    },
};
</script>

<style scoped>
.login-container {
    max-width: 300px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.form-group {
    margin-bottom: 10px;
}

label {
    font-weight: bold;
}

input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 5px;
}

button {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
}
</style>
