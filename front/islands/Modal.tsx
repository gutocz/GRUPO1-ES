import { signal } from "@preact/signals";
import { Button } from "../components/ui/Button.tsx";

export const openModalPagar = signal(false);

export default function Modal() {
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
                        href="/loginAluno"
                    >
                        Não
                    </Button>
                    <Button
                        primary
                        aria-label="Cadastro"
                        href="/cadastro"
                        style={{ color: "white" }}
                    >
                       Pagar
                    </Button>
                </form>
            </div>
        </dialog>
    );
}
