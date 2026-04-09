import { Link } from "react-router-dom";

const Navbar = () => {

    return (
        <div className="bg-transparent w-full"> 
            
            <div className="max-w-400 mx-auto px-4 sm:px-6 lg:px-8 p-4 bg-white rounded-xl scale-z-100">
                <div className="flex justify-between items-center">
                    <div>
                        <h1 className="font-bold text-xl">CloudHealth.co</h1>
                    </div>
                    <div className="hidden md:flex gap-x-6 items-center">
                        <span className="text-gray-600 hover:text-gray-900 cursor-pointer text-sm font-medium">Why Us</span>
                        <span className="text-gray-600 hover:text-gray-900 cursor-pointer text-sm font-medium">Expertise</span>
                        <span className="text-gray-600 hover:text-gray-900 cursor-pointer text-sm font-medium">Process</span>
                        <span className="text-gray-600 hover:text-gray-900 cursor-pointer text-sm font-medium">Compliance & Quality</span>
                        <span className="text-gray-600 hover:text-gray-900 cursor-pointer text-sm font-medium">AI</span>
                    </div>
                    <Link 
                    to="/signup"
                    className="px-6 py-2 text-white bg-gray-600 rounded-lg text-lg hover:bg-accent transition-colors duration-300 cursor-pointer">
                        Sign up
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default Navbar;