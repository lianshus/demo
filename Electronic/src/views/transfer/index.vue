<template>
    <div>
      <el-row>
        <el-col :span="11">
          <el-form class="Container">
            <div class="info">
              <h1>转账</h1>
            </div>
            <div class="topTitle">
              <p>转账前请核对<span class="type">地址</span>是否正确。转账一旦发起，不能取消。</p>
            </div>
            <div class="inputBox">
              <el-row>
                <el-col :span="4">
                  <div @click="changeAmount(0.25)" class="percentage">
                    25%
                  </div>
                </el-col>
                <el-col :span="4">
                  <div  @click="changeAmount(0.50)" class="percentage">
                    50%
                  </div>
                </el-col>
                <el-col :span="4">
                  <div  @click="changeAmount(0.75)" class="percentage">
                  75%
                  </div>
                  </el-col>
                <el-col :span="4">
                  <div  @click="changeAmount(1)" class="percentage">
                    100%
                  </div>  
                </el-col>
              </el-row>
              <el-row>
                <div class="amountBox">
                  <el-input placeholder="请输入交易电量" v-model="form.amount"></el-input>
                </div>
              </el-row>
            </div>
            <div class="inputBox">
              <el-row>
                <span>
                  转账地址
                </span>
              </el-row>
              <el-row>
                <div class="amountBox">
                  <el-input placeholder="0x..." v-model="form.address"></el-input>
                </div>
              </el-row>
            </div>
  
            <div class="transferbutton" @click="transaction">
              确认转账
            </div>
  
          </el-form>
        </el-col>
  
        <el-col :span="11" :offset="1">
          <el-form class="Container">
            <div class="topTitle">
              <p>电力交易市场</p>
            </div>
            <div class="infoBox" style="border:none">
              <div class="label">
                本平台支持点对点交易、上架出售或求购电力资源订单。
                用户可在交易市场选择符合预期的用电量交易订单，与陌生节点完成交易，
                交易完成可在交易记录里查询到交易信息。
              </div>
            </div>
            <div class="infoBox">
              <div class="label">
                用户名
              </div>
              <div>
                {{ this.$store.state.user.username }}
              </div>
            </div>
            <div class="infoBox">
              <div class="label">
                账户地址
              </div>
              <div class="value" style="color:#13D988">
                {{ this.$store.state.user.userAddress }}
              </div>
            </div>
            <div class="infoBox">
              <div class="label">
                电量余额
              </div>
              <div class="value">
                {{ this.points }}
              </div>
            </div>
  
          </el-form>
        </el-col>
      </el-row>
      <el-row>
        <TransferData />
      </el-row>
    </div>
  </template>
  
  <script>
  import TransferData from '../transfer/components';
  export default {
    components: { TransferData },
    data() {
      return {
        points: "",
        form: {
          address: "",
          amount: ""
        },
  
      }
    },
    mounted() {
      this.getPointsInfo();
    },
    methods: {
      async getPointsInfo() {
        const res = await this.$http.post("/token/getTokenBalance", {
          address: this.$store.state.user.userAddress
        });
        this.points = res.data;
      },
      transaction() {
  
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
  
      },
      changeAmount(amount) {
        this.form.amount = this.points;
        this.form.amount = Math.round(this.points * amount);
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
  
  .Container {
    height: 500px;
    border-radius: 15px;
    background-clip: padding-box;
    text-align: left;
    margin: auto;
    color: white;
    padding: 15px 30px 15px 30px;
    background: #3A65FB;
    border: 1px solid #d7e2f7;
  }
  
  .info {
    display: flex;
    flex-direction: row;
    margin-left: 20px;
    padding-top: 15px;
  }
  
  demo-type {
    margin-top: 20px;
    font-size: 20px;
  }
  
  .infoBox {
    width: 100%;
    height: 60px;
    margin-top: 20px;
    color:#13D988;
    border-bottom: 1px solid #5F6470;
    padding-right: 10px;
  }
  
  .label {
    color: #5F6470;
    font-size: 15px;
    margin-bottom: 10px;
  }
  
  .value {
    color: #13D988
  }
  
  .topTitle {
    display: flex;
    font-size: 20px;
    color: #5F6470;
  
    span {
      color: 5F6470
    }
  
    .type {
      color: #CD3E53
    }
  }
  
  .inputBox {
    width: 100%;
    height: 110px;
    border-radius: 15px;
    border: #d9dee7 1px solid;
    margin-top: 15px;
    margin-bottom: 10px;
    padding-top: 10px;
    padding-left: 10px;
    color: white;
  
    .typebox {
      background-color: #1E3739;
      color: #0FC680;
      width: 40px;
      height: 20px;
      font-size: 13px;
      text-align: center;
      justify-content: center;
      border-radius: 5px;
    }
  
    .amountBox {
      font-size: 35px;
      margin-top: 5px;
    }
  
    span {
      color: #5F6470;
    }
  
  }
  
  .percentage {
    background-color: #f5ebc1;
    height: 35px;
    border-radius: 5px;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid transparent;
    margin-right: 20px;
    color: #5F6470;
  }
  
  .percentage:hover {
    border-color: #FEC848;
  }
  
  .transferbutton {
    background-color: #FEC848;
    width: 100%;
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 50px;
    border-radius: 15px;
  
  }
  </style>
  