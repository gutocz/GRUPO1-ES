import Navbar from "../../../components/header/NavBar.tsx";

export default function PerfilAluno() {
    return (
        <>
            <Navbar isFuncionario/>

            <div class="bg-[#f7f1e6] min-h-screen p-10">
                <div class="max-w-4xl mx-auto">
        
                    <div class="bg-white rounded-xl p-6 shadow-md mb-6 min-h-[500px] py-20 px-28">
                        <div class="grid grid-cols-2 gap-12">
                            <div>
                                <p class="text-black text-2xl">Nome:</p>
                                <p class="text-[#8b8b8b] text-xl pt-4">
                                    João Victor
                                </p>
                            </div>
                            <div>
                                <p class="text-black text-2xl">Email:</p>
                                <p class="text-[#8b8b8b] text-xl pt-4">
                                    example@email.com
                                </p>
                            </div>
                            <div>
                                <p class="text-black text-2xl">Senha:</p>
                                <p class="text-[#8b8b8b] text-xl pt-4">
                                    *********
                                </p>
                            </div>
                            <div>
                                <p class="text-black text-2xl">Telefone:</p>
                                <p class="text-[#8b8b8b] text-xl pt-4">
                                    (83) 99999 - 9999
                                </p>
                            </div>
                        </div>
                        <div class="flex justify-end mt-4">
                            <button class="bg-ru-orange-500 text-white px-8 py-4 rounded-full shadow-md hover:bg-[#e6700f] transition">
                                Editar
                            </button>
                        </div>
                    </div>

                    <div class="bg-white rounded-lg p-6 shadow-md">
                        <div class="grid grid-cols-2 gap-4">
                            <div>
                                <p class="text-black text-2xl">CPF:</p>
                                <p class="text-[#8b8b8b] text-xl pt-4">
                                    ******789
                                </p>
                            </div>
                            <div>
                                <p class="text-black text-2xl">Cargo:</p>
                                <p class="text-[#8b8b8b] text-xl pt-4">
                                    Cozinheiro
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
