import Navbar from "../../../components/header/NavBar.tsx";
import Modal, { openModalPagar } from "../../../islands/Modal.tsx";
import { days, mealTypes } from "../../../constants.ts";
import { FreshContext, Handlers, PageProps } from "$fresh/server.ts";
import { useState } from "preact/hooks";
import { getCookieValue } from "../../../sdk/getCookieValue.ts"
import Token, { openToken } from "../../../islands/Token.tsx";
import ModalPedirMarmita, { openModalPedir } from "../../../islands/ModalPedirMarmita.tsx";

interface CustomPageProps extends PageProps {
    cookieValue: string | null;
}

export const handler: Handlers = {
    async GET(req, ctx: FreshContext) {
        const cookieValue = getCookieValue(req.headers.get("cookie"), "userType");

        if (!cookieValue) {
            return Response.redirect(new URL("/loginAluno", req.url), 303);
        }

        const alunoRes = await fetch(`http://localhost:8080/api/usuarios/Aluno/${cookieValue}`);
        const response = await fetch("http://localhost:8080/api/cardapio/getAll");
        const cardapioRes = await fetch("http://localhost:8080/api/cardapio/getAll")
        const cardapios = await cardapioRes.json()
        const data = await response.text();
        const aluno = await alunoRes.json()
        return ctx.render({ data, cookieValue, cardapios, aluno });
    },
    async POST(req, ctx) {
        const formData = await req.formData();
        const formValues: Record<string, string> = {};

        formData.forEach((value, key) => {
            formValues[key] = value.toString();
        });

        try {
            const a = await fetch(`http://localhost:8080/api/usuarios/recarga/${formValues.matricula}?valor=${formValues.valor}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    matricula: formValues.matricula,
                    valor: parseFloat(formValues.valor)
                }),
            });
        } catch (error) {
            console.error("Erro ao realizar o fetch:", error);
        }

        const redirectUrl = new URL(`/logado/aluno/${formValues.matricula}`, req.url);
        redirectUrl.searchParams.set("status", "500");

        return Response.redirect(redirectUrl.toString(), 303);
    }


};

export default function AlunoLogado({ data }: CustomPageProps) {
    const [selectedDay, setSelectedDay] = useState("SEGUNDA");

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
            <Navbar logado={data.cookieValue} saldo={data.aluno.saldo} />

            <main class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
                <div class="mb-6 flex justify-between items-center">
                    <div style={{ borderColor: "#F77F00" }} class=" px-4 py-2 border rounded-lg flex items-center ">
                        <span class="text-sm font-medium flex text-black">
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
                            <span class="ml-2">08:29</span>
                        </span>
                    </div>
                    <div class="space-x-4">
                        <Modal aluno={data.cookieValue} />
                        <button
                            onClick={() =>
                                openModalPagar.value = !openModalPagar.value}
                            class="bg-ru-orange-500 text-black border px-14 py-2 rounded-md text-sm font-medium"
                        >
                            Refeição no local
                        </button>

                        <button onClick={() =>
                            openModalPedir.value = !openModalPedir.value} class="bg-ru-orange-500 text-black px-14 border py-2 rounded-md text-sm font-medium">
                            Pedir marmita
                        </button>
                    </div>
                </div>
                <ModalPedirMarmita aluno={data.cookieValue} />
                <div class="w-full">
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

                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </main>

            <Token />
            <div class="fixed bottom-4 right-4">
                <button onClick={() => openToken.value = !openToken.value}>
                    <svg xmlns="http://www.w3.org/2000/svg" width="124" height="124" viewBox="0 0 124 124" fill="none">
                        <rect x="1.5" y="1.5" width="121" height="121" rx="60.5" fill="#F77F00" />
                        <rect x="1.5" y="1.5" width="121" height="121" rx="60.5" stroke="white" stroke-width="3" />
                        <path d="M72.5682 40.8637C72.5682 37.5809 72.5682 35.9395 73.1044 34.6447C73.8195 32.9184 75.1913 31.5468 76.9174 30.8318C78.2124 30.2955 79.8536 30.2955 83.1364 30.2955C86.4192 30.2955 88.0605 30.2955 89.3554 30.8318C91.0816 31.5468 92.4533 32.9184 93.1684 34.6447C93.7046 35.9395 93.7046 37.5809 93.7046 40.8637C93.7046 44.1464 93.7046 45.7878 93.1684 47.0826C92.4533 48.8089 91.0816 50.1805 89.3554 50.8955C88.0605 51.4318 86.4192 51.4318 83.1364 51.4318C79.8536 51.4318 78.2124 51.4318 76.9174 50.8955C75.1913 50.1805 73.8195 48.8089 73.1044 47.0826C72.5682 45.7878 72.5682 44.1464 72.5682 40.8637Z" stroke="white" stroke-width="5" stroke-linejoin="round" />
                        <path d="M30.2955 40.8637C30.2955 37.5809 30.2955 35.9395 30.8318 34.6447C31.5468 32.9184 32.9184 31.5468 34.6447 30.8318C35.9395 30.2955 37.5809 30.2955 40.8637 30.2955C44.1464 30.2955 45.7878 30.2955 47.0826 30.8318C48.8089 31.5468 50.1805 32.9184 50.8955 34.6447C51.4318 35.9395 51.4318 37.5809 51.4318 40.8637C51.4318 44.1464 51.4318 45.7878 50.8955 47.0826C50.1805 48.8089 48.8089 50.1805 47.0826 50.8955C45.7878 51.4318 44.1464 51.4318 40.8637 51.4318C37.5809 51.4318 35.9395 51.4318 34.6447 50.8955C32.9184 50.1805 31.5468 48.8089 30.8318 47.0826C30.2955 45.7878 30.2955 44.1464 30.2955 40.8637Z" stroke="white" stroke-width="5" stroke-linejoin="round" />
                        <path d="M30.2955 83.1364C30.2955 79.8536 30.2955 78.2124 30.8318 76.9174C31.5468 75.1913 32.9184 73.8195 34.6447 73.1044C35.9395 72.5682 37.5809 72.5682 40.8637 72.5682C44.1464 72.5682 45.7878 72.5682 47.0826 73.1044C48.8089 73.8195 50.1805 75.1913 50.8955 76.9174C51.4318 78.2124 51.4318 79.8536 51.4318 83.1364C51.4318 86.4192 51.4318 88.0605 50.8955 89.3554C50.1805 91.0816 48.8089 92.4533 47.0826 93.1684C45.7878 93.7046 44.1464 93.7046 40.8637 93.7046C37.5809 93.7046 35.9395 93.7046 34.6447 93.1684C32.9184 92.4533 31.5468 91.0816 30.8318 89.3554C30.2955 88.0605 30.2955 86.4192 30.2955 83.1364Z" stroke="white" stroke-width="5" stroke-linejoin="round" />
                        <path d="M62 30.2955V40.8637" stroke="white" stroke-width="5" stroke-linecap="round" stroke-linejoin="round" />
                        <path d="M83.1364 83.1364H72.5682" stroke="white" stroke-width="5" stroke-linecap="round" stroke-linejoin="round" />
                        <path d="M93.7046 72.5682H83.1364" stroke="white" stroke-width="5" stroke-linecap="round" stroke-linejoin="round" />
                        <path d="M51.4318 62H30.2955" stroke="white" stroke-width="5" stroke-linecap="round" stroke-linejoin="round" />
                        <path d="M93.7045 62H65.5227C63.5771 62 62 60.4229 62 58.4773V51.4318" stroke="white" stroke-width="5" stroke-linecap="round" stroke-linejoin="round" />
                        <path d="M70.8068 93.7046H90.1818C92.1274 93.7046 93.7046 92.1275 93.7046 90.1819V83.1364" stroke="white" stroke-width="5" stroke-linecap="round" stroke-linejoin="round" />
                        <path d="M62 93.7046V76.7955V76.091C62 74.1454 63.5771 72.5682 65.5227 72.5682H72.5682" stroke="white" stroke-width="5" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                </button>
            </div>
        </div>
    );
}
