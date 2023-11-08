import Image from "next/image"
import SmallCard from "./SmallCard"

export default function Intro() {

    const cardData = [
        {
            color: '#1FCFF1',
            title: 'Fast',
            content: 'Dont keep your users waiting.Solana has block times of 400 milliseconds - and as hardware gets faster,so will the network',
            data: '3969',
            desc: 'TRANSACTIONS PER SECOND'
        },
        {
            color: '#9945FF',
            title: 'Scalable',
            content: 'Get big,quick.Solana is made to handle thousands of transactions per second, and fees for both develops and users remain less than $0.01.',
            data: '163,077,581,394',
            desc: 'TOTAL TRANSACTIONS'
        },
        {
            color: '#FFD512',
            title: 'Decentralized',
            content: 'The Solana network is validated by thousands of nodes that operate independently of each other,ensuring your data remains secure and censorship resistant.',
            data: '1675',
            desc: 'VALIDATOR NODES'
        },
        {
            color: '#19FB9B',
            title: 'Energy Efficient ',
            content: "Solana's proof of stake network and other innovations minimize its impact on the environment.Each Solana trasaction uses about the same energy as a few Google searches",
            data: '0%',
            desc: 'NET CARBON IMPACT'
        },
    ]

    return (
        <>
            <div className=" w-full  h-[1690px] bg-[url('/image-introbg.png')] bg-center bg-no-repeat bg-cover">
                <div className="z-50 flex items-center justify-center mt-[125px]">
                    <div className="w-4/5 flex items-center justify-center ml-[300px]">
                        <div className="w-1/2 text-gray-50 text-4xl ml-40">
                            <p>Join a community of millions</p>
                        </div>
                        <div className="w-1/2 text-gray-50 ml-80">
                            <div className="-mt-20">
                                <p className="w-60 bg-gradient-to-l from-[#19FB9B] to-[#8C01FA] bg-clip-text text-transparent text-6xl">
                                    11.5M+
                                </p>
                                <p className="text-gray-50 mt-5">
                                    ACTIVE ACCOUNTS
                                </p>
                            </div>
                            <div className="mt-20">
                                <p className="w-30 bg-gradient-to-l from-[#0047FF] to-[#00BCD4] bg-clip-text text-transparent text-6xl">
                                    21.9M
                                </p>
                                <p className="text-gray-50 mt-5">
                                    NFTS MINTED
                                </p>
                            </div>
                            <div className="mt-20">
                                <p className="w-72 bg-gradient-to-l from-[#00FFBD] to-[#025B8C] bg-clip-text text-transparent text-6xl">
                                    $0.00025
                                </p>
                                <p className="text-gray-50 mt-5">
                                    AVERAGE COST PER TRANSACTION
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="flex items-center justify-center mt-60">
                    <div className="w-4/5 flex items-center justify-center ml-[300px]">
                        <div className="w-1/3 text-gray-50 text-4xl ml-40">
                            <p>Made for mass adoption.</p>
                        </div>
                        <div className="w-1/3 ml-40">
                            <div className="">
                                <SmallCard color={cardData[0].color} title={cardData[0].title} content={cardData[0].content} data={cardData[0].data} desc={cardData[0].desc} />
                            </div>
                            <div className="mt-20">
                                <SmallCard color={cardData[1].color} title={cardData[1].title} content={cardData[1].content} data={cardData[1].data} desc={cardData[1].desc} />
                            </div>
                        </div>
                        <div className="w-1/3 text-gray-50 ml-10">
                            <div className="-mt-20">
                                <SmallCard color={cardData[2].color} title={cardData[2].title} content={cardData[2].content} data={cardData[2].data} desc={cardData[2].desc} />
                            </div>
                            <div className="mt-20">
                                <SmallCard color={cardData[3].color} title={cardData[3].title} content={cardData[3].content} data={cardData[3].data} desc={cardData[3].desc} />
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="w-full h-[1726px] bg-[#000000]">
                <div className="w-full h-[93px] flex items-center justify-center">
                    <div className="mt-[125px] w-[1140px] h-[562.19px]">
                        <div className="flex justify-between w-[1100px] h-[48.19px]">
                            {/* title */}
                            <div className="text-gray-50 w-[299px] h-[47px] text-4xl">
                                <p>Build for growth</p>
                            </div>

                            {/* buttons */}
                            <div className="flex justify-between">
                                <button className="text-gray-50 w-[64.18px] h-[48px] text-xl border-2 border-[#A962FF] rounded-xl
                                p-2 mr-2 bg-[#1A1A1A]">
                                    NFTs
                                </button>
                                <button className="text-gray-50 w-[64.18px] h-[48px] text-xl border-2 border-[#000000]
                                rounded-xl p-2 mr-2 bg-[#1A1A1A]">
                                    DeFi
                                </button>
                                <button className="text-gray-50 w-[98.28px] h-[48px] text-xl border-2 border-[#000000]  rounded-xl 
                                p-2 mr-2 bg-[#1A1A1A]">
                                    Payments
                                </button>
                                <button className="text-gray-50 w-[83.24px] h-[48px] text-xl border-2 border-[#000000]
                                rounded-xl p-2 bg-[#1A1A1A]">
                                    Gaming
                                </button>
                                <button className="text-gray-50 w-[71.2px] h-[48px] text-xl border-2 border-[#000000]
                                rounded-xl p-2 bg-[#1A1A1A]">
                                    DAOs
                                </button>

                            </div>

                        </div>
                    </div>
                </div>
                <div className="w-full h-[400px] flex items-center justify-center -mt-[160px] -ml-[20px]">
                    <div className='w-[553px] h-full'>
                        <Image
                            src="/image-man.png"
                            className=""
                            width={570}
                            height={400}
                            priority
                        />
                    </div>
                    <div className='w-[555px] h-full bg-[#1A1A1A] flex-col justify-center items-center'>
                        <div className="w-full h-2/3 flex items-end p-5">
                            <p className="text-gray-50">
                                It's time to  bridge the digital and physical.
                                Anybodies helps estabished brands like Toys'R'Us connect real-life places and products with NFTs.
                            </p>
                        </div>
                        <p className="text-[#A962FF]  p-5">Learn more about NFTson Solana.</p>
                    </div>
                </div>

                <div className="w-full h-[62px]  flex items-center justify-center mt-10">
                    <Image
                        src="/image-icons.png"
                        className=""
                        width={1200}
                        height={400}
                        priority
                    />
                </div>

                <div className="w-full flex-col items-center justify-center h-[1200px]  bg-[url('/image-introbg.png')] bg-center bg-no-repeat bg-cover">
                    <div className="w-1/2 h-[48px] ml-[600px] text-gray-50 text-4xl mt-20">
                        Join a thriving communtity.
                    </div>

                    <div className="w-full h-[422px]">
                        {/* picts */}
                    </div>

                    <div className="w-full flex items-center justify-center">
                        <p className="w-[827.27px] text-center text-gray-50 text-3xl">
                            It's time to join the thousands of creators,artists,and developers using Solana.
                        </p>

                    </div>
                    <div className="w-full flex items-center justify-center mt-10">

                        <button className='w-36 h-12  bg-gradient-to-r from-[#8C01FA] to-[#1E1E1E] text-sm text-gray-50 rounded-3xl mr-2'>
                            START BUILDING
                        </button>
                    </div>
                </div>

            </div>




        </>
    )
}