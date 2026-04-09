import { Link } from "react-router-dom";
import { Pointer } from 'lucide-react';
import { useState } from "react";
import { ArrowRight } from "lucide-react";
import { SignupBasic } from "../modules/AuthService";
import loginBg from "../assets/login_page_bg.png";

const Signup = () => {

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    const [formData, setFormData] = useState({
        email: '',
        username: '',
        password: '',
    })

    const handleChange = (e) => {
        const { id, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [id]: value
        }))
    }

    const handleSignup = async (e) => {

        e.preventDefault();
        if (formData.email.trim() === "" || formData.username.trim() === "" || formData.password.trim() === "") {
            alert("Please input your username or password to continue")
            return;
        } else if (!emailRegex.test(formData.email)) {
            alert("Please enter a valid email")
            return;
        }
        const response = await SignupBasic(formData);
        if (response.success) {
            const data = response.data;
            alert("Signup successful")
            localStorage.setItem("token", data?.token);
        } else {
            alert("Signup failed", response.status)
            console.warn("signup failed", response.status)
        }
    }

    return (
        <div className="h-screen flex flex-col bg-cover bg-center bg-no-repeat p-4"
            style={{ backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url(${loginBg})` }}>
            <div className="flex justify-between">
                <div className="flex justify-between px-6 py-4 items-center w-full">
                    <Link to={"/"} className="text-2xl text-gray-700 font-semibold">CloudHealth.co</Link>
                    <Link to={"/login"} className="text-sm text-white underline flex flex-row items-center justify-center">
                        <span>Go back to login</span>
                        <Pointer className="-scale-x-100 -rotate-90 text-blue-400" size={18} />
                    </Link>
                </div>
            </div>
            <div className="h-screen flex justify-center items-center text-white">
                <div className="bg-linear-to-b from-blue-200 via-gray-400 to-gray-100 mb-40 backdrop-blur-3xl p-10 rounded-2xl w-fit flex flex-col gap-y-5">
                    <h1 className="text-center text-white">Enter your Unique details</h1>
                    <form className="flex flex-col gap-y-4 items-center mt-4">
                        <input
                            className="w-[25rem] h-[3rem] rounded-full border-none focus:outline-none bg-blue-100 text-gray-600 p-5 focus-within:bg-blue-50 duration-300"
                            type="email"
                            id="email"
                            placeholder="Enter your email"
                            value={formData.email}
                            onChange={handleChange}
                        />
                        <input
                            className="w-[25rem] h-[3rem] rounded-full border-none focus:outline-none bg-blue-100 text-gray-600 p-5 focus-within:bg-blue-50 duration-300"
                            type="text"
                            id="username"
                            placeholder="Enter your username"
                            value={formData.username}
                            onChange={handleChange}
                        />
                        <input
                            className="w-[25rem] h-[3rem] rounded-full border-none focus:outline-none bg-blue-100 text-gray-600 p-5 focus-within:bg-blue-50 duration-300"
                            type="password"
                            id="password"
                            placeholder="Enter your password"
                            value={formData.password}
                            onChange={handleChange}
                        />
                        <button
                            onClick={handleSignup}
                            className="w-[25rem] h-[3rem] rounded-full border-none bg-linear-to-r from-blue-200 to-blue-400 text-lg text-gray-600 hover:text-white duration-400 cursor-pointer flex flex-row items-center justify-center gap-x-2">
                            Sign Up
                            <ArrowRight className="text-gray-600" size={20} />
                        </button>
                    </form>
                    <div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Signup;