export default function Footers() {
    return (
        <>
            <div className="w-full h-[383px] flex justify-center items-center bg-[#000000]">
                {/* left */}
                <div className="w-1/2 h-full flex justify-center items-center">
                    <div className="flex-col">
                        <div className="text-gray-50">
                            <p>Managed by Solana Foundation</p>
                        </div>
                        <div className="text-gray-50 flex justify-between mt-10">
                            <p>Youtube</p>
                            <p>Twitter</p>
                            <p>Github</p>
                        </div>
                        <div className="text-gray-50 mt-10">
                            <p>@ 2023 Solana Foundation.All rights reserved.</p>
                        </div>
                    </div>
                </div>

                {/* right */}
                <div className="w-1/2 h-full flex justify-center items-center">
                    <div className="w-1/2 h-[221px] flex-col">
                        <div className="text-[#FFFFFF] text-2xl">
                            <p>SOLANA</p>
                        </div>
                        <div className="text-gray-50 text-lg mt-5">
                            <p>Grants</p>
                        </div>
                        <div className="text-gray-50 text-lg mt-5">
                            <p>Break Solana</p>
                        </div>
                        <div className="text-gray-50 text-lg mt-5">
                            <p>Media Kit</p>
                        </div>
                        <div className="text-gray-50 text-lg mt-5">
                            <p>Carees</p>
                        </div>
                        <div className="text-gray-50 text-lg mt-5">
                            <p>Disclaimer</p>
                        </div>

                    </div>
                    <div className="w-1/2 h-[221px] flex-col">
                        <div className="text-[#FFFFFF] text-2xl">
                            <p>GET CONNECTED</p>
                        </div>
                        <div className="text-gray-50 text-lg mt-5">
                            <p>Ecosystem</p>
                        </div>
                        <div className="text-gray-50 text-lg mt-5">
                            <p>Blog</p>
                        </div>
                        <div className="text-gray-50 text-lg mt-5">
                            <p>Newsletter</p>
                        </div>

                    </div>
                </div>
            </div>
        </>
    )
}