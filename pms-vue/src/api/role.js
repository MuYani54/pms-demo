import request from '../utils/request'

export function findAll () {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/role',
    method: 'get'
  })
}

export function search (role) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/role/search',
    method: 'get',
    params: role
  })
}

export function batchDelete (ids) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/role/batchDelete',
    method: 'delete',
    data: ids
  })
}

export function createRole (role) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/role',
    method: 'post',
    data: role
  })
}

export function findAllMenus () {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/menus/findAllMenus',
    method: 'get'
  })
}

export function findOne (id) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/role/' + id,
    method: 'get'
  })
}

export function editRole (role) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/role',
    method: 'put',
    data: role
  })
}
