/* eslint-disable no-tabs */
<template xmlns:svg-icon="http://www.w3.org/1999/html">
  <div class="login">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      label-position="left"
      label-width="0px"
      class="login-form">

      <div class="title-container">
        <h3 class="title">欢迎使用慕颜物业管理系统</h3>
      </div>

      <div>
        <el-form-item prop="username" >
          <el-input
            ref="username"
            v-model="loginForm.username"
            type="text"
            auto-complete="off"
            placeholder="账号"
            tabindex="1"
            >
            <i slot="prefix" >
              <img
                style="width:30px;height:16px"
                src="../icons/svg/user.svg"
                alt
              />
            </i>
          </el-input>
        </el-form-item>
      </div>

      <el-form-item prop="password">
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          name="password"
          auto-complete="on"
          placeholder="密码"
          @keyup.enter.native="handleLogin"
          tabindex="2"
          >
          <i slot="prefix" >
            <img
              style="width:30px;height:16px"
              src="../icons/svg/password.svg"
              alt
            />
          </i>

          <i slot="suffix">
            <span class="show-pwd" @click="showPwd">
              <img
                v-if="passwordType === 'password'"
                src="../icons/svg/openeye.svg"
                alt="Show Password"
                class="custom-icon"
              />
              <img
                v-else
                src="../icons/svg/eye.svg"
                alt="Hide Password"
                class="custom-icon"
              />
            </span>
          </i>

        </el-input>
<!--        <i slot="suffix" :class="iconchange" @click="showPass"></i>-->

      </el-form-item>
<!--      </el-form>-->
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleLogin">
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
<!--      <p class="login-tip">系统默认用户名：admin，密码：admin</p>-->
    </el-form>
  </div>
</template>

<script>
import {setToken, setUserInfo} from '~utils/sessionStorage'
import {login} from '@/api/auth'
import {getUserInfo} from '@/api/user'

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: 'admin',
        password: 'superadmin'
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', message: '用户名不能为空' }],
        password: [{ required: true, trigger: 'blur', message: '密码不能为空' }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  methods: {
    showPwd () {
      this.passwordType = this.passwordType === 'password' ? 'text' : 'password'
    },
    handleLogin () {
      this.$refs.loginForm.validate(valid => {
        // 验证成功
        if (valid) {
          this.loading = true
          // 登陆对象实体
          login(this.loginForm.username, this.loginForm.password)
            .then(res => {
              setToken(res)
            }).then(res => {
              getUserInfo().then(res => {
                setUserInfo(res)
                let role = res.role.name
                if (role === '系统管理员' || role === '物业管理员') {
                  this.$router.push('/home/manage')
                } else if (role === '业主') {
                  this.$router.push('/home/owner')
                } else {
                  this.$router.push('/home')
                }
                setTimeout(() => {
                  this.loading = false
                }, 200)
              })
            // eslint-disable-next-line handle-callback-err
            }).catch(err => {
              this.loading = false
            })
        } else {
          return false
        }
      })
    }
  },
  components: {}
}
</script>

<style lang="less" scoped>
.custom-icon {
  position: absolute;
  right: 10px;
  top: 7px;
  width: 24px;
  height: 24px;
}
.show-pwd {
  position: absolute;
  right: 10px;
  //top: 7px;
  font-size: 16px;
  color: white;
  cursor: pointer;
  user-select: none;
}
.login{
  display: flex;
  justify-content: center;
  align-items: center;
  background-image:url('../assets/img/background.jpg');
  background-size: cover;
  height: 100%;

  // 改变input里的字体颜色
  /deep/input::-webkit-input-placeholder {
    color: #17a1e5;
    font-size: 20px;
  }

   //改变input框背景颜色
  /deep/.el-input__inner {
    background-color: transparent !important;
    //background:rgba(124,215,192,0.7);
    //background:rgba(144,243,203,0.9);
    //background-color: #bfbfbf;
    border: 1px solid #1296db;
    font-size: 18px;
    letter-spacing: 1px;
    padding-left: 45px;
  }

  .title-container {
    position: relative;
    .title {
      font-size: 26px;
      color: #5a5e66;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }
}
.title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #707070;
}
.login-form {
  position: relative;
  width: 500px;
  max-width: 100%;
  padding: 35px 50px 20px;
  margin: 0 auto;
  overflow: hidden;
  background:rgba(124,215,192,0.7);
  border-radius: 8px;
  .el-input {
    padding-right: 10px;
    //background-color: #bfbfbf;
    height: 40px;
    input {
      height: 40px;
      //font-size: 40px;
    }
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.forget{
  float:right;
  margin:0 5px 10px 0px;
  font-size: 14px;
  color: cornflowerblue;
  cursor: pointer;
}
</style>
