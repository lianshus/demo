import {ethers} from "ethers";
const provider = ethers.getDefaultProvider();

const main = async()=>{
    const balance = await provider.getBalance(`vitalik.eth`);
    console.log(balance)
}

main();