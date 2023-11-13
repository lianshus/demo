

const { expect } = require("chai");

describe("DCToken", function () {
  let dc
  let DC;
  let owner;
  let addr1;
  let addr2;
  let addr3;

  beforeEach(async function () {
    [owner, addr1, addr2, addr3] = await ethers.getSigners();
    // 部署代币合约
    const DCToken = await ethers.getContractFactory("DCToken");
    dc = await DCToken.connect(addr1)
    // console.log(DCToken)
    DC = await dc.deploy("MyToken","MT");
    // await DC.getDeployTransaction();
 
    // 获取部署合约的账户
  
    console.log(owner);
  });

  it("应该设置正确的初始参数", async function () {
    expect(await DC.name()).to.equal("MyToken");
    expect(await DC.decimals()).to.equal(18);
    expect(await DC.symbol()).to.equal("MT");
    expect(await DC.totalSupply()).to.equal(0);
  });

  it("应该正确转账", async function () {

    // 转账10个代币给addr1账户
    await DC.connect(addr1).mint(10);
    await DC.transfer(addr1.address, 10,{from:addr1});

    expect(await DC.balanceOf(addr1.address)).to.equal(10);
  });

  it("应该正确授权并转移代币", async function () {
    // 转账10个代币给addr1账户
    await DC.mint(10);

    // 授权addr2可以从owner账户转移10个代币
    await DC.approve(addr2.address, 10,{from:addr1});
    await DC.connect(addr2).transferFrom(addr1.address, addr3.address, 10);

    expect(await DC.balanceOf(addr3.address)).to.equal(10);
  });

  // 添加更多的测试用例...

});