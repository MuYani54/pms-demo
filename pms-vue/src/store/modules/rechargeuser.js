import {getUser} from '@/api/user'
import {payment, search} from '@/api/recharge'
export default {
  namespaced: true,
  state: {
    table: {
      data: [],
      pageCount: 0, // 总页数
      pageSize: 7, // 每页大小
      currentPage: 1 // 当前页数
    }
  },
  mutations: {
    FIND_ALL (state, data) {
      state.table.data = data.content
      state.table.pageCount = data.totalPages
      state.table.pageSize = data.size
      state.table.currentPage = data.number + 1
    }
  },
  actions: {
    async search ({commit}, recharge) {
      const data = await search(recharge.userId, recharge.page, recharge.size)
      commit('FIND_ALL', data)
    },
    // eslint-disable-next-line no-empty-pattern
    async payment ({}, recharge) {
      await payment(recharge)
    },
    // eslint-disable-next-line no-empty-pattern
    async getUser ({}, id) {
      const user = await getUser(id)
      return user
    }
  }
}
