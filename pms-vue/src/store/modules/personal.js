// import {getUserList, deleteUser, search, editUser, createUser, getUser, resetPassword} from '@/api/personal'
import {getUserList, search, createUser, resetPassword, editUser, getUser} from '@/api/personal'
export default{
  namespaced: true,
  state: {
    username: '',
    realName: '',
    role: '',
    diglogStatus: false,
    isAdd: true,
    loading: true,
    table: {
      data: [],
      total: 0
    }
  },
  mutations: {
    SET_INFO (state, info) {
      // eslint-disable-next-line no-unused-expressions,no-sequences
      state.username = info.username,
      state.realName = info.realName,
      state.role = info.role
    },
    CHECK_DIALOG (state, status) {
      state.diglogStatus = status
    },
    CHECK_LOADING (state, status) {
      state.loading = status
    },
    FILL_DATA (state, data) {
      state.table.data = data
      state.table.total = data.length
    },
    CHECK_IS_ADD (state, status) {
      state.isAdd = status
    }
  },
  actions: {
    addDialog ({commit, dispatch}) {
      dispatch('openDialog')
      commit('CHECK_IS_ADD', true)
    },
    async editDialog ({commit, dispatch}, id) {
      dispatch('openDialog')
      commit('CHECK_IS_ADD', false)
      const user = await dispatch('getUser', id)
      return user
    },
    async findAll ({commit}) {
      commit('CHECK_LOADING', true)
      const data = await getUserList()
      commit('FILL_DATA', data)
      commit('CHECK_LOADING', false)
    },
    async search ({commit}, user) {
      commit('CHECK_LOADING', true)
      const data = await search(user)
      commit('FILL_DATA', data)
      commit('CHECK_LOADING', false)
    },
    // eslint-disable-next-line no-empty-pattern
    async createUser ({}, user) {
      await createUser(user)
    },
    // eslint-disable-next-line no-empty-pattern
    async resetPassword ({}, userId) {
      await resetPassword(userId)
    },
    // eslint-disable-next-line no-empty-pattern
    async editUser ({}, user) {
      await editUser(user)
    },
    // eslint-disable-next-line no-empty-pattern
    async getUser ({}, id) {
      const user = await getUser(id)
      return user
    }
  }
}
