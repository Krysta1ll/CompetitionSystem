// router.js

import { createRouter, createWebHistory } from "vue-router";


// 导入你的组件


import login from "@/views/login.vue";



const routes = [
    { path: '/test', component: login },
    // 在这里添加其他路由规则
];

const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router;
