<template>
  <div class="box">
    <div></div>
    <div class="man-box">
      <div class="man-list">
          <div class="grid-content bg-purple" v-for="item in 4">
            111
          </div>
      </div>
    </div>
              
    <el-breadcrumb separator="/">
  <el-breadcrumb-item :to="{ path: '/' }">购买</el-breadcrumb-item>
  <el-breadcrumb-item :to="{ path: '/' }">出售</a></el-breadcrumb-item>
</el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: 'sell_page',
  data() {
    return {
      transactions:[],
    }
  },
  mounted() {
    this.getAllTransactions();
  },
  destroyed() {

  },
  methods: {
    async getAllTransactions(){
      const res = await this.$http.post("/transaction/getAllTransaction",{page:1,size:5});
      this.transactions = res.list;
      console.log(this.transactions)
    }
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
  height: 500px;
}

.grid-content {
  height: 150px;
  width: 300px;
  background-color: rgba(255, 255, 255, 0.04);
  color: rgb(209, 213, 219);
  border-radius: 5px;
  margin-bottom:10px;
}
.el-breadcrumb-item{
  color:white
}
</style>
