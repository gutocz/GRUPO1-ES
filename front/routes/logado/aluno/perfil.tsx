import Navbar from "../../../components/header/NavBar.tsx";
import { FreshContext, Handlers, PageProps } from "$fresh/server.ts";
import { getCookieValue } from "../../../sdk/getCookieValue.ts"

interface CustomProps extends PageProps {
    cookieValue: string | null
}

export const handler: Handlers = {
    async GET(req, ctx: FreshContext) {
        const cookieValue = getCookieValue(req.headers.get("cookie"), "userType");

        if (!cookieValue) {
            return Response.redirect(new URL("/loginAluno", req.url), 303);
        }

        const response = await fetch(`http://localhost:8080/api/usuarios/Aluno/${cookieValue}`);
        const data = await response.json();
        return ctx.render({ data, cookieValue });
    },
};

export default function PerfilAluno({ data }: CustomProps) {
    return (
        <>
            <Navbar logado={data.cookieValue} saldo={data.data.saldo} />

            <div class="bg-[#f7f1e6] min-h-screen p-10">
                <div class="max-w-4xl mx-auto">
                    <div class="bg-white rounded-xl p-6 shadow-md mb-6 min-h-[500px] py-20 px-28">
                        <div class="grid grid-cols-2 gap-12">
                            <div>
                                <p class="text-black text-2xl">Nome:</p>
                                <p class="text-[#8b8b8b] text-xl pt-4">
                                    {data.data.nome}
                                </p>
                            </div>
                            <div>
                                <p class="text-black text-2xl">Email:</p>
                                <p class="text-[#8b8b8b] text-xl pt-4">
                                    {data.data.email}
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
                                    {data.data.telefone}
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
                                <p class="text-black text-2xl">Matrícula:</p>
                                <p class="text-[#8b8b8b] text-xl pt-4">
                                    {data.data.matricula?.replace(/\d/g, '*', 6)}
                                </p>
                            </div>
                            <div>
                                <p class="text-black text-2xl">Curso:</p>
                                <p class="text-[#8b8b8b] text-xl pt-4">
                                    {data.data.curso}
                                </p>
                            </div>
                        </div>
                        <div class="flex justify-end mt-4">
                            <button  class="bg-[#ff7f16] text-white px-8 py-4 rounded-full shadow-md hover:bg-[#e6700f] transition">
                                Deletar Conta
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
