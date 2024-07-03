<template>
  <div>
    <el-card>
      <el-row type="flex" align="center" justify="center">
        <el-col :span="6">
          <div class="err">
            <img src="@/assets/image/401.png" alt="" srcset="">
            <span>401</span>
            <div>抱歉,您没有权限访问该页面。</div>
            <el-button size="mini" type="primary" @click="home">返回首页</el-button>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'error',
  data () {
    return {}
  },
  methods: {
    home () {
      this.$router.push({ name: 'home' })
    }
  }
}
</script>

<style lang="less" scoped>
.err {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  span{
    font-size: 30px;
  }
  div{
    padding: 3px;
    font-size: 14px;
    color: #ccc;
  }
  .el-button{
    font-size: 13px;
  }
}
</style>
