import {getUser} from '@/api/user'
import {search} from '@/api/house'

export default {
  namespaced: true,
  state: {
    user: {
      id: '',
      username: ''
    },
    table: {
      data: [],
      pageCount: 0, // 总页数
      pageSize: 7, // 每页大小
      currentPage: 0 // 当前页数
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
    // eslint-disable-next-line no-empty-pattern
    async getUser ({}, id) {
      const user = await getUser(id)
      // console.log(1, user)
      return user
    },
    async search ({commit}, pageable) {
      const data = await search(pageable.id, pageable.page, pageable.size)
      commit('FIND_ALL', data)
    }
  }
}
