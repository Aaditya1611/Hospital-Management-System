const UsaCountry = ({data}) => {

    return (
        <table className="w-full text-left">
            <thead> 
                <tr className="border-b border-gray-700 text-gray-400">
                    <th className="pb-4">Date</th>
                    <th className="pb-4">Province</th>
                    <th className="pb-4">Administration</th>
                    <th className="pb-4">Confirmed</th>
                    <th className="pb-4">Deaths</th>
                    <th className="pb-4">Longitude</th> 
                    <th className="pb-4">Latitude</th>
                </tr>
            </thead>
            <tbody>
                {data.map((row) => (
                    <tr key={row.id} className="border-b border-gray-700 hover:bg-gray-750 transition">
                        <td className="py-4 text-blue-400">{row.date}</td>
                        <td className="py-4 text-yellow-200">{row.province}</td>
                        <td className="py-4 text-yellow-500">{row.admin}</td>
                        <td className="py-4 text-red-300">{row.confirmed?.toLocaleString()}</td>
                        <td className="py-4 text-red-500">{row.deaths?.toLocaleString()}</td>
                        <td className="py-4 text-shadow-primary">{row.longitude}</td>
                        <td className="py-4 text-shadow-primary">{row.latitude}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    )
}

export default UsaCountry;