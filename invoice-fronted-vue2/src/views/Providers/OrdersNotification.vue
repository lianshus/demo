<template>
  <div style="background-color: #f7f8fb; height: 100vh">
    <el-row>
      <el-col :span="14" :offset="5" style="margin-top: 20px">
        <el-row class="filter">
          <el-row>
            <el-col :span="2" class="title" style="color: black">
              筛选条件
            </el-col>
          </el-row>

          <!--条件查询 -->
          <el-row>
            <el-col :span="22" :offset="1" class="search">
              <el-row style="margin-top: 20px">
                <el-col :span="3" style="margin-top: 10px">
                  <span style="font-size: 20px; color: #8a8989">招标状态:</span>
                </el-col>
                <el-col :span="3">
                  <el-button class="button" style="font-size: 18px"
                    >招标中</el-button
                  >
                </el-col>

                <el-col :span="3">
                  <el-button class="button" style="font-size: 18px"
                    >招标结束</el-button
                  >
                </el-col>
              </el-row>

              <el-row style="margin-top: 20px">
                <el-col :span="3" style="margin-top: 5px">
                  <span style="font-size: 20px; color: #8a8989">标题名称:</span>
                </el-col>
                <el-col :span="6">
                  <el-input />
                </el-col>
                <el-col :span="3">
                  <el-button type="primary">查询</el-button>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </el-row>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="14" :offset="5" style="margin-top: 40px">
        <el-row v-for="item in notice" :key="item.title" class="block">
          <!-- 标题 -->
          <el-row>
            <el-col :span="3">
              <span class="title">[采购公告]：</span>
            </el-col>
            <el-col :span="16">
              <el-link
                style="font-size: 20px"
                @click="dialogFormVisible = true"
              >
                {{ item.title }}
              </el-link>
            </el-col>
          </el-row>

          <!-- 缩略信息 -->
          <el-row type="flex" justify="between">
            <el-col :span="16" style="margin-top: 10px">
              <el-link style="font-size: 20px">
                {{ item.info }}
              </el-link>
            </el-col>
            <el-col :span="8" style="font-size: 20px; margin-top: 12px">
              {{ item.time }}
            </el-col>
          </el-row>

          <el-row type="flex" justify="between">
            <el-col :span="2" style="font-size: 15px; margin-top: 10px">
              <el-tag
                class="ml-2"
                :type="item.state == 0 ? 'warning' : 'success'"
              >
                {{ item.state == 0 ? "招标中" : "招标结束" }}
              </el-tag>
            </el-col>
          </el-row>
        </el-row>
      </el-col>
    </el-row>

    <el-dialog title="采购公告" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="活动名称" :label-width="80">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动区域" :label-width="80">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      active: 0,

      labelPosition: "left",

      order: {},

      notice: [
        {
          title:
            "中国铁路哈尔滨局集团有限公司绥化车务段2024年铁路货物装卸业务外包服务招标公告",
          info: "中国铁路哈尔滨局集团有限公司绥化车务段2024年铁路货物装卸业务外包服务招标公告",
          time: "2022-02-02",
          state: 0,
        },
        {
          title:
            "中国铁路哈尔滨局集团有限公司绥化车务段2024年铁路货物装卸业务外包服务招标公告",
          info: "中国铁路哈尔滨局集团有限公司绥化车务段2024年铁路货物装卸业务外包服务招标公告",
          time: "2022-02-02",
          state: 0,
        },
        {
          title:
            "中国铁路哈尔滨局集团有限公司绥化车务段2024年铁路货物装卸业务外包服务招标公告",
          info: "中国铁路哈尔滨局集团有限公司绥化车务段2024年铁路货物装卸业务外包服务招标公告",
          time: "2022-02-02",
          state: 0,
        },
        {
          title:
            "中国铁路哈尔滨局集团有限公司绥化车务段2024年铁路货物装卸业务外包服务招标公告",
          info: "中国铁路哈尔滨局集团有限公司绥化车务段2024年铁路货物装卸业务外包服务招标公告",
          time: "2022-02-02",
          state: 1,
        },
      ],
      
      dialogFormVisible: false,
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
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

<style>
.filter {
  height: 210px;
  padding-top: 1%;
  padding-bottom: 1%;
  background-color: #ffffff;
}

.search {
  margin-top: 10px;
  border: 1px solid #eeeeee;
  height: 140px;
}

.block {
  border-bottom: 1px solid #eeeeee;
  height: 140px;
  padding-top: 1%;
  background-color: #ffffff;
}
.block:hover {
  background-color: #eee;
}
.title {
  font-size: 20px;
  color: #0055d3;
}

.button {
  border: none;
}
</style>
