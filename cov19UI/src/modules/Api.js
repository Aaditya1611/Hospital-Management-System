import axios from "axios";
import { API_URL } from "./API_URL";
import { getRolesFromToken } from "./DecodeJwt";

const api = axios.create({
    baseURL: API_URL
});
api.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");

    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
        const roles = getRolesFromToken();
        if (roles.length > 0) {
            config.headers['X-Authenticated-Roles'] = roles.join(',');
        }
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

api.interceptors.response.use((response) => response, (error) => {

    if (error.response && (error.response.status === 403 || error.response.status === 401) && !error.config.url.includes("/auth")) {

        console.error(`Blocked by backend with status: ${error.response.status}. Logging out.`);
        localStorage.removeItem("token");
        window.location.href = "/login";
    }
    return Promise.reject(error);
});

export default api;