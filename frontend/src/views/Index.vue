<template>
    <div class="home-page">
        <h1 class="page-title">竞争性关键词评分系统</h1>
        <div class="button-group">
            <router-link to="adminLogin">
                <el-button type="primary" class="login-button">管理员登录</el-button>
            </router-link>
            <router-link to="userLogin">
                <el-button type="success" class="login-button">用户登录</el-button>
            </router-link>
        </div>
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

<style scoped>
.home-page {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f2f6fc; /* 背景颜色 */
    border: 1px solid #ebebeb; /* 整个页面的边框 */
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* 阴影效果 */
}

.page-title {
    font-size: 2em;
    margin-bottom: 20px;
    color: #333; /* 标题颜色 */
}

.button-group {
    display: flex;
    gap: 15px; /* 按钮间距 */
}

.login-button {
    width: 150px; /* 按钮宽度 */
    font-size: 1em; /* 字体大小 */
    border: 1px solid #dcdcdc; /* 按钮边框 */
}
</style>
