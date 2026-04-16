const CovidComplete = ({data}) => {

    return (
        <table className="w-full text-left">
            <thead>
                <tr className="border-b border-gray-700 text-gray-400">
                    <th className="pb-4">Date</th>
                    <th className="pb-4">Country</th>
                    <th className="pb-4">Confirmed</th>
                    <th className="pb-4">Active</th>
                    <th className="pb-4">Death</th>
                    <th className="pb-4">Longitude</th>
                    <th className="pb-4">Latitude</th>
                    <th className="pb-4">WHO Region</th>
                </tr>
            </thead>
            <tbody>
                {data.map((row) => (
                    <tr key={row.id} className="border-b border-gray-700 hover:bg-gray-750 transition">
                        <td className="py-4 text-blue-400">{row.date}</td>
                        <td className="py-4 font-medium">{row.country}</td>
                        <td className="py-4 text-red-300">{row.confirmed?.toLocaleString()}</td>
                        <td className="py-4 text-yellow-400">{row.active?.toLocaleString()}</td>
                        <td className="py-4 text-red-500">{row.death?.toLocaleString()}</td>
                        <td className="py-4 text-shadow-primary">{row.longitude?.toLocaleString()}</td>
                        <td className="py-4 text-shadow-primary">{row.latitude?.toLocaleString()}</td>
                        <td className="py-4 text-gray-400 text-sm">{row.region}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    )
}

export default CovidComplete;