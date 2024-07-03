<template>
    <div class="box">
        <div>

        </div>
        <div class="man-box">
            <div class="man-list">
                <div class="grid-content bg-purple" v-for="item in 4">
                    111
                </div>
            </div>
        </div>

        <el-button>发布求购</el-button>
        <el-button>Token售出</el-button>

        <el-row>
            <el-col :span="4" :offset="4">
                <Transaction transactionType="transactionType" />
            </el-col>
        </el-row>



    </div>
</template>
  
<script>
import Transaction from '../../components/Transaction.vue';
export default {
    data() {
        return {
            transactionType: 0,
        };
    },
    mounted() {
        this.getAllTransactions();
    },
    destroyed() {
    },
    methods: {},
    components: { Transaction }
}
</script>
  
<style lang="less" scoped>
.man-list {
    margin-top: 10px;
    min-width: 1300px;
    display: flex;
    justify-content: space-around;
    flex-direction: row;
    flex-wrap: wrap;
    height: 500px;
}

.grid-content {
    height: 150px;
    width: 300px;
    background-color: rgba(255, 255, 255, 0.04);
    color: rgb(209, 213, 219);
    border-radius: 5px;
    margin-bottom: 10px;
}

.el-breadcrumb-item {
    color: white
}
</style>
  