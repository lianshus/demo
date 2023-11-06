import Image from 'next/image'

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24 bg-black">
     {/* header */}
     <div className='flex w-full justify-between text-gray-50'>
        {/* logo */}
        <div className='flex w-1/3 justify-center text-gray-50'>
          <div className='flex w-1/3 justify-between'>
            <p className='text-3xl text-gray-50'>Serendale</p>
          </div>
        </div>

        {/* navs */}
        <div className='flex w-1/3 justify-between text-gray-50'>
          <div>SmartContracts</div>
          <div>Service</div>
          <div>Solutions</div>
          <div>Roadmadiv</div>
          <div>Whitedivadiver</div>
        </div>

        {/* icons */}
        <div className='flex w-1/3 justify-center text-gray-50'>
          <div className='flex w-1/3 justify-between'>
            <div>github</div>
            <div>twitter</div>
          </div>

        </div>

      </div>

      {/* main */}
      <div className='flex w-1/2 flex-col items-center z-10'>
        {/* title */}
        <div>
          <p className='bg-clip-text text-6xl bg-title text-transparent'>A Fast BlockChain.</p>
        </div>

        {/* subtitle */}
        <div className='text-gray-50 text-xl'>
          Our technology performing fast blockchainc(120k TPS) and it has guaranteed AI-based data security.
        </div>

        {/* 按钮 */}
        <div className='flex w-80 h-12 justify-between mt-10'>
          <button className='w-40 button-border text-gray-50 mr-5 p-2'>
            Get Start
          </button>


          <button className='w-40 border border-2 bord borderer-gray-50 text-gray-50 ml-5'>Ecosystems</button>
        </div>

      </div>
      {/* pict-footer */}

      <div className='z-0 -mt-80'>
        <Image
          className="w-full -mb-24"
          src="/home.png"
          alt="Next.js Logo"
          width={1800}
          height={500}
          priority
        />
      </div>


    </main>
  )
}
