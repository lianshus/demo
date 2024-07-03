<template>
    <div class="">
        <div>
        <el-link @click=" getAllTransactions(0)"> 出售 </el-link>
        /
        <el-link @click="getAllTransactions(1)">求购</el-link>
      </div>
        <el-row>
            <el-col>
                <TransactionTable :transactions="transactions" />
            </el-col>
        </el-row>
        <Pagination :total="total" :pageSize="pageSize" :currentPage="currentPage"
            @handleCurrentChange="handleCurrentChange" />
    </div>
</template>
  
<script>
import Pagination from "../../layout/components/Pagination.vue";
import TransactionTable from '../../../components/TransactionTable.vue';
export default {
    components: { TransactionTable,Pagination },
    data() {
        return {
            transactions: [],
            total: '',
            currentPage: 1,
            pageSize: 7,
            action: '',

        };
    },
    mounted() {
        this.getAllTransactions(0);
    },
    methods: {
        async getAllTransactions(type) {
            this.action = type;
            const res = await this.$http.post(`/transaction/getOwnerTransaction/${type}`,
                { page: this.currentPage, size: this.pageSize });
            this.transactions = res.data;
            this.total = res.total;
            console.log(type, this.transactions)
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.getAllTransactions(this.action);
        }
    },


}
</script>
  
<style lang="less" scoped></style>
  