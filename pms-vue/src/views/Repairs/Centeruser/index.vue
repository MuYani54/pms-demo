<template>
  <div class="app-container">
    <div>
      <div slot="header" class="clearfix" style="text-align: center;padding-bottom: 20px">
        <span style="font-size: 20px">报修记录(未完成)</span>
      </div>
      <div>
        <el-table :data="tableData" size="small" border class="table-container">
          <el-table-column  width="50px" type="index" align="center"  label="序号" show-overflow-tooltip>  </el-table-column>
          <el-table-column  prop="repairsBillNo" label="报修单号" align="center"/>
          <el-table-column  prop="repairsType" label="报修问题" align="center"/>
          <el-table-column  prop="repairsDate" label="报修时间" align="center"/>
          <el-table-column  prop="realName" label="报修人" align="center"/>
          <el-table-column  prop="repairsStatus" label="订单状态" align="center">
             <template slot-scope="scope">
               <span v-if="scope.row.repairsStatus==='NOT'">等待派单</span>
               <span v-else-if="scope.row.repairsStatus==='ING'">正在维修</span>
             </template>
        </el-table-column>
        </el-table>
      </div>
    </div>
    <div style="padding-bottom: 20px">
    <!--分页组件-->
     <el-pagination
        background
        :page-count="pageCount"
        :page-size="pageSize"
        :current-page="currentPage"
        style="margin-top:8px; float:right"
        layout="prev,pager,next"
        @current-change="pageChange"/>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import {mapState, mapActions} from 'vuex'
import {getUserInfo} from '../../../utils/sessionStorage'
export default {
  // name: 'Centeruser',
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
  computed: {
    ...mapState({
      tableData: state => state.repairsuser.table.data,
      pageCount: state => state.repairsuser.table.pageCount,
      pageSize: state => state.repairsuser.table.pageSize,
      currentPage: state => state.repairsuser.table.currentPage
    })
  },
  methods: {
    getuser () {
      this.obj = getUserInfo()
    },
    searchData () {
      this.search({'userId': this.obj.id, 'page': 0, 'size': this.pageSize})
    },
    ...mapActions({
      search: 'repairsuser/search',
      'getUser': 'rechargeuser/getUser'
    }),
    pageChange () {
      this.searchData()
    }
  },
  mounted () {
    this.searchData()
  }
}
</script>

<style scoped lang="less">
</style>
