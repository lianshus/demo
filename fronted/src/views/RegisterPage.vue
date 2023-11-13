<template>
  <el-row type="flex" justify="center" class="container">
    <el-col :span="7" class="form">
      <el-form ref="form" :model="form" :rules="rules" label-position="left">
        <div>
          <h3>角色注册</h3>
        </div>

        <el-form-item prop="username">
          <el-input
            v-model="form.username"
            placeholder="请输入用户名"
            name="username"
            type="text"
          />
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            v-model="form.password"
            placeholder="请输入密码"
            name="password"
          />
        </el-form-item>

        <el-form-item prop="role">
          <el-radio-group v-model="form.roles">
            <el-radio label="采购商"></el-radio>
            <el-radio label="供应商"></el-radio>
          </el-radio-group>
        </el-form-item>

        <div>
          <el-button type="primary" style="width: 100%; margin-bottom: 20px"
            @click="handleRegister">注册</el-button>
        </div>

        <div class="tips">
          <span style="margin-right: 20px"
            >已有账号,
            <span style="color: #409eff; cursor: pointer" @click="login"
              >登录</span
            ></span
          >
        </div>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
export default {
  components: {},
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!value) {
        callback(new Error("用户名不能为空！"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error("密码最少为6位字符！"));
      } else {
        callback();
      }
    };
    return {
      // 头像状态
      form: {
        username: "",
        password: "",
        roles: "",
      },
      rules: {
        username: [
          { required: true, trigger: "blur", validator: validateUsername },
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePassword },
        ],
      },
    };
  },
  methods: {
    // showPwd() {
    //   if (this.passwordType === "password") {
    //     this.passwordType = "";
    //   } else {
    //     this.passwordType = "password";
    //   }
    //   this.$nextTick(() => {
    //     this.$refs.password.focus();
    //   });
    // },
    login(){
      this.$router.push('/');
    },
    // 注册
    handleRegister() {
      this.$refs.form.validate((valid) => {
        // 如果符合验证规则
        if (valid) {
          setTimeout(() => {
            this.$router.push('/');
          }, 1500);
        } else {
          console.log("error!!");
          return false;
        }
      });
    },
  },
};
</script>

<style>
.container {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-image: url('../static/BlockChain.jpg'); */
}
.form {
  border: 2px solid #dededb;
  border-radius: 10px;
  padding: 40px;
  background: #ffffff;
  box-shadow: 2px #dededb;
  /* width:100%;
  height:100%; */
}
</style>
