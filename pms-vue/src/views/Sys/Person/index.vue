<template>
  <div class="app-container">
    <div style="display: flex; justify-content: center;">
      <el-card class="box-card">
        <div slot="header" class="clearfix" style="text-align: center;">
          <span>个人信息</span>
        </div>
        <div style="padding-right: 40px">
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="form.username" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="realName">
              <el-input v-model="form.realName"></el-input>
            </el-form-item>
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email"></el-input>
            </el-form-item>
            <el-form-item label="角色">
              <el-input v-model="form.role.name" placeholder="请选择角色名称" :disabled="true"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div style="display:flex;  justify-content:center;">
        <el-button type="primary" @click="onSubmit()">提交</el-button>
<!--        <el-button @click="cancel()">清空</el-button>-->
      </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import {mapState, mapActions} from 'vuex'
import {getUserInfo} from '../../../utils/sessionStorage'
export default {
  name: 'Personal',
  created () {
    this.getuser()
  },
  data () {
    return {
      obj: {},
      form: {
        id: '',
        username: '',
        realName: '',
        phone: '',
        email: '',
        role: {
          name: ''
        }
      },
      delLoading: false,
      rules: {
        realName: [
          {required: true, message: '姓名不能为空', trigger: 'change'},
          {min: 1, max: 5, message: '长度在1 到 5 个字符之间'},
          {pattern: /^[\u4E00-\u9FA5]+$/, message: '姓名只能为中文'}
        ],
        phone: [
          {required: true, message: '请输入手机号码', trigger: 'change'},
          {validator: function (rule, value, callback) {
            if (/^1[34578]\d{9}$/.test(value) === false) {
              callback(new Error('请输入正确的手机号'))
            } else {
              callback()
            }
          },
          trigger: 'blur'}
        ],
        email: [
          {type: 'email', message: '请输入正确邮箱', trigger: 'change'}
        ]
      }
    }
  },
  computed: {
    ...mapState({
      diglogStatus: state => state.personal.diglogStatus,
      roleData: state => state.role.table.data,
      loading: state => state.personal.loading,
      data: state => state.personal.table.data,
      total: state => state.personal.table.total
    })
  },
  methods: {
    getuser () {
      this.obj = getUserInfo()
      // console.log(this.obj.id)
      this.getUser(this.obj.id).then((res) => {
        this.form = res
      })
    },
    ...mapActions({
      'editUser': 'personal/editUser',
      'getUser': 'personal/getUser',
      'updateRealName': 'app/updateRealName'
    }),
    onSubmit () {
      this.$refs.form.validate(valid => {
        if (valid) {
          this.editUser(this.form).then(() => {
            this.cancel()
            this.$notify({
              title: '编辑成功',
              type: 'success',
              duration: 1500
            })
            this.getuser()
            this.updateRealName()
            this.$bus.$emit('submit-event')
          })
        }
      })
    },
    cancel () {
      this.$emit('clearData')
      this.$refs['form'].resetFields()
    },
    handleEdit (username) {
      this.editDialog(username).then((data) => {
        this.form = data
      })
    },
    clearData () {
      Object.assign(this.$data, this.$options.data())
    }
  },
  mounted () { // 页面初始化完成
    this.getuser()
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
