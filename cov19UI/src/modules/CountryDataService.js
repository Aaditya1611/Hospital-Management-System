import api from "./Api";
import { API_URL_COVID } from "./API_URL";

export const fetchAllCountryData = async(page=0, size=20) => {

    try {
        const response = await api.get(API_URL_COVID + "/countrydata/all", {
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

export const searchByCountryName = async (name) => {

    try {
        const response = await api.get(API_URL_COVID + `/countrydata/byname/${name}`);
        return  { success: true, data: response.data}
    } catch (error) {
        console.log("An error occured while searching for a country")
        return {
            success: false,
            status: error.response.status || 500,
            errorMsg: error.response?.data || "An unexpected error occured"
        }
    }
}

export const searchByRegionName = async (region) => {

    try {
        const response = await api.get(API_URL_COVID + `/countrydata/bywhoregion/${region}`);
        return  { success: true, data: response.data}
    } catch (error) {
        console.log("An error occured while searching for a country")
        return {
            success: false,
            status: error.response.status || 500,
            errorMsg: error.response?.data || "An unexpected error occured"
        }
    }
}

export const addCases = async (id, count) => {
    try {
        const response = await api.patch(API_URL_COVID + `/countrydata/addcase/${id}/cases`, null, {
            params: { count: count }
        });
        return { success: true, data: response.data };
    } catch (error) {
        console.error("An error occurred while adding cases", error);
        return {
            success: false,
            status: error.response?.status || 500,
            errorMsg: error.response?.data || "An unexpected error occurred"
        };
    }
};

export const addDeaths = async (id, count) => {
    try {
        const response = await api.patch(API_URL_COVID + `/countrydata/adddeaths/${id}/deaths`, null, {
            params: { count: count }
        });
        return { success: true, data: response.data };
    } catch (error) {
        console.error("An error occurred while adding deaths", error);
        return {
            success: false,
            status: error.response?.status || 500,
            errorMsg: error.response?.data || "An unexpected error occurred"
        };
    }
};

export const addRecovered = async (id, count) => {
    try {
        const response = await api.patch(API_URL_COVID + `/countrydata/addrecovered/${id}/recovered`, null, {
            params: { count: count }
        });
        return { success: true, data: response.data };
    } catch (error) {
        console.error("An error occurred while adding recovered cases", error);
        return {
            success: false,
            status: error.response?.status || 500,
            errorMsg: error.response?.data || "An unexpected error occurred"
        };
    }
};

export const reduceActive = async (id, count) => {
    try {
        const response = await api.patch(API_URL_COVID + `/countrydata/reduceactive/${id}/active`, null, {
            params: { count: count }
        });
        return { success: true, data: response.data };
    } catch (error) {
        console.error("An error occurred while reducing active cases", error);
        return {
            success: false,
            status: error.response?.status || 500,
            errorMsg: error.response?.data || "An unexpected error occurred"
        };
    }
};

export const deleteCountryData = async (name) => {
    try {
        const response = await api.delete(API_URL_COVID + `/countrydata/delete/${name}`);
        return { success: true, data: response.data };
    } catch (error) {
        console.error("An error occurred while deleting country data", error);
        return {
            success: false,
            status: error.response?.status || 500,
            errorMsg: error.response?.data || "An unexpected error occurred"
        };
    }
};