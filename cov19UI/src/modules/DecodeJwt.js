import { jwtDecode } from "jwt-decode";

export const getRolesFromToken = () => {
    const token = localStorage.getItem("token");

    if (!token) {
        return [];
    }

    try {
        const decoded = jwtDecode(token);
        
        const currentTime = Date.now() / 1000;
        if (decoded.exp < currentTime) {
            console.warn("Token has expired");
            //localStorage.removeItem("token");
            return [];
        }

        if (decoded.roles) {
            return decoded.roles;
        }

        return decoded.roles || [];
    } catch (error) {
        console.error("Invalid token format", error);
        return [];
    }
};