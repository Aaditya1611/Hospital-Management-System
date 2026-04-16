import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { FcGoogle } from "react-icons/fc";
import { loginBasic, loginOauth } from "../modules/AuthService";
import loginBg from "../assets/login_page_bg.png";

const Login = () => {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        username: '',
        password: ''
    })

    const handleChange = (e) => {
        const { id, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [id]: value
        }))
    }

    const handleLoginBasic = async (e) => {

        e.preventDefault()
        if (formData.username.trim() === "" || formData.password.trim() === "") {
            alert("Please input your username or password to continue")
            return;
        }
        const response = await loginBasic(formData)
        if (response.success) {
            alert("login successful")
            const data = response.data;
            console.log("login data", data)
            localStorage.setItem("token", data?.token)
            setFormData({
                username: "",
                password: ""
            })
            navigate("/covid")
        } else {
            alert("login failed", response.status)
            console.warn("Login failed with status", response.status);
        }
    }

    const handleLoginOauth = async (e) => {
        loginOauth();
    }

    return (
        <div className="h-screen flex flex-col bg-cover bg-center bg-no-repeat p-4"
            style={{ backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url(${loginBg})` }}>
            <div className="flex justify-between">
                <div className="flex justify-between px-6 py-4 items-center w-full">
                    <Link to="/" className="text-2xl text-gray-700 font-semibold">CloudHealth.co</Link>
                    <Link to={"/signup"} className="text-sm text-white underline">Create an account</Link>
                </div>
            </div>
            <div className="h-screen flex justify-center items-center text-white">
                <div className="bg-linear-to-b from-blue-200 via-gray-400 to-gray-100 mb-40 backdrop-blur-3xl p-10 rounded-2xl w-fit">
                    <div className="flex flex-col gap-y-2 items-center ">
                        <p className="text-4xl text-white">Welcome Back</p>
                        <p className="text-sm text-white">Enter your unique details</p>
                        {/* </div> */}
                        <form className="flex flex-col gap-y-3 items-center mt-4">
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
                        </form>
                        <button
                            onClick={handleLoginBasic}
                            className="w-[25rem] h-[3rem] rounded-full border-none bg-blue-500 text-lg text-white hover:bg-blue-100 hover:text-black duration-400 cursor-pointer">
                            Log In
                        </button>
                        <div className="flex flex-row gap-x-2 items-center">
                            <span className="w-[10rem] h-[2px] bg-neutral-400"></span>
                            <p className="text-neutral-400 text-sm">or</p>
                            <span className="w-[10rem] h-[2px] bg-neutral-400"></span>
                        </div>
                        <button
                            onClick={handleLoginOauth}
                            className="w-[25rem] h-[3rem] rounded-full border-none bg-linear-to-r from-blue-200 to-blue-400 text-lg text-gray-600 hover:text-white duration-400 cursor-pointer flex flex-row items-center justify-center gap-x-2">
                            <FcGoogle size={20} />
                            Login with google
                        </button>
                    </div>

                </div>
            </div>
        </div>
    )

}

export default Login;