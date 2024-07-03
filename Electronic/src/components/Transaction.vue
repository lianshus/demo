<template>
    <el-form ref="form" :model="form" class="Container">
      <div class="topTitle">
        <p>{{ transactionType == 0 ? '售出' : '收购' }}</p>
      <div style="margin-top:20px;">
                <i class="el-icon-setting"></i>
      </div>
      </div>
      
      <h3 class="Title">
        总价为：{{ form.amount * form.price }}
      </h3>
      <div class="inputBox">
        <el-form-item>
          
          <el-input type="text" v-model="form.amount" placeholder="交易电量">
          </el-input>
          
        </el-form-item>
      </div>
      <div class="inputBox">
        <el-form-item>
          <el-input type="text" v-model="form.price" placeholder="单价">
          </el-input>
          <!-- <hr> -->
        </el-form-item></div>
        <el-button type="primary" style="width:100%" @click="submit()">{{ transactionType == 0 ? '确认售出' :
          '确认收购' }}
        </el-button>
        <p>最终解释权归{{systemName}}所有</p>


    </el-form>
</template>

<script>
export default {
  props: ["transactionType"],
  data() {

    return {
      systemName:"电力交易市场",
      form: {
        amount: 0,
        transactionType:'',
        price: 0,
      },
    }
  },
  mounted(){
    
  },
  methods: {
  
    submit() {
      if (this.transactionType == 1) {
        this.purchase();
      } else {
        this.sell();
      }
    },

    //0
    async sell() {
      this.form.transactionType = this.transactionType
      const res = await this.$http.post(
        "/transactionSendFor/uploadSendTransaction",
        this.form
      );
      if (res.resultCode == 200) {
        console.log(res.message)
        this.$message({
          message: res.message,
          type: 'success'
        });
      }
    },

    //1
    async purchase() {
      this.form.transactionType = this.transactionType
      const res = await this.$http.post(
        "/transactionToPurchase/uploadPurchaseTransaction",
        this.form
      );
      if (res.resultCode == 200) {
        this.$message({
          message: res.message,
          type: 'success'
        });
      }
    }
  }
};
</script>

<style lang="less" scoped>
.Container {
  border-radius: 15px;
  background-clip: padding-box;
  text-align: left;
  margin: auto;
  margin-top: 100px;
  width: 500px;
  color:#3A65FB;
  padding: 15px 35px 15px 35px;
  background: #3a65f2;
  border: 1px solid rgb(226, 226, 228);
  box-shadow: 0 0 25px rgb(245, 244, 244);
}
.el-form{
  background: rgb(101, 102, 103);
}
.el-form-item{
  border:none !important;
}

.Title {
  margin: 0px auto 48px auto;
  text-align: left;
  font-size: 40px;
}
.inputBox{
  width:100%;
  height:70px;
  background-color: rgb(241, 242, 244);
  border-radius: 10px;
  margin-top: 20px;
  margin-bottom: 20px;
}

.topTitle {
  display: flex;
  justify-content: space-between;
  padding-left: 10px;
  padding-right: 10px;
  font-size: 20px;
}

</style>


