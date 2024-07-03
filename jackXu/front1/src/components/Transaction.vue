<template>
    <el-form ref="form" :model="form" class="Container">
      <h3 class="Title">
        {{ transactionType == 0 ? '收购' : '售出' }}
      </h3>
      <el-form-item prop="code">
        总价为：{{ form.amount * form.price }}
      </el-form-item>
      <div>
        <el-form-item>
          <el-input type="text" v-model="form.amount" placeholder="Amount">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="text" v-model="form.price" placeholder="Price">
          </el-input>
          <hr>
        </el-form-item>
        <el-button type="primary" style="width:100%" @click="submit()">{{ transactionType == 0 ? '确认收购' :
          '确认售出' }}
        </el-button>
        <p>最终解释权归xxx所有</p>
      </div>

    </el-form>
</template>

<script>
export default {
  props: ["transactionType"],
  data() {

    return {
      form: {
        amount: 0,
        price: 0,
      },


    }
  },
  methods: {
    submit() {
      if (this.transactionType == 0) {
        this.sell();
      } else {
        this.purchase();
      }
    },

    //0
    async sell() {
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
  margin-top: 180px;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: aliceblue;
  border: 1px solid rgb(25, 23, 27);
  box-shadow: 0 0 25px #5b5a5b;
}

.Title {
  margin: 0px auto 48px auto;
  text-align: left;
  font-size: 40px;
}



</style>


