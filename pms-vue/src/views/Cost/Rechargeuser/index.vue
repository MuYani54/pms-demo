<template>
  <div class="app-container">
<!--    账户页面-->
    <div>
      <div slot="header" class="clearfix" style="text-align: center;padding-bottom: 20px">
        <span style="font-size: 20px">个人账户</span>
      </div>
      <div style="padding-bottom: 20px;">
        <!--表格渲染-->
        <el-table :data="tableData" size="small" border class="table-container">
          <el-table-column  width="50px" type="index" align="center"  label="序号" show-overflow-tooltip>  </el-table-column>
          <el-table-column  prop="username" label="用户名" align="center"></el-table-column>
          <el-table-column  prop="realName" label="业主" align="center"></el-table-column>
          <el-table-column  prop="balance" label="账户余额" align="center"/>
        </el-table>
      </div>
    </div>
    <!--充值页面-->
    <div style="padding-bottom: 20px;display: flex; justify-content: center;">
      <el-card class="box-card">
        <el-form label-width="90px" ref="accountFrom" :model="accountFrom" :rules="rules">
          <el-row>

            <el-col>
              <el-form-item label="账户:"  prop="username">
                <el-input v-model="obj.username" :disabled="true"></el-input>
              </el-form-item>
            </el-col>

            <el-col>
              <el-form-item label="充值方式:"  prop="rechargeType">
                <el-radio-group v-model="accountFrom.rechargeType">
                  <el-radio-button border
                                   v-for="item in rechargeType"
                                   :label="item.key"
                                   :key="item.key">
                    {{item.label}}
                  </el-radio-button>
                </el-radio-group>
              </el-form-item>
            </el-col>

            <el-col>
              <el-form-item label="充值金额:"  prop="money">
                <el-radio-group v-model="accountFrom.money">
                  <el-radio
                    v-for="item in money"
                    :label="item.key"
                    :key="item.key">
                    {{item.label}}
                  </el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>

            <el-col>
              <div style="display:flex;  justify-content:flex-end; margin-right:20px">
                <el-button type="primary" @click="onSubmit()">确认</el-button>
              </div>
            </el-col>

          </el-row>
        </el-form>
      </el-card>
    </div>

  </div>
</template>

<script type="text/ecmascript-6">
import {mapState, mapActions} from 'vuex'
import {getUserInfo} from '../../../utils/sessionStorage'
export default {
  name: 'Rechargeuser',
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
      // user: {
      //   id: '',
      //   username: ''
      // },
      accountFrom: {
        user: {
          id: ''
        },
        money: '',
        rechargeType: ''
      },
      rechargeType: [
        {key: 'WECHART', label: '微信'},
        {key: 'ALIPAY', label: '支付宝'},
        {key: 'CASH', label: '现金'},
        {key: 'CARD', label: '银行卡'}
      ],
      money: [
        {key: 5, label: '5元'},
        {key: 10, label: '10元'},
        {key: 50, label: '50元'},
        {key: 100, label: '100元'}
      ],
      rules: {
        user: {
          id: {required: true, message: '用户不能为空', trigger: 'blur'}
        },
        rechargeType: {required: true, message: '充值类型不能为空'},
        money: {required: true, message: '充值金额不能为空'}
      }
    }
  },
  // 计算属性 监听vuex中的数据
  computed: {
    ...mapState({
      tableData: state => state.rechargeuser.table.data,
      pageCount: state => state.rechargeuser.table.pageCount,
      pageSize: state => state.rechargeuser.table.pageSize,
      currentPage: state => state.rechargeuser.table.currentPage
    })
  },
  methods: {
    getuser () {
      this.obj = getUserInfo()
      // console.log(2, this.obj)
      // this.getUser(this.obj.id).then((res) => {
      //   // console.log(2, res)
      //   this.obj = res
      //   // console.log(3, this.accountFrom)
      //   // this.user = res
      // })
    },
    searchData () {
      this.search({'userId': this.obj.id, 'page': 0, 'size': this.pageSize})
    },
    ...mapActions({
      search: 'rechargeuser/search',
      payment: 'rechargeuser/payment',
      'getUser': 'rechargeuser/getUser'
    }),
    clearData () {
      Object.assign(this.$data, this.$options.data())
    },
    cancel () {
      this.$emit('clearData')
      this.$refs['accountFrom'].resetFields()
    },
    onSubmit () {
      this.$refs.accountFrom.validate(async valid => {
        console.log(1, valid)
        if (valid) {
          console.log(2, this.accountFrom)
          this.accountFrom.user.id = this.obj.id
          await this.payment(this.accountFrom).then((res) => {
            // console.log(3, res)
            this.cancel()
            this.$notify({
              title: '充值成功',
              type: 'success',
              duration: 1500
            })
            // this.getuser()
            this.searchData()
          })
        }
        console.log('to exit')
      })
    }
  },
  mounted () { // 页面初始化完成
    // this.getuser()
    this.searchData()
  }
}
</script>

<style scoped lang="less">
.box-card {
  //position: absolute;
  width: 600px;
  top: 50px;
}
</style>
