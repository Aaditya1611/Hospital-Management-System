const WorldoMeter = ({data}) => {

    return (
       <table className="w-full text-left">
            <thead> 
                <tr className="border-b border-gray-700 text-gray-400">
                    <th className="pb-4">Country</th>
                    <th className="pb-4">Continent</th>
                    <th className="pb-4">Population</th>
                    <th className="pb-4">Total Cases</th>
                    <th className="pb-4">Active Cases</th>
                    <th className="pb-4">Total Deaths</th>
                    <th className="pb-4">Total Recovered</th> 
                </tr>
            </thead>
            <tbody>
                {data.map((row) => (
                    <tr key={row.id} className="border-b border-gray-700 hover:bg-gray-750 transition">
                        <td className="py-4 text-blue-400">{row.country}</td>
                        <td className="py-4 text-shadow-primary">{row.continent}</td>
                        <td className="py-4 text-shadow-primary">{row.population?.toLocaleString()}</td>
                        <td className="py-4 text-yellow-500">{row.totalCases?.toLocaleString()}</td>
                        <td className="py-4 text-red-300">{row.activeCases?.toLocaleString()}</td>
                        <td className="py-4 text-red-500">{row.totalDeaths?.toLocaleString()}</td>
                        <td className="py-4 text-green-600">{row.totalRecovered?.toLocaleString()}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    )
}

export default WorldoMeter;