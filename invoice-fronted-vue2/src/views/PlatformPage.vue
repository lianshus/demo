<template>
  <div class="mainC">

    <!-- 标题 -->
    <el-row class="bg-color" style="height: 90px">
      <el-col :span="5" :offset="1">
        <p class="title">基于区块链的政务票据平台</p>
      </el-col>
    </el-row>

    <!-- 轮播图 -->
    <el-row>
      <CarouselPict/>
    </el-row>

    <el-row style="margin-top: 30px">
      <el-col :span="20" :offset="2">
        <!-- 登录注册功能块 -->
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content bg-color" @click="buyers">
              <el-row type="flex" justify="center">
                <el-col :span="5" class="icon">
                  <i class="el-icon-s-home" />
                </el-col>
                <el-col :span="7" class="text">
                  <p>采购商登录</p>
                </el-col>
              </el-row>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-color" @click="providers">
              <el-row type="flex" justify="center">
                <el-col :span="5" class="icon">
                  <i class="el-icon-shopping-cart-full icon" />
                </el-col>
                <el-col :span="7" class="text">
                  <p>供应商登录</p>
                </el-col>
              </el-row>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-color" @click="register">
              <el-row type="flex" justify="center">
                <el-col :span="5" class="icon">
                  <i class="el-icon-user icon" />
                </el-col>
                <el-col :span="7" class="text">
                  <p>角色注册</p>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>

        <!--区块链信息表格 -->
        <el-row style="margin-top: 30px">
          <el-col>
            <BCTable />
          </el-col>
        </el-row>

      </el-col>
    </el-row>
  </div>
</template>

<script>
import BCTable from "../components/BCTable.vue";
import CarouselPict from "../components/CarouselPict.vue";

export default {
  components: {
    BCTable,
    CarouselPict
  },
  data() {
    return {
    };
  },
  
  methods: {
    buyers(){
      this.$router.push("/buyers")
    },
    providers(){
      this.$router.push("/providers")
    },
    register(){
      this.$router.push("/register")
    }
  },
};
</script>

<style scoped>
.mainC{
  background: #F5F7FA;
}
.title {
  font-size: 28px;
}
.icon {
  font-size: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  /* border:1px solid black; */
}
.text {
  font-size: 30px;
}
.bg-color {
  background: linear-gradient(to bottom, #edf5ff, #ffffff);
}
.grid-content {
  border-radius: 4px;
  min-height: 110px;
}
</style>
