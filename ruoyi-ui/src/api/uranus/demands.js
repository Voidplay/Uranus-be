import request from '@/utils/request'

// 查询需求列表
export function listDemands(query) {
  return request({
    url: '/uranus/demands/list',
    method: 'get',
    params: query
  })
}

// 查询需求详细
export function getDemands(demandsId) {
  return request({
    url: '/uranus/demands/' + demandsId,
    method: 'get'
  })
}

// 新增需求
export function addDemands(data) {
  return request({
    url: '/uranus/demands',
    method: 'post',
    data: data
  })
}

// 修改需求
export function updateDemands(data) {
  return request({
    url: '/uranus/demands',
    method: 'put',
    data: data
  })
}

// 删除需求
export function delDemands(demandsId) {
  return request({
    url: '/uranus/demands/' + demandsId,
    method: 'delete'
  })
}
