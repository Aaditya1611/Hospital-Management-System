import React, { useState } from 'react';
import { RequireRole } from '../RequireRole';
import {
    addCases,
    addDeaths,
    addRecovered,
    reduceActive,
    deleteCountryData,
    searchByCountryName,
    searchByRegionName
} from '../../modules/CountryDataService';

const CountryData = ({ data, setData, userRoles, refreshData }) => {
    const [countryInput, setCountryInput] = useState("");
    const [regionInput, setRegionInput] = useState("");

    const handleSearchCountry = async (e) => {
        if (e) e.preventDefault();
        if (!countryInput.trim()) return;

        const response = await searchByCountryName(countryInput);
        if (response.success) {
            const results = Array.isArray(response.data) ? response.data : [response.data];
            setData(results);
        } else {
            alert("Country not found");
        }
    };

    const handleSearchRegion = async (e) => {
        if (e) e.preventDefault();
        if (!regionInput.trim()) return;

        const response = await searchByRegionName(regionInput);
        if (response.success) {
            setData(response.data.content || response.data);
        } else {
            alert("No data found for this region");
        }
    };

    const handleClear = () => {
        setCountryInput("");
        setRegionInput("");
        refreshData();
    };

    const handleUpdate = async (id, type, countryName) => {
        const input = window.prompt(`Enter amount to update ${type} for ${countryName}:`);
        if (!input || isNaN(input) || input.trim() === "") return;

        const count = parseInt(input);
        let response;

        try {
            switch (type) {
                case 'cases': response = await addCases(id, count); break;
                case 'deaths': response = await addDeaths(id, count); break;
                case 'recovered': response = await addRecovered(id, count); break;
                case 'reduceActive': response = await reduceActive(id, count); break;
                default: return;
            }

            if (response && response.success) {
                refreshData();
            } else {
                alert(`Update failed: ${response.errorMsg}`);
            }
        } catch (error) {
            console.error("Update operation failed", error);
        }
    };

    const handleDelete = async (countryName) => {
        if (window.confirm(`Permanently delete all data for ${countryName}?`)) {
            const response = await deleteCountryData(countryName);
            if (response && response.success) {
                refreshData();
            } else {
                alert(`Delete failed: ${response.errorMsg}`);
            }
        }
    };

    return (
        <div className="flex flex-col gap-6">

            {/* --- SEARCH SECTION --- */}
            <div className="grid grid-cols-1 lg:grid-cols-2 gap-4 bg-gray-800/50 p-4 rounded-xl border border-gray-700">
                <form onSubmit={handleSearchCountry} className="flex gap-2">
                    <input
                        type="text"
                        placeholder="Search Country Name..."
                        className="bg-gray-900 border border-gray-700 text-sm rounded-lg p-2.5 flex-1 outline-none focus:ring-2 focus:ring-blue-500"
                        value={countryInput}
                        onChange={(e) => setCountryInput(e.target.value)}
                    />
                    <button type="submit" className="bg-blue-600 hover:bg-blue-700 px-4 py-2 rounded-lg text-sm font-semibold transition">
                        Find
                    </button>
                </form>

                <div className="flex gap-2">
                    <form onSubmit={handleSearchRegion} className="flex flex-1 gap-2">
                        <input
                            type="text"
                            placeholder="Search WHO Region..."
                            className="bg-gray-900 border border-gray-700 text-sm rounded-lg p-2.5 flex-1 outline-none focus:ring-2 focus:ring-green-500"
                            value={regionInput}
                            onChange={(e) => setRegionInput(e.target.value)}
                        />
                        <button type="submit" className="bg-green-600 hover:bg-green-700 px-4 py-2 rounded-lg text-sm font-semibold transition">
                            Filter
                        </button>
                    </form>
                    <button
                        onClick={handleClear}
                        className="bg-gray-700 hover:bg-gray-600 px-3 py-2 rounded-lg text-xs font-medium border border-gray-600 transition"
                    >
                        Reset
                    </button>
                </div>
            </div>

            {/* --- TABLE SECTION --- */}
            <div className="overflow-x-auto">
                <table className="w-full text-left border-collapse">
                    <thead>
                        <tr className="border-b border-gray-700 text-gray-400 text-xs uppercase tracking-wider">
                            <th className="pb-4 pr-4">Country</th>
                            <th className="pb-4 pr-4 text-center">Confirmed</th>
                            <th className="pb-4 pr-4 text-center">Active</th>
                            <th className="pb-4 pr-4 text-center">Deaths</th>
                            <th className="pb-4 pr-4 text-center">Recovered</th>
                            <th className="pb-4 pr-4 text-center">Weekly Incr</th>

                            <RequireRole allowedRoles={['ROLE_ADMIN']} userRoles={userRoles}>
                                <th className="pb-4 pl-4 text-center text-blue-400">Admin Actions</th>
                            </RequireRole>
                        </tr>
                    </thead>
                    <tbody className="divide-y divide-gray-800">
                        {data && data.length > 0 ? (
                            data.map((country) => (
                                <tr key={country.id} className="hover:bg-gray-800/40 transition-colors group">
                                    <td className="py-4 pr-4 font-bold text-gray-200">{country.countryName}</td>
                                    <td className="py-4 pr-4 text-center text-yellow-500 tabular-nums">
                                        {country.confirmedCases?.toLocaleString()}
                                    </td>
                                    <td className="py-4 pr-4 text-center tabular-nums">
                                        {country.activeCases?.toLocaleString()}
                                    </td>
                                    <td className="py-4 pr-4 text-center text-red-500 tabular-nums">
                                        {country.deaths?.toLocaleString()}
                                    </td>
                                    <td className="py-4 pr-4 text-center text-green-500 tabular-nums">
                                        {country.recovered?.toLocaleString()}
                                    </td>
                                    <td className="py-4 pr-4 text-center font-medium text-blue-300">
                                        {country.weeklyPercentIncrease}%
                                    </td>

                                    <RequireRole allowedRoles={['ROLE_ADMIN']} userRoles={userRoles}>
                                        <td className="py-4 pl-4">
                                            <div className="flex flex-wrap gap-2 justify-center min-w-[180px]">
                                                <button
                                                    onClick={() => handleUpdate(country.id, 'cases', country.countryName)}
                                                    className="bg-blue-600/10 text-blue-400 border border-blue-600/30 hover:bg-blue-600 hover:text-white px-2 py-1 rounded text-[10px] transition uppercase font-bold"
                                                >+ Case</button>
                                                <button
                                                    onClick={() => handleUpdate(country.id, 'deaths', country.countryName)}
                                                    className="bg-red-600/10 text-red-400 border border-red-600/30 hover:bg-red-600 hover:text-white px-2 py-1 rounded text-[10px] transition uppercase font-bold"
                                                >+ Death</button>
                                                <button
                                                    onClick={() => handleUpdate(country.id, 'recovered', country.countryName)}
                                                    className="bg-green-600/10 text-green-400 border border-green-600/30 hover:bg-green-600 hover:text-white px-2 py-1 rounded text-[10px] transition uppercase font-bold"
                                                >+ Recov</button>
                                                <button
                                                    onClick={() => handleUpdate(country.id, 'reduceActive', country.countryName)}
                                                    className="bg-green-600/10 text-green-400 border border-green-600/30 hover:bg-green-600 hover:text-white px-2 py-1 rounded text-[10px] transition uppercase font-bold"
                                                >- Active</button>
                                                <button
                                                    onClick={() => handleDelete(country.countryName)}
                                                    className="bg-red-900/20 text-red-500 border border-red-900/50 hover:bg-red-700 hover:text-white px-2 py-1 rounded text-[10px] transition uppercase font-bold w-full"
                                                >Delete</button>
                                            </div>
                                        </td>
                                    </RequireRole>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="8" className="py-20 text-center text-gray-500 italic">
                                    No data available. Use search or check connection.
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default CountryData;