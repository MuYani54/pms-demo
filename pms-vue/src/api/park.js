import request from '../utils/request'

export function create (park) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/park',
    method: 'POST',
    data: park
  })
}

export function findAll (page, size) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/park/all',
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
    url: '/park/search?userId=' + id,
    method: 'GET',
    params: {
      'page': page,
      'size': size
    }
  })
}

export function updateStatus (park) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/park/updateStatus',
    method: 'PUT',
    data: park
  })
}
