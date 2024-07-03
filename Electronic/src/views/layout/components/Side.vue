<template>
  <div>
    <el-row class="tac">
      <el-col :span="24">
        <el-menu :default-active="this.$route.path" active-text-color router class="sideStyle">
          <MenuTree v-for="item in menuTreeItems" :key="item.id" :menu="item"></MenuTree>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import MenuTree from '../../../components/MenuTree.vue'
export default {
  name: 'Side',
  components: {
    MenuTree
  },
  data() {
    return {
      menuTreeItems: {},
    }
  },
  mounted() {
    this.initMenuTree();
  },
  methods: {
    // 菜单树初始化
    async initMenuTree() {
      //  const res=await this.$http.get("/menu/tree");
      //  this.menuTreeItems=res.data;
      this.menuTreeItems = [
        {
          "icon": "string",
          "id": 1,
          "name": "市场",
          "parentId": 0,
          "path": "/market",
          "sort": 0,
        },
        {
          "icon": "string",
          "id": 2,
          "name": "订单管理",
          "parentId": 0,
          "path": "/user",
          "sort": 0,
          children: [
            {
              "icon": "string",
              "id": 3,
              "name": "上架订单",
              "parentId": 0,
              "path": "/orders",  
              "sort": 0
            },
            {
              "icon": "string",
              "id": 4,
              "name": "历史订单",
              "parentId": 0,
              "path": "/history",
              "sort": 0
            }
          ]
        },
        {
          "icon": "string",
          "id": 5,
          "name": "转账",
          "parentId": 0,
          "path": "/transfer",
          "sort": 0,
        },  
        {
          "icon": "string",
          "id": 6,
          "name": "交易",
          "parentId": 0,
          "path": "/transaction",
          "sort": 0,
        },     
      ]
    }
  }
}
</script>

<style lang="less" scoped>
.el-header,
.el-footer {
  background-color: rgb(55, 153, 234);
  color: #333;
  text-align: center;
  line-height: 55px;
}

.el-aside {
  background-color: #0070C6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body>.el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.sideStyle {
  padding-left: 0px !important;
  border: none;
  color: #0070C6 !important;
}
</style>
