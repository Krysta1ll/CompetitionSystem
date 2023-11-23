<template>
    <div class="user-list">
        <el-table :data="users" style="width: 100%">
            <el-table-column prop="id" label="ID" width="180"></el-table-column>
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="role" label="角色"></el-table-column>
        </el-table>
    </div>
</template>

<script>
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
            // 在这里添加获取用户数据的逻辑
            // 例如，使用 axios 发送请求到你的后端API
            // this.users = 获取到的用户数据
        }
    }
}
</script>

<style>
/* 在这里添加样式 */
</style>
