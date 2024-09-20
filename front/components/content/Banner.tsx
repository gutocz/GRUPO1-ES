import { Button } from "../ui/Button.tsx";

export default function Banner() {
    return (
        <>
            <div class="relative">
                <img
                    src="./Banner.png"
                    height={906}
                    class="w-full h-full object-cover"
                />
                <div class="absolute top-[226px] left-24 hidden lg:block max-w-[590px] w-full max-h-96 h-full bg-black bg-opacity-70 rounded-[40px] px-5 py-3">
                    <h1 class="text-white font-bold text-[56px] leading-[66px]">
                        O restaurante universitário, para o universitário.
                    </h1>
                    <p class="text-lg leading-[30px] text-white">
                        Agora todas as informações que você queria saber sobre o
                        RU, na palma da sua mão
                    </p>
                    <div class="flex flex-row gap-x-6 pt-11">
                        <Button
                            primary
                            aria-label="Cadastro"
                            href="/cadastro"
                            style={{ color: "", backgroundColor: "white" }}
                        >
                            <div class="flex flex-row justify-between">
                                <span class="text-ru-orange-500 font-normal mr-2">Cadastro</span>

                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    width="18"
                                    height="18"
                                    viewBox="0 0 18 18"
                                    fill="none"
                                >
                                    <path
                                        d="M9.4043 2.6543L15.7499 8.99995L9.4043 15.3456"
                                        stroke="#F77F00"
                                        stroke-width="1.5"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                    />
                                    <path
                                        d="M15.7499 9L2.25 9"
                                        stroke="#F77F00"
                                        stroke-width="1.5"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                    />
                                </svg>
                            </div>
                        </Button>
                        <Button
                            style={{
                                borderWidth: "1px",
                                borderColor: "#D4D2E3",
                                color: "white",
                            }}
                            secondary
                            aria-label="Login"
                            href="/login"
                        >
                            Login
                        </Button>
                    </div>
                </div>
            </div>
        </>
    );
}
