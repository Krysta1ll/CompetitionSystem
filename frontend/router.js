// router.js

import {createRouter, createWebHistory} from "vue-router";


// 导入你的组件


import AdminLogin from "@/views/AdminLogin.vue";
import Index from "@/views/Index.vue"
import AdminView from "@/views/AdminView.vue";
import UserList from "@/components/UserList.vue";
import UserView from "@/views/UserView.vue";
import UserLogin from "@/views/UserLogin.vue";
import UserRegister from "@/views/UserRegister.vue";


//判断是否已经登录


const routes = [
    {path: '/', component: Index},
    {
        path: '/admin', component: AdminView, meta: {title: '主页'}, children: [
            {path: '/userList', component: UserList, meta: {title: '用户列表'}}
        ]
    },
    {path: '/user', component: UserView},
    {path: '/adminLogin', component: AdminLogin},
    {path: '/userLogin', component: UserLogin},
    {path: '/userRegister', component: UserRegister}




    // 在这里添加其他路由规则
];


const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router;
