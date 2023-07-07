import {getUserList,
  deleteUser,
  search,
  editUser,
  createUser,
  getUser,
  resetPassword} from '@/api/user'
export default{
  namespaced: true,
  state: {
    username: '',
    realName: '',
    role: '',
    diglogStatus: false,
    isAdd: true,
    loading: true,
    userList: [],
    table: {
      totalData: [],
      data: [],
      size: 10,
      currpage: 1,
      total: 0
    }
  },
  mutations: {
    FILL_USER_LIST (state, data) {
      state.userList = data
    },
    PAGE_HEPLER (state, currpage) {
      state.table.currpage = currpage
      state.table.data = state.table.totalData.slice((currpage - 1) * state.table.size, currpage * state.table.size)
    },
    FIND_ALL (state, data) {
      state.table.totalData = data
      state.table.total = data.length
    },
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
    openDialog ({ commit }) {
      commit('CHECK_DIALOG', true)
    },
    closeDialog ({ commit }) {
      commit('CHECK_DIALOG', false)
    },
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
    async findAll ({state, commit, dispatch}) {
      commit('CHECK_LOADING', true)
      const data = await getUserList()
      commit('FIND_ALL', data)
      commit('CHECK_LOADING', false)
      dispatch('pageable', 1)
    },
    // async findAll ({commit}, pageable) {
    //   const data = await findAll(pageable.page, pageable.size)
    //   commit('FIND_ALL', data)
    // },
    pageable ({ commit }, currpage) {
      commit('PAGE_HEPLER', currpage)
    },
    // eslint-disable-next-line no-empty-pattern
    async deleteUser ({}, id) {
      await deleteUser(id)
    },
    async search ({commit}, user) {
      commit('CHECK_LOADING', true)
      const data = await search(user)
      commit('FILL_DATA', data)
      commit('CHECK_LOADING', false)
      // dispatch('pageable', 1)
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
