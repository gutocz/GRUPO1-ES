import Navbar from "../../../components/header/NavBar.tsx";
import Container from "../../../components/content/Container.tsx";
import { Handlers, PageProps } from "$fresh/server.ts"
import { getCookieValue } from "../../../sdk/getCookieValue.ts"
import { days, mealTypes } from "../../../constants.ts";
import { useState } from "preact/hooks";
import ModalAdicionarCardapio, { openAdicionarCardapio, selectedPrato } from "../../../islands/ModalCardapio.tsx";

interface CustomPageProps extends PageProps {
    cookieValue: string | null;
}


export const handler: Handlers = {

    async GET(req, ctx) {
        const cookieValue = getCookieValue(req.headers.get("cookie"), "userType");

        if (!cookieValue) {
            return Response.redirect(new URL("/loginAluno", req.url), 303);
        }

        const itemsRes = await fetch("http://localhost:8080/api/item/getAll");
        const cardapioRes = await fetch("http://localhost:8080/api/cardapio/getAll")

        const items = await itemsRes.json();
        const cardapios = await cardapioRes.json()
        return ctx.render({ items, cookieValue, cardapios });
    },

    async POST(req, ctx) {
        const formData = await req.formData();
        const formValues: Record<string, string> = {};

        formData.forEach((value, key) => {
            formValues[key] = value.toString();
        });

        try {
            await fetch("http://localhost:8080/api/cardapio/create", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    diaDaSemana: formValues.selectedDay,
                    tipoRefeicao: formValues.selectedType,
                    itens: JSON.parse(formValues.itens)
                }),
            });
        } catch (error) {
            console.error("Erro ao realizar o fetch:", error);
        }

        const redirectUrl = new URL("/logado/funcionario/criarCardapio", req.url);
        redirectUrl.searchParams.set("status", "500");

        return Response.redirect(redirectUrl.toString(), 303);
    }

};


export default function CriarCardapio({ data }: CustomPageProps) {
    const [selectedDay, setSelectedDay] = useState("SEGUNDA");
    const [selectedType, setSelectedType] = useState("");

    const getMealsForDay = (day: string, mealType: string) => {
        return data.cardapios
            .filter((cardapio) =>
                cardapio.diaDaSemana.trim() === day.trim() && cardapio.tipoRefeicao === mealType
            )
            .map((cardapio) => cardapio.itens)
            .flat();
    };

    return (
        <div class="bg-[#FAF6F1] min-h-screen">
            <Navbar logado={data.cookieValue} isFuncionario/>
            <div class="mt-10">
                <div class="max-w-6xl mx-auto">
                    <div class="flex justify-between mb-6 bg-white px-20 rounded-lg">
                        {days.map((day) => (
                            <button
                                key={day}
                                onClick={() => setSelectedDay(day.trim())}
                                style={{ borderColor: "#F77F00" }}
                                class={`px-7 text-sm font-medium py-4 ${selectedDay === day.trim()
                                    ? "bg-[#FAF6F1] border-b-4 text-orange-700"
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
                                style={{ borderColor: "#F77F00" }}
                                class="bg-white border rounded-lg p-8 w-full"
                                key={mealType}
                            >
                                <div class="flex relative">
                                    <h3
                                        style={{ borderColor: "#F77F00" }}
                                        class="text-base font-medium rounded-full border-2 max-w-24 text-center px-2 bg-[#FAF6F1] mb-12 mx-auto"
                                    >
                                        {mealType}
                                    </h3>
                                    <button
                                        onClick={() => {
                                            openAdicionarCardapio.value = !openAdicionarCardapio.value;
                                            setSelectedType(mealType);
                                        }}
                                        class="bg-ru-orange-500 text-black px-4 border top-0 absolute right-1 py-2 rounded-full text-sm hover:bg-orange-500"
                                    >
                                        Editar
                                    </button>
                                </div>
                                <ul class="grid grid-cols-2 gap-4">
                                    {getMealsForDay(selectedDay, mealType)
                                        .length > 0
                                        ? (getMealsForDay(selectedDay, mealType).map((mealItem) => (

                                            <div
                                                key={mealItem.id}
                                                class="bg-ru-orange-500 rounded-full p-6"
                                            >
                                                <p class="text-sm text-black">
                                                    {mealItem.nome} -{" "}
                                                    {mealItem.descricao}
                                                </p>
                                            </div>
                                        ))) : <p class="text-gray-500 text-center col-span-2">
                                            Nenhum item disponível
                                        </p>}
                                </ul>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
            <ModalAdicionarCardapio
                items={data.items}
                selectedDay={selectedDay}
                selectedType={selectedType}
            />
        </div>
    );
}
