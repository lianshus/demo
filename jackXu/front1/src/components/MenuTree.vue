<template>
    <el-submenu v-if="menu.children && menu.children.length >= 1" :index="menu.id + ''">
        <template slot="title">
            <div class="my-menu-item">
                <i :class="menu.icon"></i>
                <span slot="title">{{ menu.name }}</span>
            </div>
        </template>
        <MenuTree v-for="item in menu.children" :key="item.id" :menu="item"></MenuTree>
    </el-submenu>
    <el-menu-item v-else :index="menu.path">
        <div class="my-menu-item">
            <i :class="menu.icon"></i>
            <span slot="title">{{ menu.name }}</span>
        </div>

    </el-menu-item>
</template>

<script>
export default {
    name: 'MenuTree',
    props: {
        menu: {
            type: Object,
            required: true
        }
    },
    methods: {
        handleRoute(menu) {
            // 通过菜单URL跳转至指定路由
            this.$router.push(menu.path)
        }
    }
}
</script>
<style lang="less" scoped>
.el-menu-item {

    height: 40px;
    line-height: 40px;
}

.my-menu-item {
    text-align: left;
    margin: 8px 0px 8px 0px;
    padding-left: 32px;
}
.my-menu-item:hover{
    background-color:rgba(19, 241, 149, 0.05);
    border-radius: 8px;
}

.el-submenu /deep/ .el-submenu__title {
    height: 40px;
    line-height: 40px;
}

.el-menu-item.is-active .my-menu-item {
    background: rgba(13, 162, 100,0.5) !important;
    border-radius: 8px!important;
}

.el-menu-item.is-active span,
.el-menu-item.is-active i {
    color:rgb(19, 241, 149) !important;
}

.sideStyle {
    padding-left: 0px !important;
    border: none;
}

.el-menu-item span,
.el-submenu /deep/ .el-submenu__title {
    color:rgba(209, 213, 219, 0.7);
    font-size: 15px;
}
.el-menu-item:focus, .el-menu-item:hover {
    background-color: #10141f !important;
}

.el-menu-item span {}

.el-dropdown-menu__item,
.el-menu-item {
    padding: 0px;
}

</style>