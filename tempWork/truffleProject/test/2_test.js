// test/SimpleAuthTest.js

const SimpleAuth = artifacts.require("SimpleAuth");

contract("SimpleAuth", accounts => {
    let simpleAuth;

    before(async () => {
        simpleAuth = await SimpleAuth.deployed();
    });

    // 注册，测试通过
    it("should allow a user to register", async () => {
        const username = "小明";
        const password = "123456";
        // 0是商家，1是用户
        const usertype = "1";
        const phone = "1234567890";
        const email = "123@qq.com";
        const place = "北京";

        await simpleAuth.register(username, web3.utils.keccak256(password), usertype, phone, email, place, { from: accounts[0] });

        const user = await simpleAuth.users(accounts[0]);
        assert.equal(user.username, username, "error");
    });

    // 正常登录，测试通过
    it("should allow a user to login", async () => {
        const username = "小明";
        const password = "123456";
        const result = await simpleAuth.login(username, web3.utils.keccak256(password), { from: accounts[0] });
        assert.isTrue(result.logs[0].args.success, "error");
    });

    // 密码错误，测试不通过
    it("should not allow login with incorrect password", async () => {
        const username = "小明";
        const password = "654321";
        try {
            const result = await simpleAuth.login(username, web3.utils.keccak256(password), { from: accounts[0] });
            assert.isTrue(result.logs[0].args.success, "error");
        } catch (error) {
            assert.include(error.message, "revert", "error");
        }
    });

});
