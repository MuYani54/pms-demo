<template>
  <div class="app-container">
<!--    <div slot="header" class="clearfix" style="text-align: center;padding-bottom: 20px;">-->
<!--      <span style="font-size: 30px">个人资产</span>-->
<!--    </div>-->
<!--门禁-->
    <div style="padding-bottom: 20px">
      <el-card class="box-card">
        <div slot="header" class="clearfix" style="text-align: center;">
          <span style="font-size: 20px">个人资产：门禁</span>
        </div>
        <div>
          <!--门禁表格渲染-->
          <el-table :data="tableData" size="small" border class="table-container" >
            <el-table-column  width="50px" type="index" align="center"  label="序号" show-overflow-tooltip></el-table-column>
            <el-table-column  prop="username" label="业主" align="center"></el-table-column>
            <el-table-column  prop="doorType" label="开门方式" align="center" :formatter="doorType"/>
            <el-table-column  prop="expireDate" label="到期时间" align="center"></el-table-column>
          </el-table>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">

import {mapState, mapActions} from 'vuex'
import {getUserInfo} from '../../../utils/sessionStorage'
export default {
  // 获取用户信息
  created () {
    this.getuser()
    this.searchData()
    // console.log(3, this.doortableData)
    // console.log(4, this.obj.id)
  },
  data () {
    return {
      obj: {},
      user: {
        id: '',
        username: ''
      }
    }
  },
  // 计算属性 监听vuex中的数据
  computed: {
    ...mapState({
      tableData: state => state.userdoor.table.data,
      pageCount: state => state.userdoor.table.pageCount,
      pageSize: state => state.userdoor.table.pageSize,
      currentPage: state => state.userdoor.table.currentPage
    })
  },
  mounted () {
  },
  methods: {
    searchData () {
      this.search({'userId': this.obj.id, 'page': 0, 'size': this.pageSize})
    },
    getuser () {
      this.obj = getUserInfo()
    },
    ...mapActions({
      'search': 'userdoor/search'
    }),
    // 门禁类型
    doorType (row, column) {
      if (row.doorType === 'QR') {
        return '二维码'
      } else if (row.doorType === 'NFC') {
        return '门禁卡'
      }
    }
  }
}
</script>

<style scoped lang="less">
.centered-form {
  text-align: center;
}
</style>
