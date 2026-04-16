import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
    const navigate = useNavigate();
    const isAuthenticated = !!localStorage.getItem("token");

    const handleLogout = () => {
        localStorage.removeItem("token");
        navigate("/login");
    };

    return (
        <div className="bg-transparent w-full">
            <div className="max-w-400 mx-auto px-4 sm:px-6 lg:px-8 p-4 bg-white rounded-xl scale-z-100">
                <div className="flex justify-between items-center">
                    <div>
                        <Link to={"/"} className="font-bold text-xl">CloudHealth.co</Link>
                    </div>
                    <div className="hidden md:flex gap-x-6 items-center">
                        {isAuthenticated && (
                            <>
                                 <Link to={"/login"} className="text-gray-600 hover:text-gray-900 cursor-pointer text-lg font-medium">AI Doctor</Link>
                                <Link to={"/helpdesk"} className="text-gray-600 hover:text-gray-900 cursor-pointer text-lg font-medium">HelpDesk</Link>
                                <Link to={"/covid"} className="text-gray-600 hover:text-gray-900 cursor-pointer text-lg font-medium">Covid19</Link>
                            </>
                        )}
                    </div>
                    <div>
                        {isAuthenticated ? (
                            <button
                                onClick={handleLogout}
                                className="px-6 py-2 text-white bg-red-500 rounded-lg text-lg hover:bg-red-600 transition-colors duration-300 cursor-pointer">
                                Logout
                            </button>
                        ) : (
                            <Link
                                to="/signup"
                                className="px-6 py-2 text-white bg-gray-600 rounded-lg text-lg hover:bg-accent transition-colors duration-300 cursor-pointer">
                                Sign up
                            </Link>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Navbar;