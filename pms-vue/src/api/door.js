import request from '../utils/request'

export function create (door) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/door',
    method: 'POST',
    data: door
  })
}

export function findAll (page, size) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/door/all',
    method: 'GET',
    params: {
      'page': page,
      'size': size
    }
  })
}

export function search (id, page, size) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/door/search?userId=' + id,
    method: 'GET',
    params: {
      'page': page,
      'size': size
    }
  })
}

export function updateStatus (door) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/door/updateStatus',
    method: 'PUT',
    data: door
  })
}
