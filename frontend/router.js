// router.js

import { createRouter, createWebHistory } from "vue-router";


// 导入你的组件


import AdminLogin from "@/views/AdminLogin.vue";
import Index from "@/views/Index.vue"


const routes = [
    { path: '/', component: Index },
    {path:'/login/admin' ,component: AdminLogin}
    // 在这里添加其他路由规则
];

const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router;
