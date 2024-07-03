import Vue from 'vue'
import Vuex from 'vuex'
import * as st from '@/utils/storge.js'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    user: st.getUserInfo(),
    token:st.getToken()
  },
  mutations: {
    setUser (state, data) {
      // 修改仓库中的状态
      state.user = data
      // 重新存入缓存
      st.saveUserInfo(state.user)
    },
    removeUser () {
      // 删除用户信息
      st.removeUserInfo()
    },
    // 设置token
    setToken(state, data){
      state.token = data
      st.saveToken(state.token)
    },
    // 删除token
    removeToken () {
      st.removeToken()
    },
  },
  actions: {
  },
  modules: {
  }
})

export default store
