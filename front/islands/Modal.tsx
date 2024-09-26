import { signal } from "@preact/signals";
import { Button } from "../components/ui/Button.tsx";

export const openModalPagar = signal(false);

const VALOR = -5

export default function Modal({ aluno, saldo }: { aluno: string, saldo: string }) {

    return (
        <dialog open={openModalPagar.value} id="my_modal_1" class="modal bg-[#C9AA97]">
            <div class="modal-box bg-white rounded-lg text-black text-center px-16 py-16">
                <h3 class="text-lg font-bold">
                    Você decidiu realizar a refeição no local!
                </h3>
                <h2 class="py-8">
                    Voce deseja pagar?
                </h2>
                <form method="dialog" class="modal-backdrop flex gap-x-6 justify-center">
                    <Button
                        style={{
                            borderWidth: "1px",
                            borderColor: "#D4D2E3",
                        }}
                        secondary
                        aria-label="Login"
                        href={`/logoado/aluno/${aluno}`}
                    >
                        Não
                    </Button>
                    <form method="POST">
                        <input type="hidden" name="valor" value={VALOR} />
                        <input type="hidden" name="matricula" value={aluno} />

                        <button
                            type="submit"
                            aria-label="pagar"
                            href={`/logoado/aluno/${aluno}`}
                            class="bg-ru-orange-500 rounded-full cursor-pointer leading-[18px] font-bold text-lg px-6 py-[18px] items-center hover:bg-[#f17011]"
                            style={{ color: "white" }}
                        >
                            Pagar
                        </button>
                    </form>
                </form>
            </div>
        </dialog>
    );
}
