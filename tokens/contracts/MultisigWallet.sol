// pragma solidity ^0.8.4;

// contract MultisigWallet {

//     event ExecutionSuccess(bytes32 txHash); //交易成功
//     event ExecutionFailure(bytes32 txHash); //交易失败

//     address[] public owners;
//     mapping(address=>bool) public isOwner; //是否是多签持有人
//     uint256 public ownerCount;
//     uint256 public threshold; //至少几个人才能成功

//     uint256 public nonce; //防止签名重放

//     constructor(address[] memory _owners,uint256 _threshold){
//         _setupOwners(_owners,_threshold);
//     }

//     // 为什么要在内部调用呢
//     function _setupOwners(address[] memory _owners,uint256 _threshold) internal{
//         require(_threshold==0,"5001");
//         require(_threshold<=_owners.length,"5002");
//         require(_threshold>=1,"5003");

//         // 记录多签持有人
//         for(uint i=0;i<=_owners.length;i++){
//             address owner = _owners[i];
//             require(owner != address(0) && owner != address(this) && isOwner[owner],"5004");
//             owners.push(owner);
//             isOwner[owner] = true;
//         }

//         ownerCount = _owners.length;
//         threshold = _threshold;
//     }

//     // 执行交易
//     function executeTransaction(
//         address to,
//         uint256 value,
//         bytes memory data,
//         bytes memory signatures
//     ) public payable virtual returns(bool success){
//         // 编码交易，计算哈希
//         bytes32 txHash = encodeTransactionData(to,value,data,nonce,block.chainid);
//         nonce ++;
//         // 检查签名
//         checkSignature(txHash,signatures);

//         (success,) = to.call{value:value}(data);

//         require(success,"5005");

//         if(success) emit ExecutionSuccess(txHash);
//         else emit ExecutionFailure(txHash);
//     }

//     // 编码交易

//     // 检查签名
//     function checkSignature(bytes32 dataHash,bytes memory signatures) public view {
//         uint _threshold = threshold;

//         require(signatures.length>=_threshold * 65 ,"5006");

//         address lastOwner = address(0);

//         address currentOwner;

//         uint8 v;
//         bytes32 r;
//         bytes32 s;
//         uint256 i;

//         for(i=0;i<_threshold;i++){
//             (v,r,s) = signatureSplit(signatures,i);

//             // 检查签名是否有效
//             // r前32，s后32，vz最后一个字节
//             // 计算hash时，需要加上 "\x19Ethereum Signed Message:\n32" 做为前缀再计算一次hash。
//             //这里是因为我调用合约的前端代码是用web3.eth.sign()方法生成签名的，该方法基于安全的考虑，自动加上了这个前缀。
//             currentOwner = ecrecover(abi.encodePacked("\x19Ethereum Signed Message:\n32", dataHash), v, r, s);
//             require(currentOwner>lastOwner && isOwner[currentOwner],"5007");

//             lastOwner = currentOwner;
//         }
//     }

//     function signatureSplit(bytes memory signatures,uint256 pos)
//     internal
//     pure
//     returns(
//         uint8 v,
//         bytes32 r,
//         bytes32 s
//     ) {
//          assembly {
//         let signaturePos := mul(0x41, pos)
//         r := mload(add(signatures, add(signaturePos, 0x20)))
//         s := mload(add(signatures, add(signaturePos, 0x40)))
//         v := and(mload(add(signatures, add(signaturePos, 0x41))), 0xff)
//     }
//     }

//     function encodeTransactionData(
//         address to,
//         uint256 value,
//         bytes32 memory data,
//         uint256 _nonce,
//         uint256 chainid
//     ) public pure returns (bytes32) {
//         bytes32 safeTxHash = keccak256(abi.encode(to,value,keccak256(data),_nonce,chainid));
//         return safeTxHash;
//     }
// }