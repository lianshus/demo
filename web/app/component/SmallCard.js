export default function SmallCard(props) {
    const { color, title, content, data, desc } = props
    return (
        <>
            <div className="text-gray-50">
                {/* main content */}
                <div>
                    <div className={`w-[276px] h-[33px] text-3xl border-l-4 border-[${color}] px-5`}>
                        {title}
                    </div>
                    <div className="mt-6 w-[267px] h-[164px]">
                        {content}
                    </div>

                </div>

                {/* data content */}
                <div className="mt-0">
                    {/* data */}
                    <div className="text-3xl  w-[199px] h-[28px]">
                        {data}
                    </div>

                    {/* desc */}
                    <div className="mt-6">
                        <p>{desc}</p>
                    </div>

                </div>
            </div>
        </>
    )
}