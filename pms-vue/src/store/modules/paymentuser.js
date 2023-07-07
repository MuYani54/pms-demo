import {getUser} from '@/api/user'
import {report} from '@/api/recharge'
export default {
  namespaced: true,
  state: {
    table: {
      totalData: [],
      currData: [],
      size: 7,
      currpage: 1,
      total: 0
    }
  },
  mutations: {
    FIND_ALL (state, data) {
      state.table.totalData = data
      state.table.total = data.length
    },
    PAGE_HEPLER (state, currpage) {
      state.table.currpage = currpage
      state.table.currData = state.table.totalData.slice((currpage - 1) * state.table.size, currpage * state.table.size)
    }
  },
  actions: {
    // eslint-disable-next-line no-empty-pattern
    async getUser ({}, id) {
      const user = await getUser(id)
      return user
    },
    async report ({state, commit, dispatch}, userId) {
      const data = await report(userId)
      commit('FIND_ALL', data)
      dispatch('pageable', 1)
    },
    pageable ({commit}, currpage) {
      commit('PAGE_HEPLER', currpage)
    }
  }
}
