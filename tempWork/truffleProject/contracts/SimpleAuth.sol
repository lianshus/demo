// SPDX-License-Identifier: MIT
pragma solidity ^0.6.10;

contract SimpleAuth {
    struct User {
        string username;
        bytes32 passwordHash;
        string usertype;
        string phone;
        string email;
        string place;
    }

    mapping(address => User) public users;
    mapping(string => address) private usernameToAddress;

    event RegistrationSuccessful(address indexed user, string username);
    event LoginSuccessful(address indexed user, bool success);

    // 注册用户
    function register(
        string calldata username,
        string calldata password,
        string calldata usertype,
        string calldata phone,
        string calldata email,
        string calldata place
    ) external {
        require(
            usernameToAddress[username] == address(0),
            "Username already taken."
        );
        require(
            users[msg.sender].passwordHash == 0,
            "User already registered."
        );

        bytes32 passwordHash = keccak256(abi.encodePacked(password));
        users[msg.sender] = User(
            username,
            passwordHash,
            usertype,
            phone,
            email,
            place
        );
        usernameToAddress[username] = msg.sender;

        emit RegistrationSuccessful(msg.sender, username);
    }

    // 用户登录
    function login(
        string calldata username,
        string calldata password
    ) external returns (bool) {
        require(
            usernameToAddress[username] != address(0),
            "Username does not exist."
        );
        User memory user = users[usernameToAddress[username]];
        bool success = user.passwordHash ==
            keccak256(abi.encodePacked(password));
        emit LoginSuccessful(msg.sender, success);
        return success;
    }
}
