const {defineConfig}=require("@vue/cli-service")
module.exports=defineConfig({
    lintOnSave:false,
    devServer: {
        client: {
          overlay: false
        }
      }
})