<template>
  <el-carousel :interval="5000" arrow="always" height="500px">
    <el-carousel-item v-for="item in pict_list" :key="item.id">
      <img :src="item.url" style="width:100%;height:100%"/>
    </el-carousel-item>
  </el-carousel>
</template>
<script>
export default{
  data(){
    return{
      pict_list:[
        {
          id:1,
          url:require('@/static/BlockChain.jpg')
        },
        {
          id:2,
          url:require('@/static/platform.webp')
        }
      ]
    }
  },
  methods:{

  }
}
</script>

<style>
.el-carousel {
  width: 100%;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
