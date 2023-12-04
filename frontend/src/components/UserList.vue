<template>
    <div class="user-list">
        <hr>
        <el-button type="primary" @click="fetchUsers">刷新列表</el-button>
        <el-table :data="users" style="width: 100%">
            <el-table-column prop="id" label="ID" width="180"></el-table-column>
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="createTime" label="注册时间"></el-table-column>
            <el-table-column prop="updateTime" label="上次登录时间"></el-table-column>
            <el-table-column prop="role" label="角色">
                <template v-slot:="{ row }">
                    {{ row.role === 1 ? '管理员' : '普通用户' }}
                </template>
            </el-table-column>
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
            axios.get('/users')
                .then(response => {
                    this.users = response.data.data;
                })
                .catch(error => {
                    console.error(error);
                });
        }
    }
}
</script>

<style>
/* 在这里添加样式 */
</style>
