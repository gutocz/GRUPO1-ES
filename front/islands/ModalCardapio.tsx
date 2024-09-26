import { signal } from "@preact/signals";
import Container from "../components/content/Container.tsx";

interface Prato {
    id: number;
    nome: string;
    descricao: string;
}

export const openAdicionarCardapio = signal(false);
export const selectedPrato = signal<Prato[]>([]);

export default function ModalAdicionarCardapio(props: { items: Prato[], selectedDay: string, selectedType: string }) {
    const { items, selectedDay, selectedType } = props;

    const toggleSelectPrato = (item: Prato) => {
        const isSelected = selectedPrato.value.some((prato) => prato.id === item.id);
        const updatedPratos = [...selectedPrato.value];

        if (isSelected) {

            const pratoIndex = updatedPratos.findIndex((prato) => prato.id === item.id);
            updatedPratos.splice(pratoIndex, 1);
        } else {

            updatedPratos.push(item);
        }

        selectedPrato.value = updatedPratos;
    };

    return (
        <dialog
            open={openAdicionarCardapio.value}
            id="my_modal_1"
            class="modal bg-[#C9AA97]"
        >
            <Container>
                <form method="POST">
                    <input type="hidden" value={selectedDay} name="selectedDay" />
                    <input type="hidden" value={selectedType} name="selectedType" />
                    <input type="hidden" value={JSON.stringify(selectedPrato.value)} name="itens" />
                    <button
                        type="submit"
                        class="bg-ru-orange-500 text-black px-8 border py-2 rounded-md text-sm font-medium mb-6"
                    >
                        Salvar
                    </button>
                </form>
                <div class="grid grid-cols-3 gap-4">
                    {items.map((item) => (
                        <div
                            key={item.id}
                            class={`${selectedPrato.value.find((prato) => prato.id === item.id)
                                ? "bg-green-500"
                                : "bg-ru-orange-500"
                                } rounded-full p-6`}
                        >
                            <button
                                onClick={() => toggleSelectPrato(item)}
                                class="text-sm text-black"
                            >
                                {item.nome} - {item.descricao}
                            </button>
                        </div>
                    ))}
                </div>
            </Container>
        </dialog>
    );
}
