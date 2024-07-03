<template>
    <div class="block">
        <el-pagination @size-change="handleSizeChange" 
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage" 
        :page-size="pageSize" 
        layout="total, prev, pager, next" 
        :total="total"
        background>
        </el-pagination>
    </div>
</template>
<script>
export default {
    props: ["total", "pageSize", "currentPage"],
    data() {
        return {
        }
    },
    methods: {
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            this.$emit('handleCurrentChange', val);
        }
    },
}
</script>
<style scope>
.block {
    margin-top: 20px;
}
</style>