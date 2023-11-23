<template>
    <el-menu class="el-menu-vertical-demo">
        <el-menu-item index="1">
            <router-link to="/dashboard">仪表盘</router-link>
        </el-menu-item>
        <el-menu-item index="2">
            <router-link to="/settings">设置</router-link>
        </el-menu-item>
        <el-menu-item index="3">
            <router-link to="/users">用户管理</router-link>
        </el-menu-item>
        <!-- 其他菜单项... -->
    </el-menu>
</template>

<script>
export default {
    methods: {
        logout() {
            // 处理退出登录逻辑
            console.log('退出登录');
            // 清除登录状态，跳转到登录页面等
        }
    }
}
</script>

<style>
.el-menu-vertical-demo {
    width: 200px; /* 设置宽度 */
    height: 100vh; /* 充满整个视口高度 */
    border-right: 1px solid #dcdee2; /* 添加右边框 */
}
/* Navbar.vue 或相应的样式文件 */
.el-menu-vertical-demo .el-menu-item {
    color: #333; /* 改变字体颜色 */
    border-bottom: 1px solid #eaecef; /* 每项下边框 */
}

.el-menu-vertical-demo .el-menu-item:last-child {
    border-bottom: none; /* 最后一项无下边框 */
}

.router-link-active {
    background-color: #f0f0f0; /* 激活项背景色 */
}

.router-link-exact-active {
    color: #409eff; /* 当前激活路由的字体颜色 */
}

.el-menu-item > a {
    color: inherit; /* 继承父元素的字体颜色 */
    text-decoration: none; /* 移除下划线 */
}

</style>
