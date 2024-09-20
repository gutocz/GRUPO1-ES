import Navbar from "../../../components/header/NavBar.tsx";
import Container from "../../../components/content/Container.tsx";

export default function FuncionarioLogado() {
    return (
        <div class="bg-[#FAF6F1] min-h-screen">
            <Navbar />
            <Container>
                <div class="max-w-6xl mx-auto">
                    {/* Barra de busca e botão adicionar prato */}
                    <div class="flex justify-between items-center mb-6">
                        <div class="relative">
                            <input
                                type="text"
                                style={{ borderColor: "#F77F00" }}
                                placeholder="Procure por comida..."
                                class="border rounded-full py-2 pl-4 bg-[#FAF6F1] pr-10 focus:outline-none focus:ring-1 focus:ring-orange-400"
                            />
                        </div>
                        <button class="bg-[#ff7f16] text-black border px-4 py-2 rounded-md shadow-md hover:bg-[#e6700f] transition">
                            Adicionar prato
                        </button>
                    </div>
                    <h2 class="font-bold mb-4 text-2xl underline text-black pb-6">Pratos disponíveis</h2>
                    <div class="grid grid-cols-4 gap-10 ">
                        {/* Repetir o botão de prato */}
                        {Array(20)
                            .fill(0)
                            .map(() => (
                                <button class="bg-[#ff7f16] text-white px-6 py-3 rounded-xl shadow-md hover:bg-[#e6700f] transition">
                                    Nome do prato
                                </button>
                            ))}
                    </div>
                </div>
            </Container>

            <div class="fixed bottom-4 right-4">
            </div>
        </div>
    );
}
