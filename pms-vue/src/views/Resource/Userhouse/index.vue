<template>
  <div class="app-container">
<!--房产-->
    <div style="padding-bottom: 20px">
      <el-card class="box-card">
        <div slot="header" class="clearfix" style="text-align: center;">
          <span style="font-size: 20px">个人资产：房产</span>
        </div>
        <div>
          <!--房产表格渲染-->
          <el-table :data="tableData" size="small" border class="table-container" >
            <el-table-column  width="50px" type="index" align="center"  label="序号" show-overflow-tooltip></el-table-column>
            <el-table-column  prop="username" label="业主" align="center"></el-table-column>
            <el-table-column  prop="position" label="位置" align="center"></el-table-column>
            <el-table-column  prop="storey" label="类型" align="center">
              <template slot-scope="sc">
                <span v-if="sc.row.storey=== 'HEIGHT'">住户</span>
                <span v-if="sc.row.storey=== 'LOW'">商户</span>
              </template>
            </el-table-column>
            <el-table-column  prop="cellName" label="楼栋" align="center"></el-table-column>
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
      tableData: state => state.userhouse.table.data,
      pageCount: state => state.userhouse.table.pageCount,
      pageSize: state => state.userhouse.table.pageSize,
      currentPage: state => state.userhouse.table.currentPage
    })
  },
  mounted () {
  },
  methods: {
    searchData () {
      this.search({'page': 0, 'size': this.pageSize, 'id': this.obj.id})
    },
    getuser () {
      this.obj = getUserInfo()
    },
    ...mapActions({
      'search': 'userhouse/search'
    })
  }
}
</script>

<style scoped lang="less">
.centered-form {
  text-align: center;
}
</style>
