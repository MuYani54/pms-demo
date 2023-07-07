import request from '../utils/request'

export function create (repairs) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/repairs',
    method: 'POST',
    data: repairs
  })
}

export function findAllDto (pageable) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/repairs',
    method: 'GET',
    params: pageable
  })
}

export function search (id, page, size) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/repairs/search?userId=' + id,
    method: 'GET',
    params: {
      'page': page,
      'size': size
    }
  })
}

export function startDispatch (repair) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/repairs/startDispatch',
    method: 'PUT',
    data: repair
  })
}

export function endDispatch (repair) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/repairs/endDispatch',
    method: 'PUT',
    data: repair
  })
}

export function report (userId) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/repairs/report?userId=' + userId,
    method: 'GET'
  })
}
