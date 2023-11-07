import Image from 'next/image'
import Tools from './Tools'
import Intro from './Intro'
export default function Main() {
    return (
        <>
            <div className='bg-[#000000]'>
                <div className='flex justify-start'>
                    <div className='w-1/3'>
                        <Image
                            src="/image-leftbg.png"
                            className="-mt-20 relative z-10"
                            width={800}
                            height={400}
                            priority
                        />
                    </div>

                    <div className='w-1/3 flex-col content-center justify-center py-60'>
                        <div className='text-gray-50 text-6xl text-center'>
                            <p>
                                Powerful for developers.
                            </p>
                            <p >Fast for everyone.</p>
                        </div>
                        <div className='text-gray-50 text-xl text-center mt-20'>
                            <p>
                                Bring blockchain to the people.Solana supports experiences for power users,
                                new consumers,and everyone in between.
                            </p>
                        </div>
                        <div className='flex items-center justify-center mt-5'>
                            <button className='w-36 h-12  bg-gradient-to-r from-[#8C01FA] to-[#1E1E1E] text-sm text-gray-50 rounded-3xl mr-2'>
                                START BUILDING
                            </button>
                            <button className='w-36 h-12 border text-sm text-gray-50 rounded-3xl ml-2'>
                                READ DOCS
                            </button>
                        </div>
                        <div className='mt-48'>
                            <Tools></Tools>
                        </div>
                    </div>

                    <div className='w-1/3'>
                        <Image
                            src="/image-rightbg.png"
                            className="-mt-20 relative z-10"
                            width={800}
                            height={400}
                            priority
                        />
                    </div>
                </div>
                <div className='w-full -mt-80 relative z-50'>
                    <Intro></Intro>
                </div>


            </div>

        </>
    )
}