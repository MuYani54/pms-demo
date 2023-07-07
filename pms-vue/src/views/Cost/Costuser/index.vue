<template>
  <div class="app-container">
    <div style="display: flex; justify-content: center;">
      <el-card class="box-card">
        <div slot="header" class="clearfix" style="text-align: center;">
          <span>业主缴费</span>
        </div>
    <el-form label-width="90px" ref="settleForm" :model="settleForm" :rules="rules">
      <el-row>

        <el-col>
          <el-form-item label="用户名:"  prop="user.username">
            <el-input v-model="settleForm.user.username" disabled></el-input>
          </el-form-item>
        </el-col>

        <el-col>
          <el-form-item label="费用类型:"  prop="costSetting.id">
            <el-select v-model="settleForm.costSetting.id" placeholder="请选择费用类型" @change="costSettingChange">
              <el-option
                v-for="item in costList"
                :key="item.id"
                :label="item.costName"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="基数:" prop="multiple">
            <el-input-number @change="multipleChange" v-model="settleForm.multiple"  placeholder="请输入基数" :min="0" :precision="0"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="价格:" prop="price">
            <el-input v-model="this.price" disabled></el-input>
          </el-form-item>
        </el-col>

        <el-col>
          <el-form-item label="总费用:">
            <el-input v-model="amt"></el-input>
          </el-form-item>
        </el-col>

        <el-col>
          <div style="display:flex;  justify-content:flex-end; margin-right:20px">
            <el-button type="primary" @click="onSubmit('settleForm')">提交</el-button>
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
  name: 'Costuser',
  created () {
    this.getuser()
  },
  data () {
    return {
      price: 0,
      multiple: 0,
      amt: 0,
      rules: {
        user: {
          id: {required: true, message: '用户不能为空', trigger: 'blur'},
          username: {required: true, message: '用户不能为空', trigger: 'blur'}
        },
        costSetting: {
          id: {required: true, message: '费项不能为空', trigger: 'blur'},
          costName: {required: true, message: '费项不能为空', trigger: 'blur'}
        },
        multiple: {required: true, message: '倍数不能为空', trigger: 'blur'}
      },
      settleForm: {
        user: {
          id: '',
          username: ''
        },
        costSetting: {
          id: ''
        },
        multiple: ''
      }
    }
  },
  computed: {
    ...mapState({
      costList: state => state.settle.costList
    })
  },
  methods: {
    getuser () {
      // this.obj = JSON.parse(sessionStorage.getItem('userInfo'))
      this.obj = getUserInfo()
      // console.log(1, this.obj)
      this.getUser(this.obj.id).then((res) => {
        console.log(2, res)
        this.settleForm.user.username = res.username
        this.settleForm.user.id = res.id
        console.log(3, this.settleForm)
      })
    },
    ...mapActions({
      'getUser': 'personal/getUser',
      findCostAll: 'settle/findCostAll',
      findAll: 'settle/findAll',
      create: 'settle/create'
    }),
    cancel () {
      this.price = 0
      this.multiple = 0
      this.amt = 0
      this.$refs['settleForm'].resetFields()
    },
    onSubmit () {
      this.$refs.settleForm.validate(valid => {
        if (valid) {
          this.create(this.settleForm).then(() => {
            this.cancel()
            this.$notify({
              title: '结算成功',
              type: 'success',
              duration: 1500
            })
            this.getuser()
            // this.findAll({'page': this.currentPage - 1, 'size': this.pageSize})
          })
        }
      })
    },
    clearData () {
      Object.assign(this.$data, this.$options.data())
    },
    multipleChange (multiple) {
      this.multiple = multiple
      this.amt = this.price * this.multiple
    },
    costSettingChange (data) {
      this.price = this.costList.filter(v => { return v.id === data })[0].costPrice
      this.amt = this.price * this.multiple
    }
  },
  mounted () {
    this.findCostAll()
  }
}
</script>

<style lang="less" scoped>
.box-card {
  position: absolute;
  width: 600px;
  top: 50px;
}
.message{
  width: 20em;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
.head-container{
  .head-text{
    display: initial;
  }
}
.table-container{
  margin-top: 20px;
}
</style>
