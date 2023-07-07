import {getUserList} from '@/api/user'
import {report} from '@/api/settle'
export default {
  namespaced: true,
  state: {
    userList: [],
    table: {
      totalData: [],
      data: [],
      size: 7,
      currpage: 1,
      total: 0
    }
  },
  mutations: {
    PAGE_HEPLER (state, currpage) {
      state.table.currpage = currpage
      state.table.data = state.table.totalData.slice((currpage - 1) * state.table.size, currpage * state.table.size)
    },
    FIND_ALL (state, data) {
      // state.table.data = data
      state.table.totalData = data
      state.table.total = data.length
    },
    FILL_USER_LIST (state, data) {
      state.userList = data
    }
  },
  actions: {
    pageable ({commit}, currpage) {
      commit('PAGE_HEPLER', currpage)
    },
    // async report ({commit}, repair) {
    //   const data = await report(repair)
    //   commit('FIND_ALL', data)
    // },
    async report ({state, commit, dispatch}, userId) {
      const data = await report(userId)
      commit('FIND_ALL', data)
      dispatch('pageable', 1)
    },
    async getUserList ({commit}) {
      const data = await getUserList()
      commit('FILL_USER_LIST', data)
    }
  }
}
