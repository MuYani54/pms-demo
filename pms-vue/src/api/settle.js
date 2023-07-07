import request from '../utils/request'

export function create (settle) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/settle',
    method: 'POST',
    data: settle
  })
}

export function report (userId) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/settle/report?userId=' + userId,
    method: 'GET'
  })
}
