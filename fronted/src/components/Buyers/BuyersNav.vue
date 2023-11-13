<template>
  <el-menu
    :default-active="activeIndex2"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    background-color="#2853E4"
    text-color="#fff"
    active-text-color="#ffd04b"
    router="true"
  >
    <el-row>
      <el-col :span="1">
        <el-menu-item index="/ordersmanage">我的订单</el-menu-item>
      </el-col>
      <el-col :span="1">
        <el-menu-item index="/createorder">采购</el-menu-item>
      </el-col>
      <el-col :offset="19" :span="1">
        <div class="icon">
          <i class="el-icon-user-solid"></i>
        </div>        
      </el-col>
      <el-col :span="1">
        <el-menu-item style="font-size:18px">0x123...111</el-menu-item>
      </el-col>
    </el-row>
  </el-menu>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1",
    }
  },
  mounted(){
    this.handleAddress("0x18510d9433c63b1d6a8fe8a83a4c5150c0c17c74");
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },

    handleAddress(address){
      const front = address.substring(0,5)+'...'+address.substring(39,)
      console.log(front)
    }
  },


};
</script>

<style>
.icon{
  font-size: 35px;
  margin-top: 10px;
}
</style>
