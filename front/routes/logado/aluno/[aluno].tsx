import Navbar from "../../../components/header/NavBar.tsx";
import Modal, { openModalPagar } from "../../../islands/Modal.tsx";
import { days, mealTypes } from "../../../../constants.ts";

export default function AlunoLogado() {
    return (
        <div class="bg-[#FAF6F1] min-h-screen">
            <Navbar />

            <main class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
                <div class="mb-6 flex justify-between items-center">
                    <div class="  px-4 py-2 flex items-center ">
                        <span class="text-sm font-medium flex">
                            <span class="mr-2">Tempo de fila:</span>{" "}
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="15"
                                height="15"
                                viewBox="0 0 22 22"
                                fill="none"
                                class="mt-1"
                            >
                                <path
                                    d="M1 11C1 13.6522 2.05357 16.1957 3.92893 18.0711C5.8043 19.9464 8.34784 21 11 21C13.6522 21 16.1957 19.9464 18.0711 18.0711C19.9464 16.1957 21 13.6522 21 11C21 8.34784 19.9464 5.8043 18.0711 3.92893C16.1957 2.05357 13.6522 1 11 1C8.34784 1 5.8043 2.05357 3.92893 3.92893C2.05357 5.8043 1 8.34784 1 11V11Z"
                                    stroke="black"
                                    stroke-width="2"
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                />
                                <path
                                    d="M11 11V7.42859"
                                    stroke="black"
                                    stroke-width="2"
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                />
                                <path
                                    d="M11 11L15.4638 15.4648"
                                    stroke="black"
                                    stroke-width="2"
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                />
                            </svg>{" "}
                            <span class="ml-2">13:99</span>
                        </span>
                    </div>
                    <div class="space-x-4">
                        <Modal />
                        <button
                            onClick={() =>
                                openModalPagar.value = !openModalPagar.value}
                            class="bg-ru-orange-500 text-black border px-14 py-2 rounded-md text-sm font-medium"
                        >
                            Refeição no local
                        </button>
                        <button class="bg-ru-orange-500 text-black px-14 border py-2 rounded-md text-sm font-medium">
                            Pedir marmita
                        </button>
                    </div>
                </div>

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
                                <h3
                                    style={{ borderColor: "#F77F00" }}
                                    class="text-base font-medium rounded-full border-2 max-w-24 text-center bg-[#FAF6F1] mb-12 mx-auto"
                                >
                                    {mealType}
                                </h3>
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

            <div class="fixed bottom-4 right-4">
                {/* <QrCode class="h-12 w-12 text-orange-500" /> */}
            </div>
        </div>
    );
}
