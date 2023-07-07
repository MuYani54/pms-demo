<template>
  <div class="headbar" :class="$store.state.app.collapse?'position-collapse-left':'position-left'">
    <hamburger
      :toggle-click="toggleSideBar"
      :is-active="$store.state.app.collapse"
      class="hamburger-container" />

    <breadcrumb/>
    <el-dropdown
      class="avatar-container"
      :hide-timeout="300"
      trigger="click"
      :collapse="isCollapse">

      <!--右上角下拉菜单-->

      <span class="el-dropdown-link">
        <span>{{realName}}</span>
        <i class="el-icon-arrow-down el-icon--right"></i>
      </span>

      <el-dropdown-menu slot="dropdown" class="user-dropdown">
        <el-dropdown-item @click.native="goToHome">首页</el-dropdown-item>
        <router-link to="/sysSteam/person">
          <el-dropdown-item>
            修改个人信息
          </el-dropdown-item>
        </router-link>

        <el-dropdown-item @click.native="openDialog">修改密码</el-dropdown-item>
        <el-dropdown-item @click.native="logOut">退出登陆</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import Hamburger from '@/components/Hamburger/index.vue'
import Breadcrumb from '@/components/Breadcrumb'
import {logout} from '@/api/auth'
import {mapMutations, mapActions, mapState} from 'vuex'
import {setUserInfo, getUserInfo} from '~utils/sessionStorage'
export default {
  name: 'TitleBar',
  data () {
    return {
      obj: {},
      realName: '',
      isCollapse: true
    }
  },
  created () {
    this.getuser()
    this.$bus.$on('submit-event', () => {
      this.getuser()
      // this.$store.dispatch('updateRealName', this.realName)
    })
  },
  watch: {
    realName (newValue) {
      this.updateRealName(newValue)
    }
  },
  methods: {
    updateRealName (newRealName) {
      this.realName = newRealName
    },
    // async getuser () {
    //   this.obj = getUserInfo()
    //   try {
    //     const res = await this.getUser(this.obj.id)
    //     this.realName = res.realName
    //     // console.log(1, res.realName)
    //   } catch (error) {
    //     // 处理错误
    //     console.error(error)
    //   }
    // },
    getuser () {
      this.obj = getUserInfo()
      this.getUser(this.obj.id).then((res) => {
        this.realName = res.realName
        // console.log(1, res.realName)
      })
    },
    goToHome () {
      let res = getUserInfo()
      if (res) {
        setUserInfo(res)
        let role = res.role.name
        if (role === '系统管理员' || role === '物业管理员') {
          this.$router.push('/home/manage')
        } else if (role === '业主') {
          this.$router.push('/home/owner')
        } else {
          this.$router.push('/home')
        }
      }
    },
    clearData () {
      Object.assign(this.$data, this.$options.data())
    },
    handleEdit (id) {
      this.editDialog(id).then((data) => {
        this.resetForm = data
      })
    },
    logOut () {
      logout().then(() => {
        self.location.href = '/login'
        sessionStorage.clear()
      })
    },
    ...mapMutations({
      toggleSideBar: 'app/toggleSideBar'
    }),
    ...mapActions({
      'getUser': 'app/getUser',
      openDialog: 'app/openDialog'
    })
  },
  components: {
    Hamburger,
    Breadcrumb
  },
  computed: {
    ...mapState({
      loading: state => state.user.loading,
      data: state => state.user.table.data,
      total: state => state.user.table.total
    })
  },
  mounted () { // 页面初始化完成
    this.getuser()
  }
}
</script>

<style lang="less" scoped>

.el-menu{
  height:100%
}
.router-link-active{
  text-decoration: none;
}
a{
  text-decoration: none;
}
.user-dropdown{
  width: 200px;
  position:relative;
  text-align:center;
  top: 20px;
  font-size: 20px;
  vertical-align: text-bottom;
}
  .headbar{
    position: fixed;
    top: 0;
    right: 0;
    z-index: 1030;
    height: 60px;
    line-height: 60px;
    background-color: white;
    border-color: rgba(180, 190, 190, 0.8);
    border-left-width: 1px;
    border-left-style: solid;
    box-shadow: 0 1px 3px 0 rgba(0,0,0,.12), 0 0 3px 0 rgba(0,0,0,.04);
    .hamburger-container {
      line-height: 60px;
      height: 60px;
      float: left;
      padding: 0 10px;
    }
  }
  .avatar-container{
    height: 60px;
    cursor:pointer;
    display: inline-block;
    position: absolute;
    right: 35px;
    .el-dropdown-link{
      position:relative;
      text-align:center;
      //top: 20px;
      font-size: 20px;
    }
  }
  .position-left {
    left: 204px;
  }
  .position-collapse-left {
    left: 64px;
  }
</style>
