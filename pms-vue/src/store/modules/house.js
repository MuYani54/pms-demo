import {getUserList} from '@/api/user'
import {create, findAll, findOne, update, batchDelete, search} from '@/api/house'
export default {
  namespaced: true,
  state: {
    isAdd: true,
    diglogStatus: false,
    userList: [],
    table: {
      data: [],
      pageCount: 0, // 总页数
      pageSize: 7, // 每页大小
      currentPage: 0 // 当前页数
    }
  },
  mutations: {
    CHECK_DIALOG (state, status) {
      state.diglogStatus = status
    },
    CHECK_IS_ADD (state, status) {
      state.isAdd = status
    },
    FILL_USER_LIST (state, data) {
      state.userList = data
    },
    FIND_ALL (state, data) {
      state.table.data = data.content
      state.table.pageCount = data.totalPages
      state.table.pageSize = data.size
      state.table.currentPage = data.number + 1
    }
  },
  actions: {
    closeDialog ({commit}) {
      commit('CHECK_DIALOG', false)
    },
    addDialog ({commit}) {
      commit('CHECK_DIALOG', true)
      commit('CHECK_IS_ADD', true)
    },
    editDialog ({commit, dispatch}, id) {
      commit('CHECK_DIALOG', true)
      commit('CHECK_IS_ADD', false)
      const data = dispatch('findOne', id)
      return data
    },
    async getUserList ({commit}) {
      const data = await getUserList()
      commit('FILL_USER_LIST', data)
    },
    // eslint-disable-next-line no-empty-pattern
    async createHouse ({}, house) {
      await create(house)
    },
    // eslint-disable-next-line no-empty-pattern
    async editHouse ({}, house) {
      await update(house)
    },
    async findAll ({commit}, pageable) {
      const data = await findAll(pageable.page, pageable.size)
      commit('FIND_ALL', data)
    },
    // eslint-disable-next-line no-empty-pattern
    async findOne ({}, id) {
      // eslint-disable-next-line no-return-await
      return await findOne(id)
    },
    // eslint-disable-next-line no-empty-pattern
    async batchDelete ({}, ids) {
      // eslint-disable-next-line no-return-await
      return await batchDelete(ids)
    },
    async search ({commit}, pageable) {
      const data = await search(pageable.id, pageable.page, pageable.size)
      commit('FIND_ALL', data)
    }
  }
}
