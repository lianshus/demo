<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style>
html,
body,
#app,
.el-container {
  padding: 0px;
  margin: 0px;
  height: 100%;
  min-width: 1024px;
}

.el-card {
  color: #3b8efb !important;
  border-radius: 3% !important;
  border: none !important;

}

.el-button {
  background-color: #3a65f2 !important;
  border-color: #3a65f2 !important;
}
.el-button :hover{
  background-color: rgb(19, 241, 149) !important;
}

.el-pagination.is-background .btn-next.disabled, .el-pagination.is-background .btn-next:disabled, .el-pagination.is-background .btn-prev.disabled, .el-pagination.is-background .btn-prev:disabled, .el-pagination.is-background .el-pager li.disabled {
  color:#FFF !important;
}
.el-pagination.is-background .el-pager li:not(.disabled).active {
    background-color: #3A65FB(86, 149, 249) !important;
    color: #FFF;
}
.el-pagination.is-background .btn-next, .el-pagination.is-background .btn-prev, .el-pagination.is-background .el-pager li {
    margin: 0 5px;
    background-color: #3A65FB(55, 153, 234);
    color: #606266;
    min-width: 30px;
    border-radius: 2px;
}

.el-radio.is-bordered.is-checked {
  border-color: rgb(13, 162, 100) !important;
}

.el-form {
  background-color: white !important;
  .el-form-item__label {
    color: #10141f;
  }

  .el-radio__input.is-checked+.el-radio__label {
    color: aqua;
  }
}


.el-input__inner {
  background-color: transparent !important;
  border-color: transparent !important;
  height:70px !important;
}



.el-button {
  color: #fff !important;
}

.el-button:hover {
  background-color: rgb(23, 204, 128) !important;
  border-color: rgb(23, 204, 128) !important;
}

.el-menu {
  background-color: #3A65FB !important;
}

.el-submenu__title {
  background-color: #3A65FB !important;
}

.el-dialog {
  text-align: left;
}

.el-dialog__title,
.el-dialog__body {
  color: #fff !important;
}

.el-input.is-disabled .el-input__inner {
  color: rgb(96, 98, 102) !important;
}

.el-input__inner {
  color: rgb(149, 150, 151) !important;
}


.el-table{
  margin-bottom:20px;
  margin-top:30px;
}
</style>
