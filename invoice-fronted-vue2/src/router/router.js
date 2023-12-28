import Vue from 'vue'

import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    // 首页
    {
        path: '/',
        name: 'PlatformPage',
        component: () => import('../views/PlatformPage.vue'),
    },
    // 注册
    {
        path: '/register',
        name: 'RegisterPage',
        component: () => import('../views/RegisterPage.vue'),
    },
    // 登录
    {
        path: '/providers',
        name: 'Providers',
        component: () => import('../views/Providers/ProvidersLogin.vue'),
    },
    {
        path: '/buyers',
        name: 'Buyers',
        component: () => import('../views/Buyers/BuyersLogin.vue'),
    },
    // 导航
    // 采购商
    {
        path: '/buyersmanage',
        name: 'BuyersManage',
        component: () => import('../views/Buyers/BuyersManage.vue'),
        children:[
            {
                path: '/ordersmanage',
                name: 'OrdersManage',
                component: () => import('../views/Buyers/OrdersManage.vue'),
            },
            {
                path: '/createorder',
                name: 'CreateOrder',
                component: () => import('../views/Buyers/CreateOrder.vue'),
            }
        ]
    },
    // 供应商
    {
        path: '/providersmanage',
        name: 'ProvidersManage',
        component: () => import('../views/Providers/ProvidersManage.vue'),
        children:[
            {
                path: '/myorders',
                name: 'MyOrders',
                component: () => import('../views/Providers/MyOrders.vue'),
            },
            {
                path: '/notification',
                name: 'OrdersNotification',
                component: () => import('../views/Providers/OrdersNotification.vue'),
            }
        ]
    },
]

const router = new VueRouter({
    routes
})

export default router