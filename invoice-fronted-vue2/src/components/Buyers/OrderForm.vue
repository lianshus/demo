<template>
  <el-col :span="14" :offset="5" style="margin-top: 25px">
    <el-form
      :label-position="labelPosition"
      ref="ruleForm"
      :model="ruleForm"
      label-width="80px"
      size="large"
    >
      <el-form-item label="活动名称" size="large">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>

      <el-form-item label="活动名称">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>

      <el-form-item label="活动名称">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>

      <el-form-item label="活动名称">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="活动区域">
        <el-select v-model="ruleForm.region" placeholder="请选择活动区域">
          <el-option label="区域一" value="shanghai"></el-option>
          <el-option label="区域二" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="活动时间">
                <el-col :span="11">
                  <el-date-picker
                    type="date"
                    placeholder="选择日期"
                    v-model="form.date1"
                    style="width: 100%"
                  ></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                  <el-time-picker
                    placeholder="选择时间"
                    v-model="form.date2"
                    style="width: 100%"
                  ></el-time-picker>
                </el-col>
              </el-form-item>
     
              <el-form-item label="活动性质">
                <el-checkbox-group v-model="form.type">
                  <el-checkbox
                    label="美食/餐厅线上活动"
                    name="type"
                  ></el-checkbox>
                  <el-checkbox label="地推活动" name="type"></el-checkbox>
                  <el-checkbox label="线下主题活动" name="type"></el-checkbox>
                  <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="特殊资源">
                <el-radio-group v-model="form.resource">
                  <el-radio label="线上品牌商赞助"></el-radio>
                  <el-radio label="线下场地免费"></el-radio>
                </el-radio-group>
              </el-form-item> -->
      <el-form-item label="活动形式">
        <el-input type="textarea" v-model="ruleForm.desc"></el-input>
      </el-form-item>
      <el-row type="flex" justify="end">
        <el-col :span="2">
          <el-form-item>
            <el-button type="primary" @click="save">确认订单</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-col>
</template>

<script>
export default {
  data() {
    return {
      labelPosition: "left",

      ruleForm: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入活动名称", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        region: [
          { required: true, message: "请选择活动区域", trigger: "change" },
        ],
        date1: [
          {
            type: "date",
            required: true,
            message: "请选择日期",
            trigger: "change",
          },
        ],
        date2: [
          {
            type: "date",
            required: true,
            message: "请选择时间",
            trigger: "change",
          },
        ],
        type: [
          {
            type: "array",
            required: true,
            message: "请至少选择一个活动性质",
            trigger: "change",
          },
        ],
        resource: [
          { required: true, message: "请选择活动资源", trigger: "change" },
        ],
        desc: [{ required: true, message: "请填写活动形式", trigger: "blur" }],
      },
    };
  },
  methods: {
    save() {
      //   this.$refs[formName].validate((valid) => {
      //     if (valid) {
      //       alert("submit!");
      //     } else {
      //       console.log("error submit!!");
      //       return false;
      //     }
      //   });

      this.$confirm("请核对订单信息填写完成无误？", "确认信息", {
        distinguishCancelAndClose: true,
        confirmButtonText: "创建订单",
        cancelButtonText: "取消订单",
      })
        .then(() => {
          this.$message({
            message: "创建成功",
            type: "success",
          });

          this.$emit("receiveForm",this.ruleForm)

        })
        .catch((action) => {
          this.$message({
            type: "info",
            message: action === "cancel" ? "取消订单" : "",
          });
        });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style scoped>
.el-form-item {
  margin-top: 60px;
}
</style>
