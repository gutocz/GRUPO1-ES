import Navbar from "../../../components/header/NavBar.tsx";
import Container from "../../../components/content/Container.tsx";
import { openAdicionarComida } from "../../../islands/ModalAddPrato.tsx";
import { openEditPrato, selectedPrato } from "../../../islands/ModalEditPrato.tsx";
import ModalAddPrato from "../../../islands/ModalAddPrato.tsx";
import ModalEditPrato from "../../../islands/ModalEditPrato.tsx";
import { Handlers, PageProps } from "$fresh/server.ts"
import { getCookieValue } from "../../../sdk/getCookieValue.ts"

interface CustomPageProps extends PageProps {
    cookieValue: string | null;
}

export const handler: Handlers = {
    async GET(req, ctx) {
        const cookieValue = getCookieValue(req.headers.get("cookie"), "userType");

        if (!cookieValue) {
            return Response.redirect(new URL("/loginAluno", req.url), 303);
        }

        const response = await fetch("http://localhost:8080/api/item/getAll");
        const data = await response.json();
        return ctx.render({ data, cookieValue });
    },

    async POST(req, ctx) {
        const formData = await req.formData();
        const formValues: Record<string, string> = {};

        formData.forEach((value, key) => {
            formValues[key] = value.toString();
        });

        const formType = formValues["formType"];

        if (formType === "addPrato") {
            try {
                const response = await fetch(
                    "http://localhost:8080/api/item/create",
                    {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify(formValues),
                    },
                );

                const redirectUrl = new URL(`/logado/funcionario/1`, req.url);
                return Response.redirect(redirectUrl.toString(), 303);
            } catch (error) {
                console.error("Fetch error:", error);
                const redirectUrl = new URL("/cadastro", req.url);
                redirectUrl.searchParams.set("status", "500");
                redirectUrl.searchParams.set("responseText", error.message);
                return Response.redirect(redirectUrl.toString(), 303);
            }
        }

        else if (formType === "editPrato") {
            try {
                const response = await fetch(
                    `http://localhost:8080/api/item/${parseInt(formValues.index)}/put`,
                    {
                        method: "PUT",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify({
                            nome: formValues.nome,
                            descricao: formValues.descricao
                        }),
                    },
                );

                const redirectUrl = new URL(`/logado/funcionario/1`, req.url);
                return Response.redirect(redirectUrl.toString(), 303);
            } catch (error) {
                console.error("Fetch error:", error);
                const redirectUrl = new URL("/cadastro", req.url);
                redirectUrl.searchParams.set("status", "500");
                redirectUrl.searchParams.set("responseText", error.message);
                return Response.redirect(redirectUrl.toString(), 303);
            }
        }

        else if (formType === "deletePrato") {
            try {
                const response = await fetch(
                    `http://localhost:8080/api/item/${parseInt(formValues.index)}/delete`,
                    {
                        method: "DELETE",
                        headers: {
                            "Content-Type": "application/json",
                        },
                    },
                );

                console.log(response)

                const redirectUrl = new URL(`/logado/funcionario/1`, req.url);
                return Response.redirect(redirectUrl.toString(), 303);
            } catch (error) {
                console.error("Fetch error:", error);
                const redirectUrl = new URL("/cadastro", req.url);
                redirectUrl.searchParams.set("status", "500");
                redirectUrl.searchParams.set("responseText", error.message);
                return Response.redirect(redirectUrl.toString(), 303);
            }
        }
    },
};


export default function FuncionarioLogado({ data }: CustomPageProps) {
    return (
        <div class="bg-[#FAF6F1] min-h-screen">
            <Navbar logado={data.cookieValue} />
            <Container>
                <div class="max-w-6xl mx-auto">
                    <div class="flex justify-between items-center mb-6">
                        <ModalAddPrato />
                        <div class="relative">
                            <input
                                type="text"
                                style={{ borderColor: "#F77F00" }}
                                placeholder="Procure por comida..."
                                class="border rounded-full py-2 pl-4 bg-[#FAF6F1] pr-10 focus:outline-none focus:ring-1 focus:ring-orange-400"
                            />
                        </div>

                        <button
                            onClick={() => (openAdicionarComida.value = !openAdicionarComida.value)}
                            class="bg-[#ff7f16] text-black border px-4 py-2 rounded-md shadow-md hover:bg-[#e6700f] transition"
                        >
                            Adicionar prato
                        </button>
                    </div>
                    <h2 class="font-bold mb-4 text-2xl underline text-black pb-6">
                        Pratos disponíveis
                    </h2>
                    <div class="grid grid-cols-4 gap-10">
                        {data.data.map((prato, index) => (
                            <div key={index} class="bg-[#ff7f16] text-white px-2 py-3 rounded-xl shadow-md transition flex flex-col">
                                <span class="flex text-left">
                                    <p class="text-black font-medium mr-1">Nome do prato:</p> {prato.nome}
                                </span>
                                <span class="flex text-left">
                                    <p class="text-black font-medium mr-1">Descrição:</p> {prato.descricao}
                                </span>
                                <div class="flex justify-between mt-4">
                                    <button
                                        onClick={() => {
                                            selectedPrato.value = { index: index, nome: prato.nome, descricao: prato.descricao };
                                            openEditPrato.value = true;
                                        }}
                                        class="bg-blue-500 text-white px-2 py-1 rounded-md hover:bg-blue-700 transition"
                                    >
                                        Editar
                                    </button>
                                    <form method="POST">
                                        <input type="hidden" name="index" value={index} />
                                        <input type="hidden" name="formType" value="deletePrato" />
                                        <button
                                            type="submit"
                                            class="bg-red-500 text-white px-2 py-1 rounded-md hover:bg-red-700 transition"
                                        >
                                            Deletar
                                        </button>
                                    </form>
                                </div>
                            </div>
                        ))}
                    </div>

                </div>
            </Container>

            <ModalEditPrato />
        </div>
    );
}