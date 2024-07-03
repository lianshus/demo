<template>
  <div class="login">
    <el-card>
      <div class="login-title">
        <!-- <el-avatar :size="size"
          :src="logo"></el-avatar> -->
        <span class="login-title-text">电力交易市场</span>
      </div>
      <div class="login-form">
        <el-form :model="user" :rules="rules" ref="user" class="demo-ruleForm">
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" type="text" v-model="user.username" placeholder="请输入用户名">
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" type="password" v-model="user.password" placeholder="请输入密码">
            </el-input>
          </el-form-item>

          <div class="register" @click="registerPage()"> 没有账户？注册</div>
          <el-form-item>
            <el-button type="primary" @click="submitForm('user')">登录</el-button>
          </el-form-item>
          
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import logo_h from"../../assets/logo.png"
export default {
  name: 'login',
  data() {
    return {
      user: {
        name: '',
        password: '',
      },
      size: 50,
      logo:logo_h,
      rules: {
        username: [
          { required: true, message: '请输入用户名~' },
          { min: 3, message: '长度在3字符以上' }
        ],
        password: [
          { required: true, message: '请输入密码~' },
          { min: 6, message: '长度在6字符以上~' }
        ]
      }
    }
  },
  methods: {
    // 用户登录
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const res = await this.$http.post("/user/login", this.user);
          this.$message.success("登录成功！");
          // 保存token
          console.log( res.data);
          this.$store.commit("setToken", res.data);
          setTimeout(() => {
            this.$router.push({ path: '/' })
          }, 1000)
        } else {
          return false
        }
      })
    },
    registerPage() {
      this.$router.push("/register")
    }
  }

}
</script>

<style lang="less" scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: url("../../assets/image/loginBackground.jpg") no-repeat;
  background-size: cover;

  .login-title {
    width: 100%;
    padding-bottom: 20px;
    display: flex;
    justify-content: center;
    align-items: center;

    .login-title-text {
      font-size: 20px;
      margin-left: 5px;

      span {
        color: #FF6C60;
      }
    }
  }

  .register {
    margin-bottom: 15px;
    text-align: right;
    color:rgb(19, 241, 149);
    cursor: pointer;
    user-select: none;
  }

  .el-button {
    width: 400px;
    font-size: 18px;
    color: #fff;
  }

  .tishi {
    font-size: 13px;
    color: #4cbb15;

    .tishi-content {
      padding-left: 60px;
    }
  }
}
</style>
