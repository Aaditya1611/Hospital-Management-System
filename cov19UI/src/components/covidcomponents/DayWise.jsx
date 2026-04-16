const DayWise = ({ data }) => {

    return (
        <table className="w-full text-left">
            <thead>
                <tr className="border-b border-gray-700 text-gray-400">
                    <th className="pb-4">Date</th>
                    <th className="pb-4">Confirmed</th>
                    <th className="pb-4">New Cases</th>
                    <th className="pb-4">Active Cases</th>
                    <th className="pb-4">Death</th>
                    <th className="pb-4">Recovered</th>
                    <th className="pb-4">Countries Count</th>
                </tr>
            </thead>
            <tbody>
                {data.map((row) => (
                    <tr key={row.id} className="border-b border-gray-700 hover:bg-gray-750 transition">
                        <td className="py-4 text-blue-400">{row.date}</td>
                        <td className="py-4 text-red-300">{row.confirmed?.toLocaleString()}</td>
                        <td className="py-4 text-yellow-300">{row.newCases?.toLocaleString()}</td>
                        <td className="py-4 text-yellow-500">{row.active?.toLocaleString()}</td>
                        <td className="py-4 text-red-500">{row.deaths?.toLocaleString()}</td>
                        <td className="py-4 text-green-600">{row.recovered?.toLocaleString()}</td>
                        <td className="py-4 text-gray-300">{row.countriesCount?.toLocaleString()}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    )
}

export default DayWise;