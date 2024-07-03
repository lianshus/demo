const SimpleAuth = artifacts.require("SimpleAuth");

module.exports = function(deployer) {
    deployer.deploy(SimpleAuth);
};