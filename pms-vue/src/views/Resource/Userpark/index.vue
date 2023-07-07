<template>
  <div class="app-container">
<!--车辆-->
    <div style="padding-bottom: 20px">
      <el-card class="box-card">
        <div slot="header" class="clearfix" style="text-align: center;">
          <span style="font-size: 20px">个人资产：车辆</span>
        </div>
        <div>
          <!--车辆表格渲染-->
          <el-table :data="tableData" size="small" border class="table-container">
            <el-table-column  width="50px" type="index" align="center"  label="序号" show-overflow-tooltip>  </el-table-column>
            <el-table-column  prop="username" label="业主" align="center"></el-table-column>
            <el-table-column  prop="position" label="车牌" align="center"/>
            <el-table-column  prop="parkType" label="车位类型" align="center" :formatter="doorType"/>
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
  },
  data () {
    return {
      obj: {},
      user: {
        id: '',
        username: ''
      },
      parkType: [{
        value: 'SMALL_CAR',
        label: '电动车/摩托车/自行车'
      }, {
        value: 'BIG_CAR',
        label: '汽车'
      }]
    }
  },
  // 计算属性 监听vuex中的数据
  computed: {
    ...mapState({
      tableData: state => state.userpark.table.data,
      pageCount: state => state.userpark.table.pageCount,
      pageSize: state => state.userpark.table.pageSize,
      currentPage: state => state.userpark.table.currentPage
    })
  },
  methods: {
    searchData (data) {
      this.search({'userId': this.obj.id, 'page': 0, 'size': this.pageSize})
    },
    getuser () {
      this.obj = getUserInfo()
    },
    doorType (row, column) {
      if (row.parkType === 'SMALL_CAR') {
        return '电动车/摩托车/自行车'
      } else if (row.parkType === 'BIG_CAR') {
        return '汽车'
      }
    },
    ...mapActions({
      'search': 'userpark/search'
    })
  }
}
</script>

<style scoped lang="less">
.centered-form {
  text-align: center;
}
</style>
