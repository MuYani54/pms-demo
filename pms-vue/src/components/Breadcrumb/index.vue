<!--<template>-->
<!--    <el-breadcrumb class="app-breadcrumb"  separator-class="el-icon-arrow-right">-->
<!--      <el-breadcrumb-item v-for="(item) in levelList" :key="item.path">-->
<!--        <a v-if="item.name=='首页'" @click.prevent="handleLink(item)">{{ item.name }}</a>-->
<!--        <span v-else class="no-redirect">{{ item.name }}</span>-->
<!--      </el-breadcrumb-item>-->
<!--    </el-breadcrumb>-->
<!--</template>-->
<template>
  <el-breadcrumb class="app-breadcrumb" separator-class="el-icon-arrow-right">
    <el-breadcrumb-item v-for="(item) in levelList" :key="item.path">
      <a v-if="item.name=='首页'" @click.prevent="handleLink(item)">{{ item.name }}</a>
      <span v-else class="no-redirect">{{ item.name }}</span>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script type="text/ecmascript-6">
// import {setUserInfo} from '~utils/sessionStorage'
import {getUserInfo} from '@/api/user'
export default {
  // data () {
  //   return {
  //     obj: {},
  //     levelList: []
  //   }
  // },
  data () {
    return {
      levelList: [],
      roleToPathMap: {
        '系统管理员': '/home/manage',
        '物业管理员': '/home/manage',
        '业主': '/home/owner'
      }
    }
  },
  watch: {
    $route () {
      this.getBreadcrumb()
    }
  },
  created () {
    this.getuser()
    this.getBreadcrumb()
  },
  methods: {
    getuser () {
      this.obj = getUserInfo()
    },
    // getBreadcrumb () {
    //   let matched = this.$route.matched.filter(item => {
    //     if (item.name) {
    //       return true
    //     }
    //   })
    //   const first = matched[0]
    //   if (first && first.name !== '首页') {
    //     matched = [{ path: '/home', name: '首页' }].concat(matched)
    //   }
    //   this.levelList = matched
    // },

    // handleLink (item) {
    //   if (this.obj.role === '系统管理员' || this.obj.role === '物业管理员') {
    //     this.$router.push('/home/manage')
    //   } else if (this.obj.role === '业主') {
    //     this.$router.push('/home/owner')
    //   } else {
    //     this.$router.push('/home')
    //   }
    // }
    getBreadcrumb () {
      let matched = []
      if (this.obj.role === '系统管理员' || this.obj.role === '物业管理员') {
        matched = [{
          path: '/home/manage',
          name: '管理员首页'
        }]
      } else if (this.obj.role === '业主') {
        matched = [{
          path: '/home/owner',
          name: '业主首页'
        }]
      } else {
        matched = this.$route.matched.filter(item => {
          if (item.name) {
            return true
          }
        })
        const first = matched[0]
        if (first && first.name === '首页') {
          matched.shift()
        }
      }
      this.levelList = matched
    },
    handleLink (item) {
      const role = this.obj.role
      let link = ''
      if (role === '系统管理员' || role === '物业管理员' || role === '业主') {
        link = this.roleToPathMap[role]
      } else {
        link = '/home'
      }
      this.$router.push(link)
    }
  }
}

</script>

<style lang="less" scoped>
    .el-breadcrumb{
        display: inline-block;
        font-size: 14px;
        line-height: 60px;
        margin-left: 10px;
        .no-redirect {
          color: #97a8be;
          cursor: text;
        }
    }
</style>
