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
          "name": "转账市场",
          "parentId": 0,
          "path": "/",
          "sort": 0,
          children: [
            {
              "icon": "string",
              "id": 3,
              "name": "转账统计",
              "parentId": 0,
              "path": "/3",  
              "sort": 0
            },
            {
              "icon": "string",
              "id": 4,
              "name": "积分转账",
              "parentId": 0,
              "path": "/4",
              "sort": 0
            }
          ]
        },
        {
          "icon": "string",
          "id": 2,
          "name": "市场",
          "parentId": 0,
          "path": "/market",
          "sort": 0,
        },
        {
          "icon": "string",
          "id": 2,
          "name": "个人中心",
          "parentId": 0,
          "path": "/user",
          "sort": 0,
        },
        {
              "icon": "string",
              "id": 3,
              "name": "求购",
              "parentId": 0,
              "path": "/1",  
              "sort": 0
            },
            {
              "icon": "string",
              "id": 4,
              "name": "出售",
              "parentId": 0,
              "path": "/sell_page",
              "sort": 0
            }
      ]
    }
  }
}
</script>

<style lang="less" scoped>
.el-header,
.el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 55px;
}

.el-aside {
  background-color: #D3DCE6;
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
  color: #006cf9 !important;
  background-color: #10141f !important;
}
</style>
