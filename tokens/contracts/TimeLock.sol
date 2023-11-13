pragma solidity ^0.8.0;

contract TimeLock {
    // 取消交易
    event CancelTransaction(
        bytes32 indexed txHash,
        address indexed target,
        uint value,
        string signature,
        bytes data,
        uint executeTime
    );

    // 交易执行事件
    event ExecuteTransaction(
        bytes32 indexed txHash,
        address indexed target,
        uint value,
        string signature,
        bytes data,
        uint executeTime
    );
    // 交易创建并进入队列 事件
    event QueueTransaction(
        bytes32 indexed txHash,
        address indexed target,
        uint value,
        string signature,
        bytes data,
        uint executeTime
    );

    // 修改管理员地址
    event NewAdmin(address indexed NewAdmin);

    address public admin; //管理员账户

    uint public delay; //锁定期

    uint256 public constant GRACE_PERIOD = 7 days; //交易有效期，过期作废

    mapping(bytes32 => bool) queuedTransaction; //时间锁中的交易

    modifier onlyOwner() {
        require(msg.sender == admin, "Timelock:Caller not admin");
        _;
    }
    modifier onlyTimelock() {
        require(msg.sender == address(this), "Timelock:Caller not Timelock");
        _;
    }

    constructor(uint delay_) {
        delay = delay_;
        admin = msg.sender;
    }

    // 更换管理员
    function changeAdmin(address newAdmin) public onlyTimelock {
        admin = newAdmin;
        emit NewAdmin(newAdmin);
    }

    // 创建交易并进入时间锁队列
    function queueTransaction(
        address target,
        uint256 value,
        string memory signature,
        bytes memory data,
        uint256 executeTime
    ) public onlyOwner returns (bytes32) {
        // 交易执行时间满足锁定时间
        require(
            executeTime >= getBlockTimestamp() + delay,
            "Timelock:QUEUETransaction: Estimated execution block must satisfy delay."
        );

        // 交易的唯一识别符
        bytes32 txHash = getTxHash(target, value, signature, data, executeTime);

        // 交易添加到队列
        queuedTransaction[txHash] = true;

        emit QueueTransaction(
            txHash,
            target,
            value,
            signature,
            data,
            executeTime
        );

        return txHash;
    }

    // 交易执行
    // 交易在时间锁队列中，达到执行时间，交易没过期
    function executeTransaction(
        address target,
        uint256 value,
        string memory signature,
        bytes memory data,
        uint256 executeTime
    ) public payable onlyOwner returns (bytes memory) {
        bytes32 txHash = getTxHash(target, value, signature, data, executeTime);

        require(
            queuedTransaction[txHash],
            "Timelock::executeTransaction: Transaction hasn't been queued."
        );
        require(
            getBlockTimestamp() >= executeTime,
            "Timelock::executeTransaction: Transaction hasn't surpassed time lock."
        );
        require(
            getBlockTimestamp() <= executeTime + GRACE_PERIOD,
            "Timelock::executeTransaction: Transaction hasn't surpassed time lock."
        );

        queuedTransaction[txHash] = false;

        bytes memory callData;
        if (bytes(signature).length == 0) {
            callData = data;
        } else {
            callData = abi.encodePacked(
                bytes4(keccak256(bytes(signature))),
                data
            );
        }

        //call的时候也可以发送data数据
        (bool success, bytes memory returnData) = target.call{value: value}(callData);

        require(
            success,
            "Timelock::executeTransaction: Transaction execution reverted."
        );

        emit ExecuteTransaction(
            txHash,
            target,
            value,
            signature,
            data,
            executeTime
        );

        return returnData;
    }

    // 交易取消
    function cancelTransaction(
        address target,
        uint256 value,
        string memory signature,
        bytes memory data,
        uint256 executeTime
    ) public onlyOwner {
        // 计算交易的唯一识别符：一堆东西的hash
        bytes32 txHash = getTxHash(target, value, signature, data, executeTime);
        // 检查：交易在时间锁队列中
        require(
            queuedTransaction[txHash],
            "Timelock::cancelTransaction: Transaction hasn't been queued."
        );
        // 将交易移出队列
        queuedTransaction[txHash] = false;

        emit CancelTransaction(
            txHash,
            target,
            value,
            signature,
            data,
            executeTime
        );
    }

    function getBlockTimestamp() public view returns (uint) {
        return block.timestamp;
    }

    function getTxHash(
        address target,
        uint value,
        string memory signature,
        bytes memory data,
        uint executeTime
    ) public pure returns (bytes32) {
        return keccak256(abi.encode(target,value,signature,data,executeTime));
    }
}
