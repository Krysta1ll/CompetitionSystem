<template>
    <div class="dashboard">
        <navbar></navbar>
        <!--      导航栏-->
        <div class="main-content">
            <div class="top-bar">竞争性关键词评分系统后台管理平台
                <div class="admin-username">
                    您好， {{ adminName }}
                </div>

            </div>
            <!-- 其他顶部栏内容 -->

            <div class="content">
                <!-- 页面内容 -->
                <div>
                    <el-breadcrumb separator="/">
                        <el-breadcrumb-item v-for="(item, index) in breadcrumbList" :key="index">
                            <router-link :to="item.path">{{ item.name }}</router-link>
                        </el-breadcrumb-item>
                    </el-breadcrumb>

                    <router-view></router-view>
                    <!-- 其他内容 -->
                </div>


            </div>
            <div class="footer">
                © 2023 Krystal. All rights reserved.
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '@/components/Navbar.vue';
import {ElBreadcrumb, ElBreadcrumbItem} from "element-plus";

export default {
    name: "AdminView",
    components: {
        Navbar, ElBreadcrumb,
        ElBreadcrumbItem
    },
    data() {
        return {
            admin: '',
            adminName: '',
            breadcrumbList: []
        };
    },
    mounted() {

        this.admin = JSON.parse(localStorage.getItem('admin-data'));
        this.adminName = this.admin.username;

    },
    watch: {
        $route: {
            immediate: true,
            handler(to) {
                this.breadcrumbList = to.matched.map(route => ({
                    name: route.meta.title, // 假设你在路由元信息中设置了标题
                    path: route.path
                }));
            }
        }
    }

}
</script>

<style scoped>
.dashboard {
    display: flex;
    height: 100vh; /* 使用整个视口高度 */
}

.main-content {
    flex: 1; /* 占据剩余空间 */
    display: flex;
    flex-direction: column; /* 垂直布局 */
}

.top-bar {
    height: 50px; /* 顶部栏高度 */
    background-color: #f5f5f5; /* 顶部栏背景颜色 */
    padding: 0 20px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
    display: flex;
    justify-content: space-between; /* 使内容分布在两端 */
    align-items: center;
}

.content {
    flex: 1; /* 占据剩余空间 */
    overflow-y: auto; /* 需要时显示滚动条 */
    padding: 20px; /* 内边距 */
}

.admin-username {
    margin-right: 20px; /* 右边距 */
}

.footer {
    text-align: center;
    padding: 10px 0;
    background-color: #f5f5f5; /* 底部栏背景颜色 */
    border-top: 1px solid #dcdee2; /* 上边框 */
    font-size: 0.8em; /* 字体大小 */
}

</style>