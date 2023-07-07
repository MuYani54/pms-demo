<template>
   <div class="menu-bar-container">
        <!-- logo -->
        <div class="logo"
             :class="collapse?'menu-bar-collapse-width':'menu-bar-width'"
             @click="goToHome">
            <i v-if="collapse" class="pms-icon-logo"/>
            <img v-else src="@/assets/img/logo.png"/>
        </div>

       <!-- 导航菜单 -->
     <div class="sidebar-container">
        <el-menu  :default-active="this.$route.path" class="menu" background-color="#304156" text-color="#bfcbd9" :class="collapse?'menu-bar-collapse-width':'menu-bar-width'"
        active-text-color="#409EFF"  :collapse="collapse" :collapse-transition="false" :unique-opened="true">
           <!-- 导航菜单树组件，动态加载菜单 -->

             <menu-tree v-for="item in navTree" :key="item.id" :menu="item"></menu-tree>
        </el-menu>
     </div>
   </div>
</template>

<script  >
// eslint-disable-next-line standard/object-curly-even-spacing
import { mapState, mapMutations} from 'vuex'
import MenuTree from '@/components/MenuTree'
import {setUserInfo, getUserInfo} from '~utils/sessionStorage'
// import {getUserInfo} from '@/api/user'
export default {
  name: 'Sidebar',
  components: {
    MenuTree
  },
  data () {
    return {
      obj: {},
      screenWidth: document.body.clientWidth, // 屏幕宽度
      timer: false
    }
  },
  computed: mapState({
    collapse: state => state.app.collapse,
    navTree: state => state.menu.addRouters
  }),
  watch: {
    screenWidth (val) {
      if (!this.timer) {
        this.screenWidth = val
        if (this.screenWidth < 768) {
          this.hideMenu()
        }
        this.timer = true
        let that = this
        setTimeout(function () {
          that.timer = false
        }, 400)
      }
    }
  },
  methods: {
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
    ...mapMutations({
      'hideMenu': 'app/hideMenu'
    })
  },
  mounted () {
    // 判断菜单列表是否返回
    if (this.navTree !== undefined && this.navTree[0].children !== undefined) {
      const path = this.$router.currentRoute.path
      if (path === '/home') {
        this.$router.push(this.navTree[0].children[0].path)
      } else {
        this.$router.push(path)
      }
    }
    // 监听窗口大小
    window.onresize = () => {
      return (() => {
        this.screenWidth = document.body.clientWidth
      })()
    }
  }
}
</script>

<style lang="less" scoped>
.sidebar-container {
  overflow-y: auto; /* Enable vertical scrolling */
}
/* Customize the scrollbar for the sidebar container */
.sidebar-container::-webkit-scrollbar {
  width: 10px; /* Set the width of the scrollbar */
  background-color: transparent; /* Set the background color of the scrollbar track to transparent */
}

.sidebar-container::-webkit-scrollbar-thumb {
  background-color: rgb(255, 255, 255); /* Set the color of the scrollbar thumb with a lower opacity */
  border-radius: 5px; /* Add a border radius to the scrollbar thumb */
}

.sidebar-container::-webkit-scrollbar-thumb:hover {
  background-color: rgba(90, 94, 102, 0.09); /* Change the color of the scrollbar thumb when hovered with a lower opacity */
}
    .menu-bar-container{
      position: fixed;
      top: 0px;
      left: 0;
      bottom: 0;
      z-index: 1020;

      .el-menu {
        position:absolute;
        top: 60px;
        bottom: 0px;
        text-align: left;
      }

      .logo {
        position:absolute;
        top: 0px;
        height: 60px;
        line-height: 60px;
        background: #304156;
        border-right: solid 1px #e6e6e6;
        cursor:pointer;

        img {
          width: 205px;
          height: 60px;
          border-radius: 0px;
          margin: 0px 0px 0px 0px;
          float: left;
        }
      }
      .menu-bar-width {
          width: 205px !important;
      }
      .menu-bar-collapse-width {
          width: 65px !important;
      }
    }
</style>
