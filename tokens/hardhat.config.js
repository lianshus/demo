require("@nomicfoundation/hardhat-toolbox");

/** @type import('hardhat/config').HardhatUserConfig */
module.exports = {
  solidity: "0.8.18",
  paths: {
  //编译后生成json的路径
    artifacts: './src/artifacts',
  },
  networks: {
  //本地网络用这个
     hardhat: {
       chainId: 1337
     }
  }
}
