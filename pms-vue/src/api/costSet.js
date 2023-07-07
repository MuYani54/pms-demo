import request from '../utils/request'

export function findCostAll (page, size) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/cost/all',
    method: 'GET',
    params: {
      'page': page,
      'size': size
    }
  })
}
export function findCostAllNoPage () {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/cost/costAll',
    method: 'GET'
  })
}

export function createCost (cost) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/cost',
    method: 'POST',
    data: cost
  })
}

export function deleteCost (id) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/cost/' + id,
    method: 'DELETE'
  })
}

export function findOne (id) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/cost/' + id,
    method: 'GET'
  })
}

export function updateCost (costSet) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/cost',
    method: 'PUT',
    data: costSet
  })
}

export function search (costName, page, size) {
  // eslint-disable-next-line new-cap
  return new request({
    url: '/cost/search',
    method: 'GET',
    params: {
      'costName': costName,
      'page': page,
      'size': size
    }
  })
}
