<template>
    <div class="user-list">
        <hr>
        <el-button type="primary" @click="fetchUsers">刷新列表</el-button>
        <el-table :data="users" style="width: 100%">
            <el-table-column prop="id" label="ID" width="180"></el-table-column>
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="createdAt" label="注册时间"></el-table-column>
            <el-table-column prop="updatedAt" label="上次登录时间"></el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            users: []
        };
    },
    mounted() {
        this.fetchUsers();
    },
    methods: {
        fetchUsers() {
            axios.get('/user/getUserList')
                .then(response => {
                    this.users = response.data;
                    console.log(this.users);
                })
                .catch(error => {
                    console.log(error);
                });
        }
    }
}
</script>

<style>
/* 在这里添加样式 */
</style>
