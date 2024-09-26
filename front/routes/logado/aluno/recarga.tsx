import Navbar from "../../../components/header/NavBar.tsx";
import Input from "../../../components/ui/Input.tsx";
import { FreshContext, Handlers, PageProps } from "$fresh/server.ts";
import { getCookieValue } from "../../../sdk/getCookieValue.ts"


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

interface CustomPageProps extends PageProps {
    cookieValue: string | null;
}

const INPUT = {
    label: "Valor:",
    placeholder: "R$ 10,00",
    type: "text",
    name: "valor-recarga",
    required: true,
    id: "recarga-input",
}

export default function Recarga({ data }: CustomPageProps) {

    return (
        <div>
            <Navbar logado={data.cookieValue} saldo={data.data.saldo} />
            <div class="bg-[#FAF6F1] min-h-screen flex pt-20 justify-center">
                <div class="flex gap-8">
                    <div style={{ borderColor: "#F77F00" }} class="bg-white w-[600px] p-6 rounded-lg border max-h-[576px]">
                        <h2 class="font-semibold text-xl mb-4 text-black">
                            Selecione uma forma de pagamento:
                        </h2>
                        <form>
                            <Input {...INPUT} />
                            <div class="mb-4 max-w-[274px]">
                                <select class="border border-orange-400 w-full px-4 py-2 rounded-t-md focus:outline-none text-black focus:ring-2 focus:ring-orange-400  bg-ru-orange-500">
                                    <option class="bg-[#FAF6F1]" value="visa">Visa (Brasil)</option>
                                    <option class="bg-[#FAF6F1]" value="mastercard">
                                        Mastercard
                                    </option>
                                    <option class="bg-[#FAF6F1]" value="paypal">PayPal</option>
                                </select>
                            </div>

                            <div class="flex justify-end">
                                <button type="submit" class="bg-[#ff7f16] text-white px-6 py-3 rounded-md shadow-md hover:bg-[#e6700f] transition">
                                    Continuar
                                </button>
                            </div>
                        </form>
                    </div>

                    <div style={{ borderColor: "#F77F00" }} class="bg-white p-6 rounded-lg border max-h-[150px]">
                        <h2 class="font-bold text-xl mb-4">
                            Formas de pagamento
                        </h2>
                        <div class="flex gap-4">
                            {[
                                "visa",
                                "mastercard",
                                "paypal",
                                "amex",
                                "discover",
                            ].map((icon) => (
                                <div class="border border-orange-400 p-4 rounded-md">
                                    <img
                                        src={`/${icon}.png`}
                                        alt={`${icon} icon`}
                                        class="h-8 w-8"
                                    />
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
