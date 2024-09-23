import Navbar from "../components/header/NavBar.tsx";
import Input from "../components/ui/Input.tsx";
import { Button } from "../components/ui/Button.tsx";
import Container from "../components/content/Container.tsx";
import { Handlers, PageProps } from "$fresh/server.ts";
import { useEffect, useState } from "preact/hooks";

const INPUTS = [
    {
        label: "Nome",
        placeholder: "João victor",
        type: "text",
        id: "nome-input",
        name: "nome",
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
    {
        label: "Telefone",
        placeholder: "(83) 99999-9999",
        type: "text",
        id: "telefone-input",
        name: "telefone",
        required: false,
    },
    {
        label: "Email",
        placeholder: "exemplo@exem.com",
        type: "email",
        id: "email-input",
        name: "email",
        required: true,
    },
    {
        label: "Matrícula",
        placeholder: "123456788",
        type: "text",
        id: "matricula-input",
        name: "matricula",
        required: true,
    },
    {
        label: "Curso",
        placeholder: "Ciências da Computação",
        type: "text",
        id: "curso-input",
        name: "curso",
        required: true,
    },
];

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

        const values = { ...formValues, saldo: 0 };

        try {
            const response = await fetch(
                "http://localhost:8080/api/usuarios/create",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(values),
                },
            );

            const status = response.status;
            const text = await response.text();
            const redirectUrl = new URL("/cadastro", req.url);
            redirectUrl.searchParams.set("status", status.toString());
            redirectUrl.searchParams.set("responseText", text);
            return Response.redirect(redirectUrl.toString(), 303);
        } catch (error) {
            console.error("Fetch error:", error);
            const redirectUrl = new URL("/cadastro", req.url);
            redirectUrl.searchParams.set("status", "500");
            redirectUrl.searchParams.set("responseText", error.message);
            return Response.redirect(redirectUrl.toString(), 303);
        }
    },
};

interface CadastroData {
    status?: string;
    statusText?: string;
    responseText?: string;
    formValues: Record<string, string>;
}

export default function Cadastro({ data }: PageProps<CadastroData>) {
    const [message, setMessage] = useState<string>("");
    const [success, setSuccess] = useState<boolean>(false);

    useEffect(() => {
        if (data) {
            const status = data.status ? parseInt(data.status) : null;
            if (status === 201) {
                setSuccess(true);
                setMessage("Cadastrado com sucesso!");
                const timer = setTimeout(() => {
                    window.location.href = "/loginAluno";
                }, 3000);
                return () => clearTimeout(timer);
            } else {
                setMessage(`Erro: ${data.responseText || "Ocorreu um erro"}`);
            }
        }
    }, [data]);

    return (
        <div class="bg-[#FAF6F1] min-h-screen ">
            <Navbar />
            <Container>
                <form
                    method="POST"
                    class="w-full max-w-[600px] mx-auto"
                    id="teste"
                >
                    <div class="w-full grid grid-cols-1 sm:grid-cols-2 gap-x-6">
                        {INPUTS.map((input) => (
                            <Input key={input.id} {...input} />
                        ))}
                    </div>
                    <div class="flex flex-row w-full justify-center gap-x-3 mx-auto pt-6">
                        <button
                            style={{ color: "white" }}
                            type="submit"
                            aria-label="Criar conta"
                            class="bg-ru-orange-500 rounded-full cursor-pointer leading-[18px] font-bold text-lg px-6 py-[18px] items-center hover:bg-[#f17011]"
                        >
                            Criar conta
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
                {message && (
                    <span
                        class={`mt-3 w-full flex justify-center ${
                            success ? "text-green-500" : "text-red-500"
                        }`}
                    >
                        {message}
                    </span>
                )}
            </Container>
        </div>
    );
}



