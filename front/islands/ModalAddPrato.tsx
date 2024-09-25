import { signal } from "@preact/signals";

export const openAdicionarComida = signal(false);

export default function ModalAdicionarComida() {
    return (
        <dialog
            open={openAdicionarComida.value}
            id="my_modal_1"
            class="modal bg-[#C9AA97]"
        >
            <div  class="modal-box bg-white rounded-lg text-black  px-16 py-16">
                <form method="dialog">
                    <div class="mb-4 ">
                        <label
                            class=" font-bold mb-2 text-lg"
                            htmlFor="nomePrato"
                        >
                            Nome do prato:
                        </label>
                        <input
                            id="nomePrato"
                            type="text"
                            class="border border-orange-400 rounded-xl w-full px-4 py-2 focus:outline-none focus:ring-1 bg-white focus:ring-orange-400"
                            placeholder="Digite o nome do prato"
                        />
                    </div>

                    <div class="mb-4">
                        <label
                            class=" font-bold mb-2 text-lg"
                            htmlFor="descricaoPrato"
                        >
                            Descrição do prato:
                        </label>
                        <input
                            id="descricaoPrato"
                            type="text"
                            class="border border-orange-400 rounded-xl w-full px-4 py-2 focus:outline-none focus:ring-1 bg-white focus:ring-orange-400"
                            placeholder="Digite a descrição do prato"
                        />
                    </div>

                    <div class="flex justify-between">
                        <a
                            href="/logado/funcionario/perfi"
                            type="button"
                            class="border border-orange-400 text-orange-400 px-4 py-2 rounded-xl hover:bg-orange-50 transition"
                        >
                            Voltar
                        </a>
                        <button
                            type="submit"
                            class="bg-[#ff7f16] text-white px-4 py-2 rounded-xl shadow-md hover:bg-[#e6700f] transition"
                        >
                            Adicionar prato
                        </button>
                    </div>
                </form>
            </div>
        </dialog>
    );
}
