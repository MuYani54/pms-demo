import request from '../utils/request'

export function findAll (page, size) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/account/all',
    method: 'GET',
    params: {
      'page': page,
      'size': size
    }
  })
}

export function search (userId) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/account/search?userId=' + userId,
    method: 'GET'
  })
}

export function payment (AccountDetail) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/account',
    method: 'POST',
    data: AccountDetail
  })
}

export function report (userId) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/account/report?userId=' + userId,
    method: 'GET'
  })
}
