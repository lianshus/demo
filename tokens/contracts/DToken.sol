// SPDX-License-Identifier: MIT

pragma solidity ^0.8.4;

import "@openzeppelin/contracts/token/ERC20/IERC20.sol";

contract DCToken is IERC20 {

    // 代币数据
    string public name;
    string public symbol;
    uint8 public decimals = 18;

    uint256 public override totalSupply ;

    address public owner;
    
    // 余额
    mapping(address=>uint256) public override balanceOf;
    // 授权额度
    mapping(address=>mapping(address=>uint256)) public override allowance;


    constructor(string memory _name,string memory _symbol) {
        name = _name;
        symbol = _symbol;
        owner = msg.sender;
    }
    
    modifier onlyOwner{
        require (msg.sender == owner,"not owner of the erc20");
        _;
    }

    // 直接操作自己代币转账
    function transfer(address to,uint256 amount) external override returns(bool){
        balanceOf[msg.sender] -= amount;
        balanceOf[to] += amount;

        emit Transfer(msg.sender,to,amount);
        return true;
    }

    // 授权操作代币
    function approve(address spender,uint256 amount) external override returns(bool) {
        allowance[msg.sender][spender] = amount;
        emit Approval(msg.sender,spender,amount);
        return true;
    }

    function transferFrom(address sender,address recipient,uint256 amount) external override returns(bool){
       
        require(allowance[sender][msg.sender]>=amount,"allowance is not enough");
        
        allowance[sender][msg.sender] -= amount;
        balanceOf[sender] -= amount;
        balanceOf[recipient] += amount;

        emit Transfer(sender,recipient,amount);
        return true;
    }

    function mint(uint256 amount) external onlyOwner {
        balanceOf[msg.sender] += amount;
        totalSupply += amount;

        emit Transfer(address(0),msg.sender,amount);
    }

     function burn(uint256 amount) external onlyOwner {
        balanceOf[msg.sender] -= amount;
        totalSupply -= amount;
        emit Transfer(msg.sender, address(0), amount);
    }
}
