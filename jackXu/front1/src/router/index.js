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
          name: 'home',
          component: () => import('@/views/home'),
          meta: [
            { name: '首页', url: '/' }
          ]
        }, 
        {
          path: '/market',
          name: 'market',
          component: () => import('@/views/market')
        },
        {
          path: '/user',
          name: 'user',
          component: () => import('@/views/user')
        },
        {
          path: '/sell_page',
          name: 'home',
          component: () => import('@/views/sell_page'),
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
