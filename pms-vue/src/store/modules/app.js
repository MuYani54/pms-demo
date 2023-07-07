// import {updatePassword} from '@/api/user'
import {getUserList, editUser, getUser, updatePassword} from '@/api/user'
export default {
  namespaced: true,
  state: {
    realName: '',
    collapse: false, // 菜单栏是否折叠
    loadMenus: false, // 菜单和路由加载状态
    diglogStatus: false // 密码框状态
  },
  getters: {
    collapses: state => { // 对应着上面state
      return state.collapse
    }
  },
  mutations: {
    setRealName (state, realName) {
      state.realName = realName
    },
    toggleSideBar (state) { // 改变收缩状态
      state.collapse = !state.collapse
    },
    hideMenu (state) { // 隐藏菜单
      state.collapse = true
    },
    setLoadMenus (state, loadMenus) { // 改变菜单和路由的加载状态
      state.loadMenus = loadMenus
    },
    CHECK_DIALOG (state, status) {
      state.diglogStatus = status
    }
  },
  actions: {
    updateRealName ({ commit }, realName) {
      commit('setRealName', realName)
    },
    // eslint-disable-next-line no-empty-pattern
    async getUser ({}, id) {
      const user = await getUser(id)
      return user
    },
    // eslint-disable-next-line no-empty-pattern
    async editUser ({}, user) {
      await editUser(user)
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
    updateLoadMenus ({ commit }, status) {
      return new Promise((resolve, reject) => {
        commit('setLoadMenus', status)
      })
    },
    openDialog ({commit}) {
      commit('CHECK_DIALOG', true)
    },
    closeDialog ({commit}) {
      commit('CHECK_DIALOG', false)
    },
    handleEdit (user) {
      this.editDialog(user.id).then((data) => {
        this.resetForm = data
      })
    },
    // eslint-disable-next-line no-empty-pattern
    async updatePassword ({}, user) {
      await updatePassword(user)
    }
  }
}
