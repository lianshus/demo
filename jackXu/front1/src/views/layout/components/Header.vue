<template>
  <div>
    <el-row :gutter="24" class="nheader" type="flex" align="middle">

      <el-col :span="4" :xs="{ span: 10 }" :sm="{ span: 6 }" align="left">
        <span class="title">PointsPlatform</span>
      </el-col>
      <el-col :span="14">
      </el-col>
      <el-col :span="4" :xs="{ span: 10 }" :sm="{ span: 8 }" type="flex" align="right" class="header-user">
        <el-popover popper-class="head_popper" placement="bottom" trigger="hover">
          <div class="user-tip">
            <p @click="userInfo">个人信息</p>
            <p @click="pointsDialogFormVisible = true">积分管理</p>
            <!-- <p v-else @click="modifyPassFormDialogVisible=true">修改密码</p> -->
            <p @click="userOut">退出登录</p>
          </div>
          <div slot="reference">
            <span>您好，<b>{{ this.$store.state.user.username }}</b></span>
            &nbsp;&nbsp;<img class="header-img-github" src="../../../assets/image/lo.jpeg" alt="">
          </div>
        </el-popover>
      </el-col>
    </el-row>

    <!-- 个人信息 -->
    <el-dialog title="个人信息" :visible.sync="userDialogFormVisible" @open="getUserInfo()" width="550px"
      :before-close="userInfoHandleClose">

      <el-form :model="ruleUserForm" :disabled="isDisabled" :rules="isModify ? userRules : {}" ref="ruleUserForm"
        label-width="100px" class="demo-ruleForm">
        <el-form-item :required="isModify" label="登录名称" prop="username">
          <el-input type="text" autocomplete="off" v-model="ruleUserForm.username"></el-input>
        </el-form-item>
        <el-form-item v-if="!isModify" :required="isModify" label="昵称" prop="fullName">
          <el-input type="text" autocomplete="off" v-model="ruleUserForm.fullName"></el-input>
        </el-form-item>
        <el-form-item v-if="isModify" label="新密码" prop="password">
          <el-input type="password" autocomplete="off" v-model="ruleUserForm.password"></el-input>
        </el-form-item>
        <el-form-item v-if="!isModify" label="身份证号" prop="idCard">
          <el-input type="text" autocomplete="off" v-model="ruleUserForm.idCard"></el-input>
        </el-form-item>
        <el-form-item :required="isModify" label="电话号码" prop="phone">
          <el-input type="text" autocomplete="off" v-model="ruleUserForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="出生日期" :required="isModify" prop="birthDate">
          <el-date-picker type="date" value-format="yyyy-MM-dd" format="yyyy-MM-dd" v-model="ruleUserForm.birthDate"
            placeholder="选择日期" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item v-if="!isModify" label="区块链地址">
          <span class="user-item-info">{{ ruleUserForm.userAddress }}</span>
        </el-form-item>
        <!-- <el-form-item label="公钥">
          <span class="user-item-info">{{ ruleUserForm.userPublicKey }}</span>
        </el-form-item> -->
        <el-form-item v-if="!isModify" label="注册时间">
          <span class="user-item-info"> {{ ruleUserForm.registrationDate }}</span>
        </el-form-item>
      </el-form>
      <div class="user-dialog-footer">
        <el-button size="medium" type="primary" v-if="!isModify" @click="changeModify()">修改信息</el-button>
        <el-button size="medium" type="primary" v-if="!isModify" @click="readPrivate()">查看私钥</el-button>
        <el-button size="medium" type="primary" v-if="isModify" @click="changeUserInfo('ruleUserForm')">确认修改</el-button>
        <el-button size="medium" @click="userInfoHandleClose()">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="私钥" :visible.sync="dialogPrivateVisible" width="300px">
      <span>{{ this.private }}</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" size="small" @click="dialogPrivateVisible = false">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 积分管理 -->
    <el-dialog title="积分管理" :visible.sync="pointsDialogFormVisible" @open="getPointsInfo()" width="500px">
      <div style="margin: -20px 0px 50px 0px;">个人积分余额：{{ this.points }}</div>
      <div class="user-dialog-footer">
        <el-button size="medium" type="primary" v-if="!isModify" @click="castingPoints()">铸造积分</el-button>
        <el-button size="medium" type="primary" v-if="!isModify" @click="estructionPoints()">销毁积分</el-button>
        <el-button size="medium" type="primary" v-if="!isModify" @click="queryPoints()">查询积分</el-button>
        <el-button size="medium" type="primary" v-if="isModify" @click="changeUserInfo('ruleUserForm')">确认修改</el-button>
        <el-button size="medium" @click="pointsDialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import logo_h from "../../../assets/logo.png"
