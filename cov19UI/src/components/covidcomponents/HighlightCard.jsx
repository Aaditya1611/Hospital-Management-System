const HighlightCard = ({ view, safest, worst, data }) => {

    if (view === "") {
        return (
            <div className="bg-gray-800 rounded-xl p-6 shadow-xl border border-gray-700 h-fit">
                <h2 className="text-xl font-semibold mb-2 text-blue-400">Global Insights</h2>
                <p className="text-sm text-gray-400 mb-6 border-b border-gray-700 pb-2">
                    Historical Record Analysis
                </p>
                <div className="text-center py-10">
                    <p className="text-gray-500 text-sm italic">
                        Switch view to see safety insights and risk rankings.
                    </p>
                </div>
            </div>
        );
    }


if (view === "countrywise") {
    return (
        <div className="bg-gray-800 rounded-xl p-6 shadow-xl border border-gray-700 h-fit">
            <h2 className="text-xl font-semibold mb-2 text-blue-400">Global Insights</h2>
            <p className="text-sm text-gray-400 mb-6 border-b border-gray-700 pb-2">
                Real-time Statistics (187 Countries)
            </p>

            {/* Safest Section */}
            <div className="space-y-3 mb-8">
                <p className="text-sm font-bold text-green-400 uppercase tracking-wider">
                    Safest Destinations
                </p>
                <div className="space-y-2">
                    {safest.map((c) => (
                        <div key={c.id} className="flex justify-between items-center p-2 bg-green-900/30 border border-green-800/50 rounded-lg">
                            <span className="text-sm font-medium">{c.countryName}</span>
                            <span className="text-xs font-bold text-green-300">
                                {c.confirmedCases?.toLocaleString()}
                            </span>
                        </div>
                    ))}
                </div>
            </div>

            {/* Risk Section */}
            <div className="space-y-3">
                <p className="text-sm font-bold text-red-400 uppercase tracking-wider">
                    High Risk Zones
                </p>
                <div className="space-y-2">
                    {worst.map((c) => (
                        <div key={c.id} className="flex justify-between items-center p-2 bg-red-900/30 border border-red-800/50 rounded-lg">
                            <span className="text-sm font-medium">{c.countryName}</span>
                            <span className="text-xs font-bold text-red-300">
                                {c.confirmedCases?.toLocaleString()}
                            </span>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

if (view === "daywise") {
        const latestEntry = data?.[0]; 
        
        const prevTotal = latestEntry?.confirmed - latestEntry?.newCases;
        const growthRate = prevTotal > 0 
            ? ((latestEntry.newCases / prevTotal) * 100).toFixed(2) 
            : 0;

        return (
            <div className="bg-gray-800 rounded-xl p-6 shadow-xl border border-gray-700 h-fit">
                <h2 className="text-xl font-semibold mb-2 text-blue-400">Day Trend Analysis</h2>
                <p className="text-sm text-gray-400 mb-6 border-b border-gray-700 pb-2">
                    Date: <span className="text-white">{latestEntry?.date}</span>
                </p>

                <div className="space-y-6">
                    {/* Growth Percentage Metric */}
                    <div>
                        <div className="flex justify-between mb-1">
                            <span className="text-sm font-medium text-blue-300">Daily Growth Rate</span>
                            <span className="text-sm font-bold text-blue-300">{growthRate}%</span>
                        </div>
                        <div className="w-full bg-gray-700 rounded-full h-2.5">
                            <div 
                                className="bg-blue-500 h-2.5 rounded-full transition-all duration-1000" 
                                style={{ width: `${Math.min(growthRate * 10, 100)}%` }}
                            ></div>
                        </div>
                    </div>

                    {/* Simple Stats Grid */}
                    <div className="grid grid-cols-2 gap-4">
                        <div className="p-3 bg-gray-900/50 rounded-lg border border-gray-700">
                            <p className="text-xs text-gray-400">New Cases</p>
                            <p className="text-lg font-bold text-yellow-500">+{latestEntry?.newCases?.toLocaleString()}</p>
                        </div>
                        <div className="p-3 bg-gray-900/50 rounded-lg border border-gray-700">
                            <p className="text-xs text-gray-400">New Deaths</p>
                            <p className="text-lg font-bold text-red-500">+{latestEntry?.newDeaths?.toLocaleString()}</p>
                        </div>
                    </div>

                    <p className="text-xs text-gray-500 italic">
                        *Growth rate calculated based on the ratio of new cases to previous total.
                    </p>
                </div>
            </div>
        );
    }

    if (view === "covidcomplete") {
        const entry = data?.[0];
        
        const recoveryRate = entry?.confirmed > 0 
            ? ((entry.recovered / entry.confirmed) * 100).toFixed(1) 
            : 0;

        return (
            <div className="bg-gray-800 rounded-xl p-6 shadow-xl border border-gray-700 h-fit">
                <h2 className="text-xl font-semibold mb-2 text-blue-400">Regional Health Status</h2>
                <div className="flex justify-between items-center mb-6 border-b border-gray-700 pb-2">
                    <p className="text-sm text-white font-bold">{entry?.country}</p>
                    <span className="text-[10px] bg-gray-700 px-2 py-1 rounded text-gray-400 font-mono">
                        {entry?.latitude}, {entry?.longitude}
                    </span>
                </div>

                <div className="space-y-6">
                    {/* Recovery Gauge Visual */}
                    <div>
                        <div className="flex justify-between mb-2">
                            <span className="text-xs font-semibold text-green-400">Recovery Rate</span>
                            <span className="text-xs font-bold text-green-400">{recoveryRate}%</span>
                        </div>
                        <div className="w-full bg-gray-700 rounded-full h-3">
                            <div 
                                className="bg-green-500 h-3 rounded-full transition-all duration-700 shadow-[0_0_10px_rgba(34,197,94,0.5)]" 
                                style={{ width: `${recoveryRate}%` }}
                            ></div>
                        </div>
                    </div>

                    {/* Active vs Recovered Split */}
                    <div className="flex gap-2 h-12">
                        <div 
                            className="bg-yellow-600/20 border-l-4 border-yellow-500 flex-1 p-2"
                            style={{ flexGrow: entry?.active || 1 }}
                        >
                            <p className="text-[10px] text-yellow-500 uppercase font-bold">Active</p>
                            <p className="text-sm font-bold">{entry?.active?.toLocaleString()}</p>
                        </div>
                        <div 
                            className="bg-green-600/20 border-l-4 border-green-500 flex-1 p-2"
                            style={{ flexGrow: entry?.recovered || 1 }}
                        >
                            <p className="text-[10px] text-green-500 uppercase font-bold">Recovered</p>
                            <p className="text-sm font-bold">{entry?.recovered?.toLocaleString()}</p>
                        </div>
                    </div>

                    {/* Metadata */}
                    <div className="pt-2">
                        <p className="text-[11px] text-gray-500 italic">
                            WHO Region: <span className="text-gray-300">{entry?.region || 'N/A'}</span>
                        </p>
                    </div>
                </div>
            </div>
        );
    }

    if (view === "fullgrouped") {
        const entry = data?.[0]; // Get the specific country record for that date
        
        // Calculate the ratio for the bar chart
        const totalDailyAction = (entry?.newCases || 0) + (entry?.newRecovered || 0);
        const casesWidth = totalDailyAction > 0 ? (entry.newCases / totalDailyAction) * 100 : 50;
        const recoveredWidth = totalDailyAction > 0 ? (entry.newRecovered / totalDailyAction) * 100 : 50;

        const netChange = (entry?.newCases || 0) - (entry?.newDeaths || 0) - (entry?.newRecovered || 0);

        return (
            <div className="bg-gray-800 rounded-xl p-6 shadow-xl border border-gray-700 h-fit">
                <h2 className="text-xl font-semibold mb-2 text-blue-400">Daily Delta Analysis</h2>
                <div className="flex justify-between items-center mb-6 border-b border-gray-700 pb-2">
                    <p className="text-sm text-white font-bold">{entry?.country}</p>
                    <span className="text-xs text-gray-400">{entry?.date}</span>
                </div>

                <div className="space-y-6">
                    {/* New Cases vs New Recovered Visual Bar */}
                    <div className="space-y-2">
                        <div className="flex justify-between text-[10px] uppercase font-bold tracking-wider">
                            <span className="text-yellow-500">New Cases</span>
                            <span className="text-green-500">New Recovered</span>
                        </div>
                        <div className="flex w-full h-4 rounded-full overflow-hidden bg-gray-700 shadow-inner">
                            <div 
                                className="bg-yellow-500 transition-all duration-700" 
                                style={{ width: `${casesWidth}%` }}
                            ></div>
                            <div 
                                className="bg-green-500 transition-all duration-700" 
                                style={{ width: `${recoveredWidth}%` }}
                            ></div>
                        </div>
                    </div>

                    {/* Net Impact Card */}
                    <div className={`p-4 rounded-lg border ${netChange > 0 ? 'bg-red-900/20 border-red-800' : 'bg-green-900/20 border-green-800'}`}>
                        <div className="flex items-center justify-between">
                            <div>
                                <p className="text-[10px] text-gray-400 uppercase">Net Active Change</p>
                                <p className={`text-xl font-bold ${netChange > 0 ? 'text-red-500' : 'text-green-500'}`}>
                                    {netChange > 0 ? `+${netChange}` : netChange}
                                </p>
                            </div>
                            <div className="text-right">
                                <p className="text-[10px] text-gray-400 uppercase">Total Active</p>
                                <p className="text-sm font-semibold">{entry?.active?.toLocaleString()}</p>
                            </div>
                        </div>
                    </div>

                    {/* Quick Stats list */}
                    <div className="space-y-2">
                        <div className="flex justify-between text-xs">
                            <span className="text-gray-500">Daily Deaths</span>
                            <span className="text-red-400 font-mono">+{entry?.newDeaths}</span>
                        </div>
                        <div className="flex justify-between text-xs">
                            <span className="text-gray-500">WHO Region</span>
                            <span className="text-gray-300">{entry?.region}</span>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    if (view === "usacountry") {
        const entry = data?.[0]; 
        
        const cfr = entry?.confirmed > 0 
            ? ((entry.deaths / entry.confirmed) * 100).toFixed(2) 
            : 0;
        const getRiskColor = (rate) => {
            if (rate > 5) return 'text-red-500';
            if (rate > 2) return 'text-orange-500';
            return 'text-green-500';
        };

        return (
            <div className="bg-gray-800 rounded-xl p-6 shadow-xl border border-gray-700 h-fit">
                <h2 className="text-xl font-semibold mb-2 text-blue-400">USA Local Analysis</h2>
                <div className="mb-6 border-b border-gray-700 pb-2">
                    <p className="text-sm text-white font-bold">{entry?.combinedKey}</p>
                    <p className="text-[10px] text-gray-500 uppercase tracking-widest">
                        FIPS: {entry?.fips || 'N/A'} | {entry?.isoThree}
                    </p>
                </div>

                <div className="space-y-6">
                    {/* Mortality Rate Visual */}
                    <div className="text-center p-4 bg-gray-900/50 rounded-xl border border-gray-700">
                        <p className="text-xs text-gray-400 uppercase mb-1">Case Fatality Rate</p>
                        <p className={`text-3xl font-black ${getRiskColor(cfr)}`}>
                            {cfr}%
                        </p>
                        <div className="w-full bg-gray-700 rounded-full h-1.5 mt-3">
                            <div 
                                className={`h-1.5 rounded-full transition-all duration-1000 ${cfr > 2 ? 'bg-red-500' : 'bg-green-500'}`} 
                                style={{ width: `${Math.min(cfr * 10, 100)}%` }}
                            ></div>
                        </div>
                    </div>

                    {/* Data Points Grid */}
                    <div className="grid grid-cols-2 gap-3">
                        <div className="flex flex-col">
                            <span className="text-[10px] text-gray-500 uppercase">Confirmed</span>
                            <span className="text-lg font-bold text-yellow-500">
                                {entry?.confirmed?.toLocaleString()}
                            </span>
                        </div>
                        <div className="flex flex-col">
                            <span className="text-[10px] text-gray-500 uppercase">Total Deaths</span>
                            <span className="text-lg font-bold text-red-500">
                                {entry?.deaths?.toLocaleString()}
                            </span>
                        </div>
                    </div>

                    {/* Location Badge */}
                    <div className="pt-2 flex items-center gap-2">
                        <div className="w-2 h-2 rounded-full bg-blue-500 animate-pulse"></div>
                        <p className="text-xs text-gray-400">
                            Region: <span className="text-gray-200">{entry?.province}</span>
                        </p>
                    </div>
                </div>
            </div>
        );
    }

    if (view === "worldometer") {
        const entry = data?.[0];
        
        const criticalRate = entry?.activeCases > 0 
            ? ((entry.criticalCases / entry.activeCases) * 100).toFixed(2) 
            : 0;

        return (
            <div className="bg-gray-800 rounded-xl p-6 shadow-xl border border-gray-700 h-fit">
                <h2 className="text-xl font-semibold mb-1 text-blue-400">Worldometer Stats</h2>
                <div className="mb-4 border-b border-gray-700 pb-2">
                    <p className="text-sm text-white font-bold">{entry?.country}</p>
                    <p className="text-[10px] text-gray-500 uppercase tracking-widest">
                        {entry?.continent} | {entry?.region}
                    </p>
                </div>

                <div className="space-y-6">
                    {/* Critical Care Gauge */}
                    <div className="p-4 bg-red-900/10 rounded-lg border border-red-900/30">
                        <div className="flex justify-between items-end mb-2">
                            <div>
                                <p className="text-[10px] text-red-400 uppercase font-bold">Critical Cases</p>
                                <p className="text-2xl font-black text-red-500">{entry?.criticalCases?.toLocaleString()}</p>
                            </div>
                            <div className="text-right">
                                <p className="text-[10px] text-gray-400 uppercase">of active</p>
                                <p className="text-sm font-bold text-gray-300">{criticalRate}%</p>
                            </div>
                        </div>
                        <div className="w-full bg-gray-700 rounded-full h-1.5">
                            <div 
                                className="bg-red-600 h-1.5 rounded-full shadow-[0_0_8px_rgba(220,38,38,0.6)]" 
                                style={{ width: `${Math.min(criticalRate * 5, 100)}%` }}
                            ></div>
                        </div>
                    </div>

                    {/* Testing vs Population Visual */}
                    <div className="space-y-3">
                        <p className="text-[10px] text-gray-400 uppercase font-bold tracking-widest">Testing Coverage</p>
                        <div className="grid grid-cols-2 gap-2 text-center">
                            <div className="p-2 bg-gray-900/50 rounded-md border border-gray-700">
                                <p className="text-[10px] text-gray-500">Total Tests</p>
                                <p className="text-xs font-bold">{entry?.totalTest?.toLocaleString() || 'N/A'}</p>
                            </div>
                            <div className="p-2 bg-gray-900/50 rounded-md border border-gray-700">
                                <p className="text-[10px] text-gray-500">Per Million</p>
                                <p className="text-xs font-bold text-blue-400">{entry?.testPerMil?.toLocaleString()}</p>
                            </div>
                        </div>
                    </div>

                    {/* Per Million Mortality */}
                    <div className="flex items-center justify-between p-3 bg-blue-900/10 rounded-lg border border-blue-900/30">
                        <span className="text-xs text-blue-200">Deaths per Million</span>
                        <span className="text-lg font-mono font-bold text-blue-400">{entry?.deathPerMil}</span>
                    </div>

                    <p className="text-[10px] text-gray-500 text-center italic">
                        Population: {entry?.population?.toLocaleString()}
                    </p>
                </div>
            </div>
        );
    }


}
export default HighlightCard;