import Vue from 'vue'
import VueRouter from 'vue-router'
import nprogress from 'nprogress'
import store from '@/store';
Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/login')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/views/register')
    },
    {
      path: '',
      component: () => import('@/views/layout'),
      children: [
        {
          path: '/',
          redirect:'/market',
        },
        {
          path: '/market',
          name: 'market',
          component: () => import('@/views/market'),
        },
        {
          path: '/detail/:id',
          name: 'detail',
          component: () => import('@/components/Detail.vue'),
          //路由参数作为组件的props
          props:true,
        },
        {
          path: '/order',
          name: 'order',
          component: () => import('@/views/orderManage'),
          children: [
            {
              path: '/orders',
              name: 'orders',
              component: () => import('@/views/orderManage/components/orders.vue'),
            },
            {
              path: '/history',
              name: 'history',
              component: () => import('@/views/orderManage/components/history.vue'),
            }
          ]
        },
        {
          path: '/transaction',
          name: 'transaction',
          component: () => import('@/views/transaction'),
        },
        {
          path: '/transfer',
          name: 'transfer',
          component: () => import('@/views/transfer'),
        },
      ]
    },

  ]

})

router.beforeEach((to, from, next) => {
  // 开启进度条
  nprogress.start()
  if ((to.path === "/login" || to.path === "/register")) {
    next();
  } else {
    if (store.state.token == null || store.state.token == undefined) {
      next("/login");
    } else {
      next();
    }
  }
})

// 后置操作rou
router.afterEach((to, from) => {
  // 关闭进度条
  nprogress.done()
})

export default router
