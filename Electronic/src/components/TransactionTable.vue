<template>
    <div class="tableContainer">
        <span>历史订单</span>
        <el-table :data="transactions" style="width: 100%">
            <el-table-column prop="transactionId" label="id" width="180">
            </el-table-column>
            <el-table-column prop="amount" label="数量" width="180">
            </el-table-column>
            <el-table-column prop="price" label="单价">
            </el-table-column>

            <el-table-column prop="transactionStart" label="时间">
            </el-table-column>
            <el-table-column label="时间">
                <template slot-scope="scope">
                    <el-button @click="openModal(scope.row.transactionId)">修改</el-button>
                    <el-button @click="del(scope.row.transactionId)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="修改订单" :visible.sync="dialogVisible" width="300px">
            <el-input placeholder="请输入交易电量" v-model="updateForm.amount">

            </el-input>
            <el-input placeholder="请输入单价" v-model="updateForm.price">

            </el-input>
           
        </el-dialog>
    </div>
</template>

<script>
export default {
    props: ["transactions"],
    data() {
        return {
            dialogVisible: false,
            selectedId: "",
            updateForm: {},

        }
    },
    mounted() {
        setTimeout(() => {
            console.log("111111", this.transactions)
        }, 1000)

    },
    methods: {
        openModal(id) {
            this.selectedId = id;
            this.dialogVisible = true;
        },
        async del(id) {
            const res = await this.$http.post(`/transaction/deleteTransaction/${id}`);
            if (res.resultCode == 200) {
                this.$message({
                    message: res.message,
                    type: 'success'
                });
                setTimeout(() => {
                    location.reload();
                }, 3000)
            } else {
                this.$message({
                    message: res.message,
                    type: 'error'
                });
            }
        },

        async update(id) {
            const res = await this.$http.post(`/transaction/updateTransaction/${id}`, this.updateForm);
            if (res.resultCode == 200) {
                this.$message({
                    message: res.message,
                    type: 'success'
                });
                setTimeout(() => {
                    location.reload();
                }, 3000)
            } else {
                this.$message({
                    message: res.message,
                    type: 'error'
                });
            }
        },

     
    }
};
</script>

<style lang="less" scoped>
.tableContainer {
    border-radius: 15px;
    margin-top: 20px;
    padding: 15px 35px 15px 35px;
    color: white;
}

.el-form {
    background: rgb(101, 102, 103);
}

.el-form-item {
    border: none !important;
}

.Title {
    margin: 0px auto 48px auto;
    text-align: left;
    font-size: 40px;
}

.inputBox {
    width: 100%;
    height: 70px;
    background-color: rgb(19, 23, 33);
    border-radius: 10px;
    margin-top: 20px;
    margin-bottom: 20px;
}

.topTitle {
    display: flex;
    justify-content: space-between;
    padding-left: 10px;
    padding-right: 10px;
    font-size: 20px;
}
</style>


