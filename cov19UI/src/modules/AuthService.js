import api from "./Api"
import { API_URL_AUTH } from "./API_URL"

export const loginBasic = async (credentials) => {

    try{
        console.log("auth url", API_URL_AUTH);
        const response = await api.post(API_URL_AUTH + "/api/login", credentials);
        return {success: true, data: response.data};
    } catch (error) {
        console.log("An error occured while log in", error);
        return {
            success: false,
            status: error.response.status || 500,
            errorMsg: error.response?.data || "An unexpected error occured"
        }
    }
}

export const SignupBasic = async (credentials) => {

    try {
        const response = await api.post(API_URL_AUTH + "/api/signup", credentials);
        return {success: true, data: response.data};
    } catch (error) {
        console.log("An error occured while sing up", error);
        return {
            success: false,
            status: error.response?.status || 500,
            errorMsg: error.response?.data || "An unexpected error occured"
        }
    }
}

export const SignupAdminBasic = async (credentials) => {

    try {
        const response = await api.post(API_URL_AUTH + "/api/signupAdmin", credentials);
        return {success: true, data: response.data}
    } catch (error) {
        console.log("An error occured while signing up admin", error);
        return {
            success: false,
            status: error.response?.status || 500,
            errorMsg: error.response?.data || "An unexpected error occured"
        }
    }
}

export const loginOauth = () => {
    window.location.href = "http://localhost:8084/oauth2/authorization/google";
};


// export const loginOauth = async () => {

//     try {
//         const response = await api.get(API_URL + "/oauth2/authorization/google");
//         return {success: true, data: response.data};
//     } catch (error) {
//         console.log("An error occured while Oauth log in", error);
//         return {
//             success: false,
//             status: error.response.status || 500,
//             errorMsg: error.response?.data || "An unexpected error occured"
//         }
//     }
// }
