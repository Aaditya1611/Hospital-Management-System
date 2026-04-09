import Navbar from "../components/navbar";
import mainBgImg2 from "../assets/main_bg.png";
import sideImg1 from "../assets/side_image2.png";
import sideImg4 from "../assets/side_image4.png";
import sideImg3 from "../assets/side_image3.png";
import { motion } from "framer-motion";
import Experties from "../data/expertiesSection";
import { Notebook } from "lucide-react";
import { UserLock } from "lucide-react";
import Footer from "../components/footer";
import { Link } from "react-router-dom";

const HomePage = () => {

    return (
        <div>
            <section className="h-screen flex flex-col bg-cover bg-center bg-no-repeat p-4"
                style={{ backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url(${mainBgImg2})` }}>

                <Navbar />
                <div className="mt-auto mb-15 mx-auto w-full max-w-8xl px-4 sm:px-8 flex flex-col md:flex-row justify-between items-end gap-y-8">
                    <div className="flex flex-col gap-y-6">
                        <div className="flex flex-col leading-tight">
                            <h1 className="text-5xl md:text-7xl font-bold text-white tracking-tight">
                                The Global Standard
                            </h1>
                            <h2 className="text-4xl md:text-6xl text-gray-300">
                                in Medical Support
                            </h2>
                        </div>
                        <div className="flex flex-col">
                            <p className="text-lg text-white">We connect dedicated doctors with premier institutes worldwide through transparent,</p>
                            <p className="text-lg text-white">compliant and supportive recruitment method</p>
                        </div>
                    </div>
                    <div className="flex flex-col items-start gap-y-4">
                        <div className="flex flex-row items-center gap-x-2">
                            <div className="flex flex-row -space-x-4">
                                <span className="w-14 h-14 bg-gray-500 rounded-full border-2 border-gray-900 shadow-sm"></span>
                                <span className="w-14 h-14 bg-gray-400 rounded-full border-2 border-gray-900 shadow-sm"></span>
                                <span className="w-14 h-14 bg-gray-300 rounded-full border-2 border-gray-900 shadow-sm"></span>
                                <span className="w-14 h-14 bg-gray-100 rounded-full border-2 border-gray-900 shadow-sm"></span>
                            </div>
                            <div className="flex flex-col">
                                <h1 className="text-2xl text-white">100+</h1>
                                <p className="text-lg text-white">International Healthcare Service Providers</p>
                            </div>
                        </div>
                        <Link  to="/login" className="bg-white text-black cursor-pointer px-5 py-3 rounded-lg hover:bg-black hover:text-white duration-300">Book An Appointment</Link>
                    </div>
                </div>
            </section>
            <section className="flex flex-col lg:flex-row bg-gray-300 lg:px-32 lg:py-20 p-8 gap-12 lg:gap-20 items-center">
                <div className="flex flex-col gap-y-8 w-full lg:w-1/2">
                    <div className="flex flex-row items-center gap-x-3">
                        <span className="w-4 h-4 rounded-full bg-accent shadow-lg shadow-accent/50"></span>
                        <h2 className="text-2xl font-semibold text-accent">
                            Why Us?
                        </h2>
                    </div>

                    <div className="flex flex-col gap-y-2">
                        <h1 className="text-4xl md:text-5xl font-semibold text-gray-700 leading-tight">
                            A trusted partner for Medical Professionals
                        </h1>
                    </div>

                    <div>
                        <p className="text-lg text-gray-700 max-w-xl">
                            We empower physicians to transcend borders. Our rigorous recruitment process ensures your skills are matched with prestigious medical centers that value excellence and compliance.
                        </p>
                    </div>

                    <div className="flex flex-col sm:flex-row justify-between gap-8 mt-4">
                        <div className="flex flex-col gap-y-3 w-full sm:w-1/2">
                            <h1 className="text-5xl text-gray-800 font-bold">
                                100+
                            </h1>
                            <h2 className="text-xl font-semibold text-gray-900">Professional Doctors</h2>
                            <p className="text-md text-gray-700">
                                We have professional doctors from various medical backgrounds through a structured registration and compliance-focused recruitment process.
                            </p>
                        </div >
                        <div className="flex flex-col gap-y-3 w-full sm:w-1/2">
                            <h1 className="text-5xl text-gray-800 font-bold">
                                10+
                            </h1>
                            <h2 className="text-xl font-semibold text-gray-900">Serving in countries</h2>
                            <p className="text-md text-gray-700">
                                Our process supports doctors across a wide range of medical disciplines based on current demand and professional qualification standards.
                            </p>
                        </div>
                    </div>
                </div>

                <div className="w-full lg:w-1/2">
                    <img
                        className="w-full h-96 lg:h-[650px] object-cover rounded-2xl shadow-2xl"
                        src={sideImg1}
                        alt="Medical Professionals"
                    />
                </div>
            </section>
            <section className="flex lg:flex-col w-full bg-gray-300 lg:px-32 p-8 lg:gap-y-10 justify-center items-center">
                <div className="flex flex-row items-center w-full justify-center">
                    <h1 className="text-2xl text-gray-800 bg-gray-100 text-center items-center p-4 rounded-xl">Experties & Discipline</h1>
                </div>
                <div className="flex flex-col justify-center items-center gap-y-5">
                    <h2 className="text-5xl">RecruitMent Across</h2>
                    <h3 className="text-4xl">Key Medical Fields</h3>
                    <div className="flex flex-col justify-center items-center">
                        <p className="text-lg text-wrap">We specialize in matching talent with opportunity. Medhire understands the distinct</p>
                        <p className="text-lg">requirements of each medical discipline to ensuren precise and rewarding placements globally</p>
                    </div>
                </div>
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6 p-6">
                    {Experties.map((content) => (
                        <motion.div
                            key={content.id}
                            // className="bg-blue-50 border border-blue-100 rounded-xl p-6 shadow-sm"
                            whileHover={{
                                scale: 1.05,
                                boxShadow: "0px 15px 30px rgba(59, 130, 246, 0.3)"
                            }}
                            transition={{
                                type: "spring",
                                stiffness: 300,
                                damping: 20
                            }}
                            className="bg-white border border-gray-100 rounded-2xl p-6 flex flex-col gap-y-3 cursor-pointer"

                        >
                            <h2 className="text-xl font-semibold text-blue-900">
                                {content.title}
                            </h2>
                            <p className="text-blue-700 mt-2 text-sm">
                                {content.description}
                            </p>
                        </motion.div>
                    ))}

                </div>
            </section>
            <section className="flex flex-col w-full bg-gray-300 lg:px-32 p-8 lg:gap-y-12 justify-center items-center">
                <div className="flex flex-row items-center w-full justify-center">
                    <h1 className="text-2xl text-gray-800 bg-gray-100 text-center items-center p-4 rounded-xl">The Process</h1>
                </div>
                <div className="flex flex-col justify-center items-center gap-y-5">
                    <h2 className="text-5xl">A Seamless Path to</h2>
                    <h3 className="text-5xl">Global Practice</h3>
                    <div className="flex flex-col justify-center items-center">
                        <p className="text-lg text-wrap">We simplify the complexities of International recruitment, guiding you</p>
                        <p className="text-lg">From profile creation to your first day on duty.</p>
                    </div>
                </div>
                <div className="flex flex-row items-center justify-center gap-x-2">
                    <div className="flex flex-col gap-y-4">
                        <h1 className="text-2xl">1. Secure Profile Creation</h1>
                        <p className="text-wrap text-lg text-gray-500">Submit your Professional profile through our encrypted portal, specifically desgined to highlight your medical expertiese to leading gloabal employees</p>
                    </div>
                    <div className="flex flex-col gap-y-4">
                        <h1 className="text-2xl">2. Secure Profile Creation</h1>
                        <p className="text-wrap text-lg text-gray-500">Submit your Professional profile through our encrypted portal, specifically desgined to highlight your medical expertiese to leading gloabal employees</p>
                    </div>
                    <div className="flex flex-col gap-y-4">
                        <h1 className="text-2xl">3. Secure Profile Creation</h1>
                        <p className="text-wrap text-lg text-gray-500">Submit your Professional profile through our encrypted portal, specifically desgined to highlight your medical expertiese to leading gloabal employees</p>
                    </div>
                </div>
            </section>
            <section className="flex flex-col lg:flex-row bg-gray-300 lg:px-32 lg:py-20 p-8 gap-12 lg:gap-20 items-center">
                <div className="w-full lg:w-1/2">
                    <img
                        className="w-full h-96 lg:h-[650px] object-cover rounded-2xl shadow-2xl"
                        src={sideImg3}
                        alt="Medical Professionals"
                    />
                </div>
                <div className="flex flex-col gap-y-8 w-full lg:w-1/2">
                    <div className="flex flex-row items-center gap-x-3">
                        <span className="w-4 h-4 rounded-full bg-accent shadow-lg shadow-accent/50"></span>
                        <h2 className="text-2xl font-semibold text-accent">
                            Compliance & Quality
                        </h2>
                    </div>

                    <div className="flex flex-col gap-y-2">
                        <h1 className="text-4xl md:text-5xl font-semibold text-gray-700 leading-tight">
                            Built on Global Medical Standards
                        </h1>
                    </div>

                    <div>
                        <p className="text-lg text-gray-700 max-w-xl">
                            We empower physicians to transcend borders. Our rigorous recruitment process ensures your skills are matched with prestigious medical centers that value excellence and compliance.
                        </p>
                    </div>

                    <div className="flex flex-col sm:flex-row justify-between gap-8 mt-4">
                        <div className="flex flex-col gap-y-3 w-full sm:w-1/2">
                            <Notebook className="bg-gray-700 text-white size-16 rounded-2xl" />
                            <h2 className="text-2xl text-gray-900">Global Regulatory Compliance</h2>
                            <p className="text-md text-gray-700">
                                We have professional doctors from various medical backgrounds through a structured registration and compliance-focused recruitment process.
                            </p>
                        </div >
                        <div className="flex flex-col gap-y-3 w-full sm:w-1/2">
                            <UserLock className="bg-gray-700 text-white size-16 rounded-2xl" />
                            <h2 className="text-2xl text-gray-900">Data Security & Privacy</h2>
                            <p className="text-md text-gray-700">
                                Our process supports doctors across a wide range of medical disciplines based on current demand and professional qualification standards.
                            </p>
                        </div>
                    </div>
                </div>
            </section>
            <section className="flex flex-col w-full bg-gray-300 lg:px-32 p-30 lg:gap-y-15 justify-center items-center">
                <div className="flex flex-col items-center w-full justify-center gap-y-8">
                    <h1 className="text-2xl text-gray-800 bg-gray-100 text-center items-center p-4 rounded-xl">Our AI</h1>
                    <p className="text-5xl text-black text-center items-center text-wrap">Our smart AI can identify cases with 75% accuracy</p>
                    <p className="text-5xl text-black text-center items-center text-wrap">Get free healthcare advise from our own specialized AI model</p>
                </div>
                <div className="flex flex-row">
                    <div className="flex flex-col justify-between py-6 px-4 w-full lg:w-1/2">
                        <h2 className="text-2xl font-semibold text-gray-600 bg-white w-fit p-2 rounded-xl">
                            Get Started
                        </h2>
                        <div className="flex flex-col gap-y-4">
                            <h1 className="text-4xl text-gray-700">
                                Chat with our AI
                            </h1>
                            <p className="text-lg text-gray-500">Just tell our AI your symptoms and it will identify the your diseases based on them, it offers you a personlized treatment for you.</p>
                            <button className="text-lg px-4 py-2 bg-accent text-white rounded-xl w-fit cursor-pointer hover:bg-blue-100 hover:text-accent duration-300">Chat with AI</button>
                        </div>
                        <div className="flex flex-row items-center w-full my-6">
                            <span className="flex-grow bg-gray-400 h-0.5"></span>
                            <span className="px-4 text-gray-400 font-medium uppercase tracking-widest text-sm">Or</span>
                            <span className="flex-grow bg-gray-400 h-0.5"></span>
                        </div>
                        <div className="flex flex-col gap-y-4">
                            <h1 className="text-4xl text-gray-700">
                                Consult a real doctor
                            </h1>
                            <p className="text-lg text-gray-500">Hundreds of medical Professionals are avialable for you 24/7. Book your appointment right now and get the best medical experience you can at home</p>
                            <button className="text-lg px-4 py-2 bg-accent text-white rounded-xl w-fit cursor-pointer hover:bg-blue-100 hover:text-accent duration-300">Book Appointment</button>
                        </div>
                    </div>
                    <div className="w-full lg:w-1/2">
                        <img
                            className="w-full h-96 lg:h-[650px] object-cover rounded-2xl shadow-2xl"
                            src={sideImg4}
                            alt="Medical Professionals"
                        />
                    </div>
                </div>
            </section>
            <Footer />
        </div>
    )
}

export default HomePage;