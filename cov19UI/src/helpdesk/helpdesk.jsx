import Navbar from "../components/navbar";
import { Settings } from "lucide-react";
import { BellIcon } from "lucide-react";

const HelpDesk = () => {

    return (
        <div>
            <section className="h-screen p-4">
                <Navbar />
                <div className="flex flex-col p-10 gap-y-4">
                    <div className="flex flex-row justify-between items-center">
                        <h1 className="text-white text-4xl">Hey, Amanda! Glad to have you back</h1>
                    </div>
                    <div className="grid grid-cols-3 gap-4">
                        <div className="bg-amber-50">Item 1</div>
                        <div className="bg-amber-50 col-span-2">Item 2</div>
                        <div className="bg-amber-50">Item 3</div>
                    </div>
                </div>
            </section>
        </div>
    )
}

export default HelpDesk;