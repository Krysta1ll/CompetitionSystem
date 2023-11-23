// router.js

import { createRouter, createWebHistory } from "vue-router";


// 导入你的组件


import AdminLogin from "@/views/AdminLogin.vue";
import Index from "@/views/Index.vue"
import AdminView from "@/views/AdminView.vue";



//判断是否已经登录
function adminIsLoggedIn() {
    return !!localStorage.getItem('admin-data'); // 假设登录令牌保存在 'user-token' 键下
}


const routes = [
    { path: '/', component: Index },
    {path:'/login/admin' ,component: AdminLogin},
    {path: '/admin',component: AdminView,meta: { requiresAuth: true } },
    // 在这里添加其他路由规则
];




const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router;
