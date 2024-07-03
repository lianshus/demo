<template>
  <div class="box">
    <el-card>
      <el-form ref="form" :model="form" label-width="120px" :rules="rules">
        <el-form-item label="用户名">
          {{ this.$store.state.user.username }}
        </el-form-item>
        <el-form-item label="积分余额">
          {{ this.points }}
        </el-form-item>
        <el-form-item label="本账户地址">
          {{ this.$store.state.user.userAddress }}
        </el-form-item>
        <el-form-item label="目的账户地址" prop="address">
          <el-input type="text" autocomplete="off" v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item label="转账积分" prop="amount">
          <el-input type="text" autocomplete="off" v-model="form.amount"></el-input>
        </el-form-item>
        <div style="text-align: center;">
          <el-button size="medium" type="primary" @click="transaction('form')">转账</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'home',
  data() {
    return {
      points: "",
      form: {
        address: "",
        amount: ""
      },
      rules: {
        address: [
          { required: true, message: '请输入目的账户地址', trigger: 'blur' },
        ],
        amount: [

          { required: true, message: '请输入转账积分', trigger: 'blur' }
        ],
      }

    }
  },
  mounted() {
    this.getPointsInfo();
  },
  destroyed() {

  },
  methods: {
    async getPointsInfo() {
      const res = await this.$http.post("/token/getTokenBalance", {
        address: this.$store.state.user.userAddress
      });
      this.points = res.data;
    },
    transaction(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (!valid) {
          return;
        }
        this.$confirm('是否进行转账操作？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          const res = await this.$http.post("/token/transferToken", this.form);
          this.$message({
            type: 'success',
            message: res.message
          });
          this.getPointsInfo();
          this.form = {
            address: "",
            amount: ""
          }
        }).catch(() => {
        });
      })
    }
  }
}
</script>

<style lang="less" scoped>
.card-box {
  font-size: 14px;
  color: #606266;

  .card-body {
    height: 300px;
  }
}

.card-box .card-head {
  font-size: 17px;
  text-align: left;
  border-bottom: 1px solid #ebeef5;
  padding-bottom: 10px;
}

.info-line {
  padding-bottom: 25px;

  .line-content {
    color: #909399;
  }
}

.box {
  width: 500px;
  min-width: 400px;
  margin: 0 auto;
}
</style>
