import {getUser} from '@/api/user'
import {report} from '@/api/settle'
export default {
  namespaced: true,
  state: {
    table: {
      data: [],
      total: 0
    }
  },
  mutations: {
    FIND_ALL (state, data) {
      state.table.data = data
      state.table.total = data.length
    }
  },
  actions: {
    async report ({commit}, repair) {
      const data = await report(repair)
      commit('FIND_ALL', data)
    },
    // eslint-disable-next-line no-empty-pattern
    async getUser ({}, id) {
      const user = await getUser(id)
      return user
    }
  }
}
