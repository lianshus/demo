<template>
  <div>
    <el-row>
      <el-col :span="20" :offset="2" style="margin-top: 60px">
        <el-steps :align-center="true" :active="active" finish-status="success">
          <el-step title="创建订单"> </el-step>
          <el-step title="等待报价"></el-step>
          <el-step title="审批报价"></el-step>
        </el-steps>

        <el-row>
          <OrderForm v-on:receiveForm="receiveForm" v-if="active == 0" />
          <WaitForQuate :order="order" v-if="active == 1" />
          <GetQuote v-if="active == 2" />

          <el-result style="margin-top: 60px;" icon="success" title="已成功采购" v-if="active == 3" >
            <template slot="extra">
              <p style="margin-top: 60px; font-size: 20px">
                请等待供应商配送
              </p>
            </template>
          </el-result>
        </el-row>

        <el-row type="flex" justify="end">
          <el-col :span="6">
            <el-button>取消</el-button>
            <el-button type="success" style="margin-top: 12px" @click="next"
              >下一步</el-button
            >
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import GetQuote from "@/components/Buyers/GetQuote.vue";
import OrderForm from "@/components/Buyers/OrderForm.vue";
import WaitForQuate from "@/components/Buyers/WaitForQuote.vue";
export default {
  components: {
    OrderForm,
    WaitForQuate,
    GetQuote,
  },
  data() {
    return {
      active: 0,

      labelPosition: "left",

      order: {},
    };
  },

  methods: {
    next() {
      if (this.active++ > 2) this.active = 0;
    },
    onSubmit() {
      console.log("submit!");
    },
    receiveForm(data) {
      this.order = data;
      console.log("form", this.form);
    },

    // createOrder()
  },
};
</script>
