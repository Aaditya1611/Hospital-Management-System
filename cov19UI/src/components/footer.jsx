import { Copyright } from "lucide-react";
import { FaFacebook, FaInstagram, FaYoutube, FaLinkedin, FaXTwitter } from 'react-icons/fa6';

const Footer = () => {

    return (
        <div>
            <section className="flex flex-col p-25">
                <div className="flex flex-row justify-between gap-x-20">
                    <div className="flex flex-col gap-y-8 text-gray-300 max-w-1/2">
                        <h1 className="text-2xl font-semibold">CloudHealth.co</h1>
                        <p className="text-wrap">Lorem ipsum dolor sit amet consectetur adipisicing elit. Consectetur dolores, debitis voluptatibus cupiditate.</p>
                        <p className="text-wrap">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Fuga, ea! Doloribus consequatur officia illo quaerat, ex fugit quae eum, at, blanditiis et sint! Pariatur, at natus vel iste rem dolorem.</p>
                    </div>
                    <div className="flex flex-row gap-x-10">
                        <div className="flex flex-col gap-y-5">
                            <p className="text-lg text-white font-semibold cursor-pointer">Why Us?</p>
                            <p className="text-lg text-white font-semibold cursor-pointer">Specialisation</p>
                            <p className="text-lg text-white font-semibold cursor-pointer">How it works</p>
                            <p className="text-lg text-white font-semibold cursor-pointer">Testimonials</p>
                        </div>
                        <div className="flex flex-col gap-y-2">
                            <p className="text-sm text-white">Contact</p>
                            <p className="text-sm text-white">Global Headquaters</p>
                            <p className="text-sm text-white">+91 382902373</p>
                            <p className="text-sm text-white">CloudHealth@cloud.in</p>
                        </div>
                    </div>
                </div>
                <span className="bg-gray-300 w-full h-[1px] mt-15"></span>
                <div className="flex flex-row mt-5 items-center justify-between">
                    <div className="gap-x-2 flex flex-row items-center">
                        <Copyright className="size-5 text-gray-300" />
                        <h1 className="text-gray-300">2026 CloudHealth Global All rights reserved</h1>
                    </div>
                    <div className="flex flex-row gap-x-5">
                        <FaFacebook className="size-8 text-gray-200 cursor-pointer"/>
                    <FaInstagram className="size-8 text-gray-200 cursor-pointer"/>
                    <FaYoutube className="size-8 text-gray-200 cursor-pointer"/>
                    <FaLinkedin className="size-8 text-gray-200 cursor-pointer"/>
                    <FaXTwitter className="size-8 text-gray-200 cursor-pointer"/>
                    </div>
                </div>
            </section>
        </div>
    )
}

export default Footer;