import Container from "../components/content/Container.tsx";
import Navbar from "../components/header/NavBar.tsx";
import Input from "../components/ui/Input.tsx";
import { Button } from "../components/ui/Button.tsx";

const INPUTS = [
    {
        label: "Senha",
        placeholder: "*******",
        type: "password",
        id: "senha-nova-input",
        name: "novasenha",
        required: true,
    },
    {
        label: "Confirmar senha",
        placeholder: "******",
        type: "password",
        id: "senha-nova-confirmacao-input",
        name: "confirmarsenha",
        required: true,
    }
];

export default function NovaSenha() {
    return (<div class="bg-[#FAF6F1] min-h-screen ">
        <Navbar />
        <Container>
            <h2 class="text-ru-orange-500 text-2xl font-bold w-full text-center mb-8">Digite sua nova senha</h2>
           
            <form
                method="POST"
                class="w-full max-w-[256px] mx-auto flex flex-col justify-center"
                id="teste"
            >
                <div class="w-full grid grid-cols-1 gap-x-6">
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
                        Confirmar
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

        </Container>
    </div>)
}