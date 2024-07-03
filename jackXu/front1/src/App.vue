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
  background-color: #10141f !important;
  color: #fff !important;
  border-radius: 3% !important;
  border: none !important;

}

.el-button {
  background-color: rgb(19, 241, 149) !important;
  border-color: rgb(13, 162, 100) !important;
}

.el-radio.is-bordered.is-checked {
  border-color: rgb(13, 162, 100) !important;
}

.el-form {
  .el-form-item__label {
    color: #fff;
  }

  .el-radio__input.is-checked+.el-radio__label {
    color: aqua;
  }
}

.el-input__inner {
  background-color: #10141f !important;
  border-color: #1b2135 !important;
  ;
}

.el-picker-panel {
  background-color: #10141f !important;
  border-color: #1b2135 !important;

  .current span {
    background-color: rgb(13, 162, 100) !important;
  }

  .today span {
    color: rgb(13, 162, 100) !important;
  }

  .el-date-table td.available:hover {
    color: rgb(13, 162, 100) !important;
  }

  .el-picker-panel__icon-btn:hover {
    color: rgb(13, 162, 100) !important;
  }
}

.el-button {
  color: #fff !important;
}

.el-button:hover {
  background-color: rgb(23, 204, 128) !important;
}

.el-menu {
  background-color: #10141f !important;
}

.el-submenu__title {
  background-color: #10141f !important;
}

.el-dialog {
  text-align: left;
  background-color: #10141f !important;

}

.el-dialog__title,
.el-dialog__body {
  color: #fff !important;
}

.el-input.is-disabled .el-input__inner {
  color: rgb(96, 98, 102) !important;
}

.el-input__inner {
  color: rgb(192, 196, 204) !important;
}

.el-message-box,.el-popover {
  background-color: #10141f !important;
  border-color: #1b2135 !important;
 
}
.el-message-box__title,.el-message-box__message{
  color: #fff !important;
}
</style>