export default {
  name: 'Header',
  data() {
    var checkRpassword = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请重复输入密码~'));
      }
      if (this.modifyPassForm.newPass !== this.modifyPassForm.reNewPass) {
        return callback(new Error('密码输入不一致~'));
      }
      callback();
    }
    return {
      userDialogFormVisible: false,
      userInf: {},
      modifyPassFormDialogVisible: false,
      logo: logo_h,
      isModify: false,
      isDisabled: true,
      dialogPrivateVisible: false,
      private: "",
      pointsDialogFormVisible: false,
      points: 0,
      ruleUserForm: {
        username: "",
        fullName: "",
        password: "",
        birthDate: "",
        phone: "",
        idCard: "",
        userAddress: "",
        userPublicKey: "",
        registrationDate: ""
      },
      userRules: {
        username: [
          { required: true, message: '请输入用户名~' },
          { min: 3, message: '长度在3字符以上' }
        ],
        password: [
          { required: true, message: '请输入密码~' },
          { min: 6, message: '长度在6字符以上~' }
        ],
        phone: [
          { required: true, message: '请输入电话号码~' },
        ],
        birthDate: [
          { required: true, message: '请输选择出生日期~' }
        ]
      }
    }
  },
  mounted() {
    this.getUserInfo();
  },
  methods: {
    changeModify() {
      this.isDisabled = false;
      this.isModify = true;
    },
    userInfo() {
      this.userDialogFormVisible = true;
    },
    changeUserInfo(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const result = await this.$http.post("/user/updateUser", this.ruleUserForm);
          this.$message.success("修改成功！");
          this.userOut();
        }
      })
    },
    userInfoHandleClose() {
      this.userDialogFormVisible = false;
      this.isDisabled = true;
      this.isModify = false;

    },
    userOut() {
      this.$store.commit("removeUser");
      this.$store.commit("removeToken");
      this.$router.push({ path: '/login' })
    },
    // 获取用户信息
    async getUserInfo() {
      const res = await this.$http.get("/user/info")
      this.ruleUserForm = res.data;
      this.$store.commit("setUser", res.data)
    },
    async getUInfo() {
      const res = await this.$http.get("/pension/details/" + this.$store.state.user.pension.id);
      this.userInf = res.data;
    },
    readPrivate() {
      this.$prompt('请输入密码', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^\d{6,}$/,
        inputErrorMessage: '密码至少6位数'
      }).then(async ({ value }) => {
        const res = await this.$http.post("/user/privateKey", {
          address: this.$store.state.user.userAddress,
          password: value
        });
        this.private = res.data.userPrivateKey
        this.dialogPrivateVisible = true;



      }).catch(() => { });

    },
    async getPointsInfo() {
      const res = await this.$http.post("/token/getTokenBalance", {
        address: this.ruleUserForm.userAddress
      });
      this.points = res.data;
    },
    castingPoints() {
      this.$prompt('请输入铸造积分', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^[0-9]*$/,
        inputErrorMessage: '格式不正确'
      }).then(async ({ value }) => {
        const res = await this.$http.post("/token/depositTokenBalanceFromCenter", {
          mintToken: value
        });
        this.$message({
          message: "铸造成功！",
          type: 'success'
        });
        this.getPointsInfo();
      }).catch(() => {
      });
    },
    estructionPoints() {
      this.$prompt('请输入销毁积分', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^[0-9]*$/,
        inputErrorMessage: '格式不正确'
      }).then(async ({ value }) => {
        const res = await this.$http.post("/token/burnTokenBalanceToCenter", {
          mintToken: value
        });
        this.$message({
          message: "销毁成功！",
          type: 'success'
        });
        this.getPointsInfo();
      }).catch(() => {
      });
    },
    async queryPoints() {
      this.$prompt('请输入密码', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^\d{6,}$/,
        inputErrorMessage: '密码至少6位数'
      }).then(async ({ value }) => {
        const res = await this.$http.post("/token/getTokenBalanceInCenter", {
          address: this.$store.state.user.userAddress,
          password: value
        });
        this.$alert("余额："+res.data.amount, '积分中心数据', {
          confirmButtonText: '确定',

        });

      }).catch(() => { });
    }


  }

}
</script>

<style lang="less">
.nheader {
  height: 55px;
  user-select: none;
}

.title {
  font-size: 23px;
  color: aliceblue;
}

.logo {
  height: 40px;
  border-radius: 50%;
}

span i {
  color: #FF6C60;
}

.header-img-github {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin: 0 auto;
}

span b {
  color: #a9d86e;
}

.header-user {
  span {
    font-size: 18px;
  }

  img {
    width: 40px;
    height: 40px;
    vertical-align: middle
  }

  .el-popover__reference {
    width: 230px;
  }
}

.my_popover {
  min-width: 100px !important;
}

.tip-tag {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 10px;

  img {
    padding: 10px;
    width: 120px;
    height: 120px;
  }
}

.user-tip {
  padding: 5px;
  width: 100px;
  text-align: center;

  p {
    cursor: pointer;
  }
}

.el-popover {
  min-width: 0px !important;
}

.user_info_box {
  padding: 0px 0px 20px 0px;

  .info_box {
    min-height: 120px;
    margin-top: 20px;
    // border: 1px solid red;
  }

  .operation-box {
    margin-top: 15px;


  }

  .head {
    text-align: left;
    font-size: 16px;
    padding-bottom: 2px;
    color: #303133;
    border-bottom: 2px dashed #009ad6;
    margin-bottom: 10px;
  }

  .body {
    color: #606266;
    font-size: 14px;

    .el-col {
      padding: 3px 0px 3px 0px;
      text-align: left;
    }

    .grid-content {
      white-space: nowrap;
      /* 不换行 */
      overflow: hidden;
      /* 内容超过元素大小时隐藏 */
      text-overflow: ellipsis;
      /* 使用省略号表示被隐藏的内容 */
    }
  }
}

.personalInformation {
  .el-dialog__header {
    text-align: left;
  }
}

.user-item-info {
  color: rgb(96, 98, 102);
}

.user-dialog-footer {
  text-align: right
}
</style>
