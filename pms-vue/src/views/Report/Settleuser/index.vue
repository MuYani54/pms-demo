<template>
  <div class="app-container">
    <div slot="header" class="clearfix" style="text-align: center;padding-bottom: 20px">
      <span style="font-size: 20px">缴费记录</span>
    </div>
    <div>
      <el-table :data="tableData" size="small" border class="table-container" show-summary :summary-method="totalSummary">
        <el-table-column  width="50px" type="index" align="center"  label="序号" show-overflow-tooltip>  </el-table-column>
        <el-table-column  prop="username" label="用户名" align="center"/>
        <el-table-column  prop="realName" label="业主" align="center"/>
        <el-table-column  prop="costName" label="扣费名称" align="center"/>
        <el-table-column  prop="totalPrice" label="扣费金额" align="center"/>
        <el-table-column  prop="settingDate" label="扣费时间" align="center"/>
      </el-table>
    </div>
        <!--分页组件-->
        <el-pagination
        background
        :total="total"
        style="margin-top:8px; float:right"
        layout="prev,pager,next"/>
  </div>
</template>

<script type="text/ecmascript-6">
import {mapState, mapActions} from 'vuex'
import {getUserInfo} from '../../../utils/sessionStorage'
export default {
  created () {
    this.getuser()
    this.searchData()
  },
  data () {
    return {
      obj: {}
    }
  },

  // 计算属性 监听vuex中的数据
  computed: {
    ...mapState({
      pageSize: state => state.settleReportuser.table.pageSize,
      tableData: state => state.settleReportuser.table.data,
      total: state => state.settleReportuser.table.total
    })
  },
  methods: {
    getuser () {
      this.obj = getUserInfo()
    },
    searchData () {
      this.report(this.obj.id)
    },
    ...mapActions({
      'getUser': 'settleReportuser/getUser',
      report: 'settleReportuser/report'
    }),
    totalSummary (param) {
      const { columns, data } = param
      const sums = []
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '合计'
        } else if (column.property === 'totalPrice') {
          const values = data.map(item => Number(item[column.property]))
          if (!values.every(value => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr)
              if (!isNaN(value)) {
                return prev + curr
              } else {
                return prev
              }
            }, 0)
          }
        } else {
          sums[index] = '-- --'
        }
      })
      return sums
    }
  },
  mounted () { // 页面初始化完成
    this.getuser()
    this.searchData()
  }
}
</script>

<style scoped lang="less">
</style>
