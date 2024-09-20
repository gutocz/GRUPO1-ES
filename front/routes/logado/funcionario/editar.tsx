import { days, mealTypes } from "../../../../constants.ts";
import Navbar from "../../../components/header/NavBar.tsx";

export default function Editar() {
    return (
        <div class="bg-[#FAF6F1] min-h-screen">
            <Navbar />
            <main class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
                <div class="w-full">
                    <div class="flex justify-between mb-6 bg-white px-20 rounded-lg">
                        {days.map((day, index) => (
                            <button
                                key={day}
                                style={{ borderColor: "#F77F00" }}
                                class={`px-7 text-sm font-medium py-4 ${
                                    index === 0
                                        ? "bg-[#FAF6F1] border-b-4  text-orange-700"
                                        : "text-gray-500 hover:bg-gray-100"
                                }`}
                            >
                                {day}
                            </button>
                        ))}
                    </div>

                    <div class="grid grid-cols-2 gap-4 mt-4">
                        {mealTypes.map((mealType) => (
                            <div
                                class="bg-white rounded-lg p-8 w-full"
                                key={mealType}
                            >
                                <div class="flex justify-between">
                                    <h3
                                        style={{ borderColor: "#F77F00" }}
                                        class="text-base font-medium rounded-full border-2 px-2 max-w-24 text-center bg-[#FAF6F1] mb-12 mx-auto"
                                    >
                                        {mealType}
                                    </h3>
                                    <button class="bg-ru-orange-500 text-black px-5 border h-8 rounded-md text-sm font-medium border
                                    ">
                                        Editar
                                    </button>
                                </div>
                                <div class="grid grid-cols-2 gap-4">
                                    {[...Array(14)].map((_, i) => (
                                        <div
                                            key={i}
                                            class="bg-[#FCBF49] rounded-md p-6"
                                        >
                                            <p class="text-sm text-gray-600">
                                                Descrição do item
                                            </p>
                                        </div>
                                    ))}
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </main>
        </div>
    );
}
