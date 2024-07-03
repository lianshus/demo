<template>
  <div class="box">
    <div></div>
    <div class="man-box">
      <div class="man-list">
        <div v-for="(item, index) in topThree" :key="index">
          <div class="grid-content bg-purple">

            <div class="title">
              <p>{{ item.totalPrice }}</p>
              <p :class="item.transactionType == 0 ? 'red' : 'green'">{{ item.transactionType == 0 ? '出售' : '求购' }}</p>
            </div>
            <div class="item">
              {{ item.amount }}
            </div>
            <div>
              {{ item.price }}
            </div>



            <div>

            </div>

          </div>
          <el-button>{{ item.transactionType == 0 ? '购买' : '卖出' }}</el-button>
        </div>

      </div>
    </div>

    <div>
      <el-link @click=" getAllTransactions(1)"> 购买 </el-link>
      /
      <el-link @click="getAllTransactions(0)">出售</el-link>
    </div>

    <list :transactions="transactions" />
  </div>
</template>

<script>
import list from './components/list.vue';
export default {
  components: { list },
  data() {
    return {
      topThree: [],

      transactions: [],

      detail: {},
    }
  },
  mounted() {
    this.getTopTransactions();
    this.getAllTransactions(1);
    this.getDetail(1);

  },
  destroyed() {

  },
  methods: {
    async getTopTransactions() {
      const res = await this.$http.post("/transaction/getAllTransaction", { page: 1, size: 5 });
      const sortedTransactions = res.list.sort((a, b) => b.totalPrice - a.totalPrice);
      this.topThree = sortedTransactions.slice(0, 3);
      console.log(this.topThree)
    },

    // 0是出售，1是求购
    async getAllTransactions(type) {
      const res = await this.$http.post(`/transaction/getAllTransaction/${type}`, { page: 1, size: 5 });
      this.transactions = res.list;
      console.log(type, this.transactions)
    },

    // 0 是未完成，1是完成，address 发起人
    async getDetail(id) {
      const res = await this.$http.get(`/transaction/getTransactionForDetail/${id}`);
      this.detail = res.data;
      console.log(id, this.detail)
    },

    // 购买指定出售 id 的订单
    async buy(id) {
      const res = await this.$http.get(`/transactionSendFor/purchaseTransaction/${id}`);
      console.log(res.message)
    },

    // 卖出指定求购id 的订单
    async sell(id) {
      const res = await this.$http.get(`/transactionToPurchase/sendTransaction/${id}`);
      console.log(res.message)
    },

  }
}
</script>

<style lang="less" scoped>
.man-list {
  min-width: 1300px;
  display: flex;
  justify-content: space-around;
  flex-direction: row;
  flex-wrap: wrap;
  height: 200px;
}

.grid-content {
  height: 150px;
  width: 300px;
  background-color: rgba(255, 255, 255, 0.04);
  color: rgb(209, 213, 219);
  border-radius: 5px;
  margin-bottom: 10px;
}

.el-breadcrumb-item {
  color: white
}

.title {
  height: 10%;
  margin-left: 10px;
  font-size: 15px;
}

.red {
  color: red;
}

.green {
  color: green;
}
</style>
