<template>
    <div class="home-page">
      <router-link to="adminLogin"> <el-button type="primary" >管理员登录</el-button></router-link>
        <el-button type="success">用户登录</el-button>
    </div>
</template>

<script>
import { ElButton } from 'element-plus'

export default {
    components: {
        ElButton
    }
}
</script>

<style>
.home-page {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
.el-button {
    margin: 0 10px;
}
</style>
