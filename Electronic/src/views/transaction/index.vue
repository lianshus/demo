<template>
    <div class="tableContainer">
        <span>市场电力资源交易历史</span>
        <el-table :data="transactions" style="width: 100%">
            <el-table-column prop="id" label="id" width="80">
                <template v-slot="{ row }">
                    <div :class="{ 'hightlight': row.isUser }">{{ row.id }}</div>
                </template>
            </el-table-column>

            <el-table-column prop="sender" label="发送者">
                <template v-slot="{ row }">
                    <div :class="{ 'hightlight': row.isUser }">{{ row.sender }}</div>
                </template>
            </el-table-column>

            <el-table-column label="" width="100">
                <template v-slot="{ row }">
                    <i class="el-icon-top-right"></i>
                </template>
            </el-table-column>
            <el-table-column prop="receiver" label="接收者">
                <template v-slot="{ row }">
                    <div :class="{ 'hightlight': row.isUser }">{{ row.receiver }}</div>
                </template>
            </el-table-column>
            <el-table-column prop="amount" label="数量" width="80">
                <template v-slot="{ row }">
                    <div :class="{ 'hightlight': row.isUser }">{{ row.amount }}</div>
                </template>
            </el-table-column>
            <el-table-column prop="transferHash" label="交易hash">
                <template v-slot="{ row }">
                    <div :class="{ 'hightlight': row.isUser }">{{ row.transferHash }}</div>
                </template>
                
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
            const res = await this.$http.get('/token/getAllTransferToken');
            if (res.resultCode == 200) {
                this.transactions = res.data.map(item => {
                    const newItem = { ...item };

                    if (item.sender === this.userInfo.userAddress || item.receiver === this.userInfo.userAddress) {
                        newItem.isUser = true;
                    } else {
                        newItem.isUser = false;
                    }
                    return newItem;
                });
                console.log(this.transactions)
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
    color: rgb(101, 102, 103);
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
    color: black;
}

.topTitle {
    display: flex;
    justify-content: space-between;
    padding-left: 10px;
    padding-right: 10px;
    font-size: 20px;
}

.hightlight {
    color: rgb(222, 98, 98)
}
</style>


