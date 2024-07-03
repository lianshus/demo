<template>
    <div>
        <el-row>
            <el-col :span="11">
                <el-form ref="form" :model="form" class="Container">
                    <div class="info">
                        <div class="demo-type">
                            <el-avatar :size="85" src="https://empty">
                                <img :src="imgUrl" />
                            </el-avatar>
                        </div>
                        <div style="font-size: 20px;margin-left: 10px;margin-top: 10px;">
                            #{{ detail.transactionId }}
                        </div>
                    </div>

                    <div class="topTitle">
                        <p>用户
                            <span>
                                {{ detail.address }}
                            </span>
                            正在
                            <span class="type">
                                {{ detail.transactionType == 0 ? '出售' : '求购' }}
                            </span>
                            电量。
                            你想{{ detail.transactionType == 0 ? '购买' : '售出' }}电量吗？

                        </p>
                    </div>
                    <div class="inputBox">
                        <el-row>
                            <div class="typebox">
                                {{ detail.transactionType == 0 ? '购买' : '售出' }}
                            </div>
                        </el-row>
                        <el-row>
                            <div class="amountBox">
                                {{ detail.amount }}
                                <el-progress :percentage="100" status="success" color="#0ECB81"
                                    style="margin-top:-8px"></el-progress>
                            </div>
                        </el-row>
                    </div>
                    <div class="inputBox">
                        <el-row>
                            <span>
                                总价为
                            </span>
                        </el-row>
                        <el-row>
                            <div class="amountBox">
                                {{ detail.totalPrice }}
                            </div>
                        </el-row>
                        <el-row>
                            <span>
                                电量单价：{{ detail.price }}
                            </span>
                        </el-row>
                    </div>
                    <el-button type="primary" style="width:100%;margin-top:20px;"
                        @click="placeOrder(detail.transactionType, detail.transactionId,detail.totalPrice)">
                        {{ detail.transactionType == 0 ? '购买' : '售出' }}
                    </el-button>
                </el-form>
            </el-col>

            <el-col :span="11" :offset="1">
                <el-form ref="form" :model="form" class="Container">
                    <div class="topTitle">
                        <p>订单详情</p>
                    </div>
                    <div class="infoBox">
                        <div class="label">
                            订单id
                        </div>
                        <div class="value">
                            {{ detail.transactionId }}
                        </div>
                    </div>
                    <div class="infoBox">
                        <div class="label">
                            发起地址
                        </div>
                        <div class="value" style="color:#13D988">
                            {{ detail.address }}
                        </div>
                    </div>
                    <div class="infoBox">
                        <div class="label">
                            订单状态
                        </div>
                        <div class="value">
                            {{ detail.status == 0 ? '暂未交易' : '交易成功' }}
                        </div>
                    </div>
                    <div class="infoBox">
                        <div class="label">
                            电量数量
                        </div>
                        <div class="value">
                            {{ detail.amount }}
                        </div>
                    </div>
                    <div class="infoBox">
                        <div class="label">
                            电量单价
                        </div>
                        <div class="value">
                            {{ detail.price }}
                        </div>
                    </div>
                    <div class="infoBox">
                        <div class="label">
                            发起时间
                        </div>
                        <div class="value">
                            {{ detail.transactionStart }}
                        </div>
                    </div>
                    <div class="infoBox">
                        <div class="label">
                            交易Hash
                        </div>
                        <div class="value">
                            {{ detail.transactionHash }}
                        </div>
                    </div>
                </el-form>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import logo1 from "../assets/logo1.png";
export default {
    props: ["id"],
    data() {
        return {
            detail: {},
            imgUrl: logo1
        }
    },
    created() {
        this.getDetail(this.id);
    },
    methods: {
        async getDetail(id) {
            const res = await this.$http.get(`/transaction/getTransactionForDetail/${id}`);
            this.detail = res.data;
            console.log(res.data)
        },

        placeOrder(type, id,amount) {
            if (type == 0) {
                this.buy(id,amount);
            } else {
                this.sell(id);
            }
        },

        // 购买指定出售 id 的订单
        async buy(id,amount) {
            this.$confirm(`确认支付 :  ${amount}`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                const res = await this.$http.post(`/transactionSendFor/purchaseTransaction/${id}`);
                if (res.resultCode == 200) {
                    this.$message({
                        message: '购买成功',
                        type: 'success'
                    });
                } else {
                    this.$message({
                        message: res.message,
                        type: 'error'
                    });
                }
            })
        },

        // 卖出指定求购id 的订单，处理求购
        async sell(id) {
            const res = await this.$http.post(`/transactionToPurchase/sendTransaction/${id}`);
            if (res.resultCode == 200) {
                this.$message({
                    message: res.message,
                    type: 'success'
                });
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
.Container {
    height: 550px;
    border-radius: 15px;
    background-clip: padding-box;
    text-align: left;
    margin: auto;
    color: white;
    padding: 15px 30px 15px 30px;
    background: #171D29;
    border: 1px solid #171D29;
}

.info {
    display: flex;
    flex-direction: row;
    margin-left: 20px;
    padding-top: 15px;
}

demo-type {
    margin-top: 20px;
}

.infoBox {
    width: 100%;
    height: 40px;
    margin-top: 20px;
    border-bottom: 1px solid #5F6470;
    display: flex;
    justify-content: space-between;
    padding-right: 10px;
}

.label {
    color: #5F6470;
    font-size: 15px;
    margin-bottom: 5px;
}

.value {
    color: rgb(72, 70, 70);
}

.topTitle {
    display: flex;
    font-size: 20px;
    color: #5F6470;

    span {
        color: rgb(22, 21, 21);
    }

    .type {
        color: #CD3E53
    }
}

.inputBox {
    width: 100%;
    height: 110px;
    border-radius: 15px;
    background-color: #f8f9fa;
    margin-top: 15px;
    margin-bottom: 10px;
    padding-top: 10px;
    padding-left: 10px;
    color: rgb(22, 21, 21);

    .typebox {
        background-color: #e9f0f0;
        color: #0FC680;
        width: 40px;
        height: 20px;
        font-size: 13px;
        text-align: center;
        justify-content: center;
        border-radius: 5px;
    }

    .amountBox {
        font-size: 35px;
        margin-top: 5px;
    }

    span {
        color: #5F6470;
    }
}
</style>


