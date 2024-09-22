import Navbar from "../components/header/NavBar.tsx";
import Input from "../components/ui/Input.tsx";
import { Button } from "../components/ui/Button.tsx";
import Container from "../components/content/Container.tsx";
import { FreshContext, Handlers, PageProps } from "$fresh/server.ts";

export const handler: Handlers = {
    async GET(req, ctx) {
        const url = new URL(req.url);
        const status = url.searchParams.get("status");
        const responseText = url.searchParams.get("responseText");

        url.searchParams.delete("status");
        url.searchParams.delete("responseText");

        return ctx.render({
            status,
            responseText,
            formValues: {},
        });
    },

    async POST(req, ctx) {
        const formData = await req.formData();
        const formValues: Record<string, string> = {};

        formData.forEach((value, key) => {
            formValues[key] = value.toString();
        });

        try {
            const response = await fetch(
                "http://localhost:8080/api/login/usuario",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(formValues),
                },
            );

            const status = response.status;
            const text = await response.text();
            const redirectUrl = new URL("/loginAluno", req.url);
            redirectUrl.searchParams.set("status", status.toString());
            redirectUrl.searchParams.set("responseText", text);
            return Response.redirect(redirectUrl.toString(), 303);
        } catch (error) {
            console.error("Fetch error:", error);
            const redirectUrl = new URL("/loginAluno", req.url);
            redirectUrl.searchParams.set("status", "500");
            redirectUrl.searchParams.set("responseText", error.message);
            return Response.redirect(redirectUrl.toString(), 303);
        }
    },
};

const INPUTS = [
    {
        label: "Matrícula",
        placeholder: "123456788",
        type: "text",
        id: "matricula-input",
        name: "matricula",
        required: true,
    },
    {
        label: "Senha",
        placeholder: "********",
        type: "password",
        id: "senha-input",
        name: "senha",
        required: true,
    },
];

export default function LoginAluno({ data }: PageProps) {
    return (
        <div class="bg-[#FAF6F1] min-h-screen ">
            <Navbar />
            <Container>
                <div class="h-10 w-full mx-auto max-w-[300px] flex flex-row mb-6">
                    <a
                        href="/loginAluno"
                        class="h-full bg-ru-orange-500 text-white rounded-l-md flex w-1/2 text-center items-center"
                    >
                        <span class="mx-auto">Aluno</span>
                    </a>
                    <a
                        href="/loginFuncionario"
                        class="border-[#e2e2e2] border rounded-r-md flex w-1/2 text-center items-center "
                    >
                        <span class="mx-auto">Funcionário</span>
                    </a>
                </div>
                <form
                    method="POST"
                    class="mx-auto max-w-[300px] w-full"
                >
                    <div class="w-full grid grid-cols-1 gap-x-6">
                        {INPUTS.map((input) => <Input {...input} />)}
                    </div>
                    <div class="flex flex-row w-full justify-center gap-x-3 mx-auto pt-6">
                        <button
                            style={{
                                color: "white",
                            }}
                            type="submit"
                            class="bg-ru-orange-500 rounded-full cursor-pointer leading-[18px] font-bold text-lg px-6 py-[18px] items-center hover:bg-[#f17011]"
                            aria-label="Criar conta"
                        >
                            Entrar
                        </button>
                        <Button
                            style={{
                                borderWidth: "1px",
                                borderColor: "#D4D2E3",
                            }}
                            secondary
                            aria-label="Cancelar"
                            href="/"
                        >
                            Cancelar
                        </Button>
                    </div>
                </form>

                <a class="text-ru-orange-500 underline flex justify-center pt-10">
                    Esqueci minha senha
                </a>
            </Container>
        </div>
    );
}
