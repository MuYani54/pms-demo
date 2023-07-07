import {getUser} from '@/api/user'
import {search} from '@/api/repairs'
export default {
  namespaced: true,
  state: {
    table: {
      user: {
        id: '',
        username: ''
      },
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
    // eslint-disable-next-line no-empty-pattern
    async getUser ({}, id) {
      const user = await getUser(id)
      // console.log(1, user)
      return user
    },
    async search ({commit}, pageable) {
      const data = await search(pageable.userId, pageable.page, pageable.size)
      commit('FIND_ALL', data)
    }
  }
}
