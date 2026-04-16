import { useState, useEffect } from "react";
import Navbar from "../components/navbar";
import { fetchAllCountryData } from "../modules/CountryDataService";
import { fetchAllCovCompData } from "../modules/CovidCompleteService";
import CountryData from "../components/covidcomponents/CountryData";
import CovidComplete from "../components/covidcomponents/CovidComplete";
import DayWise from "../components/covidcomponents/DayWise";
import FullGrouped from "../components/covidcomponents/FullGrouped";
import UsaCountry from "../components/covidcomponents/UsaCountry";
import WorldoMeter from "../components/covidcomponents/WorldoMeter";
import { fetchAllDaywiseData } from "../modules/DayWiseService";
import { fetchAllGroupedData } from "../modules/FullGroupedService";
import { fetchAllUsaData } from "../modules/UsaCountryService";
import { fetchAllWorldData } from "../modules/WorldoMeterService";
import HighlightCard from "../components/covidcomponents/HighlightCard";
import { getRolesFromToken } from "../modules/DecodeJwt";

const CovidDashboard = () => {
    const [data, setData] = useState([]);
    const [page, setPage] = useState(0);
    const [view, setView] = useState("countrywise");
    const [userRoles, setUserRoles] = useState([]);

    useEffect(() => {
        const roles = getRolesFromToken();
        setUserRoles(roles);
    }, []);

    const loadData = async () => {
        try {
            let response;
            switch (view) {
                case "countrywise": response = await fetchAllCountryData(page, 10); break;
                case "daywise": response = await fetchAllDaywiseData(page, 10); break;
                case "fullgrouped": response = await fetchAllGroupedData(page, 10); break;
                case "usacountry": response = await fetchAllUsaData(page, 10); break;
                case "worldometer": response = await fetchAllWorldData(page, 10); break;
                case "covidcomplete": response = await fetchAllCovCompData(page, 10); break;
                default: response = "Connection to the server failed";
            }
            if (response && response.success) {
                setData(response.data.content || response.data);
            } else {
                setData([]);
            }
        } catch (error) {
            alert("failed to execute operation due to the following error", error.status)
            console.error("Error fetching data:", error);
            setData([]);
        }
    };

    useEffect(() => {
        loadData();
    }, [page, view]);

    const TableView = {
        countrywise: <CountryData data={data} setData={setData} userRoles={userRoles} refreshData={loadData} />,
        daywise: <DayWise data={data} userRoles={userRoles} refreshData={loadData} />,
        fullgrouped: <FullGrouped data={data} userRoles={userRoles} refreshData={loadData} />,
        usacountry: <UsaCountry data={data} userRoles={userRoles} refreshData={loadData} />,
        worldometer: <WorldoMeter data={data} userRoles={userRoles} refreshData={loadData} />,
        covidcomplete: <CovidComplete data={data} userRoles={userRoles} refreshData={loadData} />,
    };

    const safestCountries = view === "countrywise"
        ? [...data].sort((a, b) => a.confirmedCases - b.confirmedCases).slice(0, 5)
        : [];

    const worstHitCountries = view === "countrywise"
        ? [...data].sort((a, b) => b.confirmedCases - a.confirmedCases).slice(0, 5)
        : [];

    return (
        <div>
            <section className="p-4 flex flex-col gap-y-4 h-screen">
                <Navbar />
                <div className="max-h-screen bg-gray-900 text-white p-8 rounded-2xl overflow-y-auto">
                    <header className="mb-4 flex items-center justify-between">
                        <h1 className="text-2xl font-bold text-blue-500">Real-time COVID-19 Tracking</h1>
                        <select
                            value={view}
                            onChange={(e) => { setView(e.target.value); setPage(0); }}
                            className="bg-gray-800 border border-gray-700 text-white rounded-lg p-2.5 outline-none"
                        >
                            <option value="countrywise">Country Wise Data</option>
                            <option value="daywise">Day Wise Data</option>
                            <option value="fullgrouped">Full Grouped Data</option>
                            <option value="usacountry">United States Data</option>
                            <option value="worldometer">World Data</option>
                            <option value="covidcomplete">Covid Complete Data</option>
                        </select>
                    </header>

                    <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                        <div className="md:col-span-2 bg-gray-800 rounded-xl p-6 shadow-lg">

                            {TableView[view]}

                            {/* Pagination (Keep as is) */}
                            <div className="flex justify-between mt-6">
                                <button onClick={() => setPage(p => Math.max(0, p - 1))} disabled={page === 0} className="px-4 py-2 bg-blue-600 rounded">Previous</button>
                                <span className="self-center">Page {page + 1}</span>
                                <button onClick={() => setPage(p => p + 1)} className="px-4 py-2 bg-blue-600 rounded">Next</button>
                            </div>
                        </div>

                        {/* Highlight Card (Keep as is) */}
                        <HighlightCard
                            view={view}
                            safest={safestCountries}
                            worst={worstHitCountries}
                            data={data}
                        />
                    </div>
                </div>
            </section>
        </div>
    );
};

export default CovidDashboard;