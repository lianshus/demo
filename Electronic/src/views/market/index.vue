<template>
  <div>
    <div class="box">
      <div class="man-list">
        <div v-for="(item, index) in topThree" :key="index">
          <div class="grid-content">
            <div class="topTitle">
              <p :class="item.transactionType == 1 ? 'green' : 'red'">
                {{ item.transactionType == 1 ? '求购' : '出售' }}
              </p>
              <div style="margin-top:20px;">
                <i class="el-icon-setting"></i>
              </div>
            </div>
            <div class="info">
              <div style="font-size: 45px;margin-left: 10px;margin-top: 10px;">
                {{ item.totalPrice }}
              </div>
              <div style="font-size: 20px;margin-left: 10px;margin-top: 10px;">
                #{{ item.transactionId }}
              </div>
            </div>

            <div class="detail">
              <div class="text">
                <div class="title">
                  数量(度):
                </div>
                <div class="value">
                  {{ item.amount }}
                </div>
              </div>
              <div style="margin-top:10px;font-size: 25px;">
                <i class="el-icon-right"></i>
              </div>

              <div class="text">
                <div class="title">
                  金额:
                </div>
                <div class="value">
                  {{ item.price }}
                </div>
              </div>

            </div>

            <hr>
            <div class="footer">
              <p>{{ item.transactionStart }}</p>
              <el-button style="margin-left:15px" @click="placeOrder(item.transactionType, item.transactionId)">{{
                item.transactionType == 1 ? '卖出' : '购买' }}</el-button>
            </div>

          </div>

        </div>

      </div>

      <div>
        <el-link @click=" getAllTransactions(1)">求购  </el-link>
        /
        <el-link @click="getAllTransactions(0)">出售</el-link>
      </div>

      <list :transactions="transactions" />

    </div>
    <Pagination :total="total" :pageSize="pageSize" :currentPage="currentPage" @handleCurrentChange="handleCurrentChange"/>

  </div>
</template>

<script>
import Pagination from '../layout/components/Pagination.vue';
import list from './components/list.vue';
export default {
  components: { list, Pagination },
  data() {
    return {
      topThree: [],
      transactions: [],
      detail: {},
      total: '',
      pageSize: 12,
      currentPage:1,
      action:'',
    }
  },
  mounted() {
    this.getTopTransactions();

    this.getAllTransactions(1);
    this.getDetail(1);

  },

  methods: {
    async getTopTransactions() {
      const res = await this.$http.post("/transaction/getAllTransaction",
        { page: 1, size: 5 }
      );
      const sortedTransactions = res.data.sort((a, b) => b.totalPrice - a.totalPrice);
      this.topThree = sortedTransactions.slice(0, 3);
      console.log(this.topThree)
    },

    // 0是求购，1是出售
    async getAllTransactions(type) {
      this.action = type;
      const res = await this.$http.post(`/transaction/getAllTransaction/${type}`,
        { page: this.currentPage, size: this.pageSize });
      this.transactions = res.data;
      this.total = res.total;
      console.log(type, this.transactions)
    },

    handleCurrentChange(val){
      this.currentPage = val;
      this.getAllTransactions(this.action);
    },

    // 0 是未完成，1是完成，address 发起人
    async getDetail(id) {
      const res = await this.$http.get(`/transaction/getTransactionForDetail/${id}`);
      this.detail = res.data;
      console.log(id, this.detail)
    },

    placeOrder(type, id) {
      if (type == 0) {
        this.buy(id);
      } else {
        this.sell(id);
      }
    },

    // 购买指定出售 id 的订单
    async buy(id) {
      const res = await this.$http.post(`/transactionSendFor/purchaseTransaction/${id}`);
      console.log(res.message)
    },

    // 卖出指定求购id 的订单
    async sell(id) {
      const res = await this.$http.post(`/transactionToPurchase/sendTransaction/${id}`);
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
  flex-wrap: nowrap;
  height: 350px;
}

.grid-content {
  height: 300px;
  width: 450px;
  background-color: rgba(179, 194, 242, 0.04);
  color: rgb(11, 11, 11);
  border:1px solid rgb(159, 161, 163);
  border-radius: 5px;
  margin-bottom: 10px;
}

.el-breadcrumb-item {
  color: white
}

.topTitle {
  display: flex;
  justify-content: space-between;
  padding-left: 10px;
  padding-right: 10px;
  font-size: 20px;
}

.title {
  height: 10%;
  margin-left: 10px;
  font-size: 15px;
}

.red {
  color: #FF494A;
}

.green {
  color: green;
}

.detail {
  display: flex;
  justify-content: space-between;
  margin-left: 15px;
  margin-right: 15px;
  margin-top: 15px;
}

.info {
  display: flex;
  flex-direction: row;
  margin-left: 20px;
}

.text {
  display: flex;
  flex-direction: column;
}

.title {
  font-size: 20px;
  color: rgb(71, 72, 72);
  margin-bottom: 20px;
}

.value {
  margin-left: 15px;
  font-size: 20px;
}

.footer {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-left: 20px;
  margin-right: 20px;
}
</style>
