<template>

    <div class="box">
      <div class="man-box">
        <div class="man-list">
          <div class="content" v-for="(item, index) in transactions" :key="index" @click="getDetail(item.transactionId)">
            <div class="grid-content bg-purple">
              <div class="info">
                <div class="demo-type">
                  <el-avatar :size="85" src="https://empty">
                    <img :src="imgUrl" />
                  </el-avatar>
                </div>
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
                <el-button style="margin-left:15px">{{ item.transactionType == 0 ? '购买' : '售出' }}</el-button>
              </div>
            </div>

          </div>

        </div>
      </div>
    </div>

</template>
  
<script>
import logo1 from "../../../assets/logo1.png";
export default {
  props: ["transactions"],
  data() {
    return {
      imgUrl: logo1,
      detail: {},
    };
  },
  destroyed() {
  },
  methods: {
    async getDetail(id) {
      this.$router.push({ name: 'detail', params: { id: id } });
    }
  },
}
</script>
  
<style lang="less" scoped>
.box {
  margin-top: 20px;
  margin-bottom: 20px;
}

.man-list {
  min-width: 1300px;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}

.grid-content {
  height: 270px;
  width: 400px;
  background-color: rgba(246, 241, 241, 0.04);
  color: rgb(159, 161, 163);
  border:1px solid rgb(159, 161, 163);
  border-radius: 10px;
  margin-bottom: 10px;
  margin-left: 30px;
}

.content {
  :hover {
    background-color: #b6c7e5
  }
}

.el-breadcrumb-item {
  color: white
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
  padding-top: 15px;
}

demo-type {
  margin-top: 20px;
}

.text {
  display: flex;
  flex-direction: column;
}

.title {
  font-size: 20px;
  color: rgb(71, 72, 72);
}

.value {
  color: #816FD2;
  font-size: 30px;
}

.footer {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-left: 20px;
  margin-right: 20px;
}
</style>
  