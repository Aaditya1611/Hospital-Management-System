import api from "./Api";
import { API_URL_COVID } from "./API_URL";

export const fetchAllUsaData = async(page=0, size=20) => {

    try {
        const response = await api.get(API_URL_COVID + "/usa/all", {
            params: {
                page: page,
                size: size
            }
        });
        return {success: true, data: response.data}
    } catch (error) {
        console.log("An error occured while fetching the data")
        return {
            success: false,
            status: error.response.status || 500,
            errorMsg: error.response?.data || "An unexpected error occured"
        }
    }
};