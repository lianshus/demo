<template>
    <div class="">
        <el-button @click="buy">发布求购</el-button>
        <el-button @click="sell">售出电力</el-button>
        <el-row>
            <el-col :span="4" :offset="8">
                <Transaction :transactionType="transactionType" />
            </el-col>
        </el-row>
    </div>
</template>
  
<script>
import Transaction from '../../../components/Transaction.vue';
export default {
    data() {
        return {
            transactionType: 0,
        };
    },
    mounted() {
    },
    methods: {
        //用户求购
        buy() {
            this.transactionType = 1;
        },
        sell() {
            this.transactionType = 0;
        }
    },
    components: { Transaction }
}
</script>
  
<style lang="less" scoped></style>
  