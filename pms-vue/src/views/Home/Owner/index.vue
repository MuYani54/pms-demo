<template>
  <div class="app-container">
    <div style="display: flex; justify-content: center;">
      <!--表格渲染-->
      <el-card class="box-card">
        <div slot="header" class="clearfix" style="text-align: center;">
          <span>收费公告</span>
        </div>
        <el-table :data="tableData"  border class="table-container">
          <el-table-column  type="index" width="50px" align="center" label="序号">  </el-table-column>
          <el-table-column  prop="costName" label="费用名称" align="center"></el-table-column>
          <el-table-column  prop="costPrice" label="价格" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">￥{{scope.row.costPrice}}</span>
            </template>
          </el-table-column>
          <el-table-column  prop="regular" label="类型" align="center"></el-table-column>
        </el-table>
        <!--分页组件-->
        <el-pagination
          background
          :page-count="pageCount"
          :page-size="pageSize"
          :current-page="currentPage"
          style="margin-top:8px; float:right"
          layout="prev,pager,next"
          @current-change="pageChange"/>
      </el-card>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import {mapState, mapActions} from 'vuex'
export default {
  data () {
    return {
      costForm: {
        costName: '',
        costPrice: '',
        regular: ''
      }
    }
  },
  // 页面初始化方法向后台请求数据
  mounted () {
    this.findCostAll({'page': 0, 'size': this.pageSize})
  },
  // 计算属性 监听vuex中的数据
  computed: {
    ...mapState({
      tableData: state => state.costSet.table.data,
      pageCount: state => state.costSet.table.pageCount,
      pageSize: state => state.costSet.table.pageSize,
      currentPage: state => state.costSet.table.currentPage
    })
  },
  methods: {
    ...mapActions({
      findCostAll: 'costSet/findCostAll',
      deleteCostSet: 'costSet/deleteCostSet',
      editDialog: 'costSet/editDialog'
    }),
    handleEdit (id) {
      this.editDialog(id).then((data) => {
        this.costForm = data
      })
    },
    pageChange (currPage) {
      this.findCostAll({'page': currPage - 1, 'size': this.pageSize})
    },
    subDelete (id) {
      this.deleteCostSet(id).then(() => {
        this.$refs[id].doClose()
        this.$notify({
          title: '删除成功',
          type: 'success',
          duration: 1500
        })
        this.findCostAll({'page': this.currentPage - 1, 'size': this.pageSize})
        // eslint-disable-next-line handle-callback-err
      }).catch(err => {
        this.$refs[id].doClose()
      })
    },
    clearData () {
      Object.assign(this.$data, this.$options.data())
    }
  }
}
</script>

<style scoped lang="less">
.box-card {
  position: absolute;
  width: 800px;
  top: 50px;
}
</style>
