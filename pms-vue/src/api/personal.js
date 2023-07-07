import request from '../utils/request'
// 获取登录用户信息
export function getUserInfo () {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

export function loadCurrMenu () {
  return request({
    url: '/menus/menuList',
    method: 'get'
  })
}

export function getUserList () {
  return request({
    url: '/user/list',
    method: 'get'
  })
}

export function createUser (user) {
  return request({
    url: '/user',
    method: 'post',
    data: user
  })
}
// 删除
export function deleteUser (id) {
  return request({
    url: '/user/' + id,
    method: 'delete'
  })
}
// 查询
export function search (user) {
  return request({
    url: '/user/search',
    method: 'get',
    params: user
  })
}
// 编辑
export function editUser (user) {
  return request({
    url: '/user/edit',
    method: 'put',
    data: user
  })
}
// 获取用户信息
export function getUser (id) {
  return request({
    url: '/user/' + id,
    mothod: 'get'
  })
}

export function updatePassword (user) {
  return request({
    url: '/user/updatePassword',
    method: 'post',
    data: user
  })
}

export function resetPassword (userId) {
  return request({
    url: '/user/' + userId,
    method: 'PUT'
  })
}
