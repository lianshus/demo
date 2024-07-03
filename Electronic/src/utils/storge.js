const localStorage = window.localStorage
const USER_KEY = 'user_info'
const TOKEN_KEY = 'token'

// 获取缓存
export function getUserInfo() {
  const userInfo = localStorage.getItem(USER_KEY);
  return (userInfo == null || userInfo == "undefined") ? null : JSON.parse(userInfo)
}

// 保存缓存
export function saveUserInfo(val) {
  localStorage.setItem(USER_KEY, JSON.stringify(val))
}

// 删除缓存
export function removeUserInfo() {
  localStorage.removeItem(USER_KEY)
}

// 获取token
export function getToken() {
  return localStorage.getItem(TOKEN_KEY)
}

// 保存token
export function saveToken(val) {
  localStorage.setItem(TOKEN_KEY, val)
}

// 删除token
export function removeToken() {
  localStorage.removeItem(TOKEN_KEY)
}

// 获取缓存
export function getStorage() {
  const userInfo = localStorage.getItem(USER_KEY);
  return (userInfo == null || userInfo == "undefined") ? null : JSON.parse(userInfo)
}

// 保存缓存
export function saveStorage(val) {
  localStorage.setItem(USER_KEY, JSON.stringify(val))
}

// 删除缓存
export function removeStorage() {
  localStorage.removeItem(USER_KEY)
}
