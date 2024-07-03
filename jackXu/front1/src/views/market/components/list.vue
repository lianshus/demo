<template>
  <div class="box">
    <div class="man-box">
      <div class="man-list">
        <div v-for="(item, index) in transactions" :key="index">
          <div class="grid-content bg-purple">
            <h2>
              {{ item.totalPrice }}
            </h2>
            #{{ item.transactionId }}

            <div class="detail">
              <div>
                数量:{{ item.amount }}
              </div>
              <div>
                金额:
                {{ item.amount }}
              </div>

            </div>

            <hr>
            <p>{{ item.timestamp }}</p>
            <el-button>{{ item.transactionType == 0 ? '购买' : '卖出' }}</el-button>
          </div>

        </div>

      </div>
    </div>
  </div>
</template>
  
<script>
export default {
  props: ["transactions"],
  data() {

    return {
      transactions: [],
    }
  },
  mounted() {
  },
  destroyed() {

  },
  methods: {

  }
}
</script>
  
<style lang="less" scoped>
.box {
  margin-top: 20px;
}

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
  margin-bottom: 10px;
}

.el-breadcrumb-item {
  color: white
}
.detail{
  display: flex;
  justify-content:space-between;
}
</style>
  