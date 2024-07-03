import axios from 'axios'
import store from '@/store'
import router from '@/router'
import { Loading } from 'element-ui';
// import JSONbig from 'json-bigint'
import { Message } from 'element-ui' //引入element-ui库


let loadingInstance;

const request = axios.create({
  baseURL: ' http://localhost:8080/'
})
request.defaults.timeout = 10000;

function closeLoading() {
  if (loadingInstance !== undefined) {
    loadingInstance.close();
  }
}
// 添加请求拦截器
request.interceptors.request.use((config) => {
  if (config.url !== "/block") {
    loadingInstance = Loading.service({ fullscreen: true });
  }
  // 登录和注册不用token
  if (config.url == "/user/login" || config.url == "/user/register") {
    return config
  }
  if (store.state.token != null) {
    config.headers.Authorization = store.state.token;
  }
  return config;



}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error)
})

// 添加响应拦截器
request.interceptors.response.use((response) => {
  if (loadingInstance !== undefined) {
    loadingInstance.close();
  }
  if (response.data.resultCode === 401) {
    Message.error("认证失败");
    closeLoading();
    return Promise.reject(response.data.message);;
  }
  if (response.data.resultCode === 400 || response.data.resultCode === 40002) {
    Message.error(response.data.message);
    closeLoading();
    return Promise.reject(response.data.message);;
  }
  if (response.data.resultCode === 403) {
    Message.error("账户或密码不正确");
    closeLoading();
    router.push("/login")
    return Promise.reject(response.data.message);;
  }
  // 对响应数据做点什么
  return response.data
}, function (error) {
  // 对响应错误做点什么
  closeLoading();
  Message.error("服务器异常");

  return Promise.reject(error)
})



export default request
