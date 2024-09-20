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
        label: "Matrícula",
        placeholder: "123456788",
        type: "text",
        id: "matricula-input",
    },
    {
        label: "Senha",
        placeholder: "********",
        type: "password",
        id: "senha-input",
    },
];

export default function Login() {
    return (
        <div class="bg-[#FAF6F1] min-h-screen ">
            <Navbar />
            <div class="bg-white rounded-3xl mx-auto mt-12 min-h-[694px] max-w-[1178px] w-full py-16 px-20">
                <form
                    method="POST"
                    class="w-full max-w-[300px] mx-auto"
                >
                    <div class="w-full grid grid-cols-1 gap-x-6">
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
                            Entrar
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

                <a class="text-ru-orange-500 underline flex justify-center pt-10">Esqueci minha senha</a>
            </div>
        </div>
    );
}
