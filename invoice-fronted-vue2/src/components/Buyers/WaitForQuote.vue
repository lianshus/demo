<template>
  <el-col :span="14" :offset="5" style="margin-top: 25px">
    <el-result icon="success" title="成功生成采购订单如下">
      <template slot="extra">
        <el-card class="box-card">
          <div v-for="o in 4" :key="o" class="text item">
            {{ "列表内容 " + o }}
          </div>
        </el-card>
        <p style="margin-top:60px;font-size: 20px;">等待供应商报价中等待供应商报价中</p>
      </template>
    </el-result>
  </el-col>
</template>

<script>
export default {
  props: ["order"],
  data() {
    return {
      labelPosition: "left",
    };
  },
  methods: {},
};
</script>

<style scoped>
.el-form-item {
  margin-top: 60px;
}
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 100%;
}
</style>
