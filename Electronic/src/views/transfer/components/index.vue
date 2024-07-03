<template>
    <div class="tableContainer">
        <span>转账历史</span>
        <el-table :data="transactions" style="width: 100%">
            <el-table-column prop="id" label="id" width="80">
            </el-table-column>
            <el-table-column label="" width="180">
                <template v-slot="{ row }">
                    <div class="typebox" :style="{'background-color': row.sender === userInfo.userAddress ? '#0FC680' : '#FF4238'}">
                        {{ row.sender === userInfo.userAddress ? '购买' : '售出' }}
                    </div>
                </template>
            </el-table-column>
            <el-table-column prop="sender" label="">
            </el-table-column>

            <el-table-column label="" width="100">
                <template v-slot="{ row }">
                    <i class="el-icon-top-right"></i>
                </template>
            </el-table-column>
            <el-table-column prop="receiver" label="">
            </el-table-column>
            <el-table-column prop="amount" label="数量" width="80">
            </el-table-column>
            <el-table-column prop="transferHash" label="交易hash">
            </el-table-column>

        </el-table>
    </div>
</template>

<script>
export default {
    data() {
        return {
            transactions: []
        }
    },
    computed: {
        userInfo() {
            return this.$store.state.user;
        },
    },
    mounted() {
        this.getData();
    },
    methods: {
        async getData() {
            const res = await this.$http.get('/token/getTransferToken');
            if (res.resultCode == 200) {
                this.transactions = res.data;
            }
        },


    }
};
</script>

<style lang="less" scoped>
.tableContainer {
    border-radius: 15px;
    margin-top:20px;
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

.typebox {
    background-color: #1E3739;
    width: 40px;
    height: 20px;
    font-size: 13px;
    text-align: center;
    justify-content: center;
    border-radius: 5px;
    color:black;
}

.topTitle {
    display: flex;
    justify-content: space-between;
    padding-left: 10px;
    padding-right: 10px;
    font-size: 20px;
}

</style>


