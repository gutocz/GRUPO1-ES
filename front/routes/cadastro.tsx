import Navbar from "../components/header/NavBar.tsx";
import { FreshContext, Handlers } from "$fresh/server.ts";
import Input from "../components/ui/Input.tsx";
import { Button } from "../components/ui/Button.tsx";

export const handler: Handlers = {
    GET(_req: Request, _ctx: FreshContext) {
        return _ctx.render();
    },
};

const INPUTS = [
    {
        label: "Nome",
        placeholder: "João victor",
        type: "text",
        id: "nome-input",
    },
    {
        label: "Senha",
        placeholder: "********",
        type: "password",
        id: "senha-input",
    },
    {
        label: "Telefone",
        placeholder: "(83) 99999-9999",
        type: "text",
        id: "telefone-input",
    },
    {
        label: "Email",
        placeholder: "exemplo@exem.com",
        type: "email",
        id: "email-input",
    },
    {
        label: "Matrícula",
        placeholder: "123456788",
        type: "text",
        id: "matricula-input",
    },
    {
        label: "Curso",
        placeholder: "Ciências da Computação",
        type: "text",
        id: "curso-input",
    },
];

export default function Cadastro() {
    return (
        <div class="bg-[#FAF6F1] min-h-screen ">
            <Navbar />
            <div class="bg-white rounded-3xl mx-auto mt-12 min-h-[694px] max-w-[1178px] w-full py-16 px-20">
                <form
                    method="POST"
                    class="w-full max-w-[600px] mx-auto"
                >
                    <div class="w-full grid grid-cols-1 sm:grid-cols-2 gap-x-6">
                        {INPUTS.map((input) => <Input {...input} />)}
                    </div>
                    <div class="flex flex-row w-full justify-center gap-x-3 mx-auto pt-6">
                        <Button
                            style={{
                                color: "white",
                            }}
                            primary
                            aria-label="Criar conta"
                        >
                            Criar conta
                        </Button>
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
            </div>
        </div>
    );
}
