<template>
  <div class="register">
    <el-card >
      <div style="width: 440px;" class="register-title">
        <!-- <el-avatar :size="size" :src="logo"></el-avatar> -->
        <span class="register-title-text">PointsPlatform</span>
      </div>
      <div class="register-form">
        <el-form :model="registerInfo"  status-icon :rules="rules" ref="ruleForm" label-width="100px"
          class="demo-ruleForm">
          <el-form-item required label="登录名称" prop="username">
            <el-input type="text" autocomplete="off" v-model="registerInfo.username"></el-input>
          </el-form-item>
          <el-form-item required label="昵称" prop="fullName">
            <el-input type="text" autocomplete="off" v-model="registerInfo.fullName"></el-input>
          </el-form-item>
          <el-form-item required label="密码" prop="password" v-model="registerInfo.password">
            <el-input type="password" autocomplete="off" v-model="registerInfo.password"></el-input>
          </el-form-item>
          <el-form-item required label="确认密码" prop="repassword">
            <el-input type="password" autocomplete="off" v-model="registerInfo.repassword"></el-input>
          </el-form-item>
          <el-form-item required label="身份证号" prop="idCard">
            <el-input type="text" autocomplete="off" v-model="registerInfo.idCard"></el-input>
          </el-form-item>
          <el-form-item required label="电话号码" prop="phone">
            <el-input type="text" autocomplete="off" v-model="registerInfo.phone"></el-input>
          </el-form-item>
          <el-form-item label="出生日期" required>
            <el-form-item prop="birthDate">
              <el-date-picker type="date" value-format="yyyy-MM-dd" format="yyyy-MM-dd"
                v-model="registerInfo.birthDate" placeholder="选择日期" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" class="reg" @click="register('ruleForm')">{{ registerInfo.roleType === 'admin' ?
          '申请管理员' : '注册' }}</el-button>
            <el-button @click="goBack()">返回</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>

import logo_h from "../../assets/logo.png"

export default {
  name: 'register',
  data() {
    var checkRpassword = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请重复输入密码~'));
      }
      if (this.registerInfo.password !== this.registerInfo.repassword) {
        return callback(new Error('密码输入不一致~'));
      }
      callback();
    }
    return {
      size: 50,
      logo: logo_h,
      securityBureaus: [],
      registerInfo: {
        username: "",
        password: "",
        fullName: "",
        birthDate: null,
        phone:"",
        idCard: "",
        roleType: "patient",
      },
      rules: {},
      // 公共校验规则
      commonRules: {
        username: [
          { required: true, message: '请输入用户名~' },
          { min: 3, message: '长度在3字符以上' }
        ],
        fullName: [
          { required: true, message: '请输入昵称~' },
          { min: 3, message: '长度在2字符以上' }
        ],
        password: [
          { required: true, message: '请输入密码~' },
          { min: 6, message: '长度在6字符以上~' }
        ],
        idCard: [
          { required: true, message: '请输入正确的身份证~' },
          { max: 18,min:18, message: '请输入正确的身份证~' }
        ],
        repassword: [
          { validator: checkRpassword }
        ],
        roleType: [
          { required: true, message: '请选择注册角色~' },
        ],
        phone: [
          { required: true, message: '请输入电话号码~' },
        ],
        birthDate:[
        { required: true, message: '请输选择出生日期~' }
        ]
      },
    };
  },
  // 根据不同的角色分配不同的校验规则
  watch: {
    "registerInfo.roleType": {
      handler: function () {
        this.rules = this.commonRules;
        if(this.registerInfo.roleType!="patient"){
          Object.assign(this.rules, this.locationCheck);
        }
      },
      immediate: true
    }
  },
  async mounted() {
  },
  methods: {
    goBack() {
      this.$router.push("/login")
    },
    register(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          console.log(this.registerInfo);
          const result = await this.$http.post("/user/register", this.registerInfo);
          this.$message.success("注册成功！");
          setTimeout(() => {
            this.goBack();
          }, 500)
        }
      })
    }
  }
}

</script>

<style lang="less" scoped>
.register {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: url("../../assets/image/loginBackground.jpg");
  background-size: cover;

  .register-title {
    width: 100%;
    padding-bottom: 20px;
    display: flex;
    justify-content: center;
    align-items: center;

    .register-title-text {
      font-size: 20px;
      margin-left: 5px;

      span {
        color: #FF6C60;
      }
    }
  }

  .pension .pension-date /deep/ .el-form-item__content {
    margin-left: 0px !important;
  }



}
</style>