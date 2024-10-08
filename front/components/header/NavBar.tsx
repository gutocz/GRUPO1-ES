import { Button } from "../ui/Button.tsx";


export default function Navbar({ logado, saldo, isFuncionario }: { logado?: string, saldo?: string, isFuncionario?: boolean }) {
    let isAlunoLogado = false
    if (logado?.length) {
        isAlunoLogado = !isAlunoLogado
    }

    return (
        <>
            <nav
                style={{ borderColor: "#e2e2e2" }}
                class="bg-white h-20 sm:h-28 sm:px-24 items-center flex w-full border border-b"
            >
                <div class="hidden sm:flex w-full h-16 items-center justify-between">
                    <div class="flex flex-1 items-center justify-center sm:justify-start">
                        <div class="flex flex-shrink-0 items-center">
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="84"
                                height="46"
                                viewBox="0 0 84 46"
                                fill="none"
                            >
                                <path
                                    d="M12.7775 45H0.37125V0H22.4963C24.5796 0 26.59 0.25 28.5275 0.75C30.465 1.22917 32.1733 2 33.6525 3.0625C35.1525 4.10417 36.34 5.45833 37.215 7.125C38.1108 8.79167 38.5588 10.8125 38.5588 13.1875C38.5588 15.0417 38.1629 16.7917 37.3713 18.4375C36.6004 20.0625 35.5588 21.5208 34.2463 22.8125C32.9546 24.0833 31.465 25.1458 29.7775 26C28.09 26.8333 26.34 27.3854 24.5275 27.6562C25.34 29.0312 26.1838 30.3333 27.0588 31.5625C27.9546 32.7917 28.8504 33.9479 29.7463 35.0312C30.6629 36.0938 31.5692 37.0833 32.465 38C33.3608 38.8958 34.215 39.7188 35.0275 40.4688C36.9442 42.2188 38.84 43.7292 40.715 45H25.2463C23.9963 43.8125 22.8088 42.3854 21.6838 40.7188C20.7254 39.3021 19.7567 37.5521 18.7775 35.4688C17.8192 33.3646 17.0796 30.9375 16.5588 28.1875H12.7775V45ZM25.965 13.1875C25.965 10.1667 25.2254 7.94792 23.7463 6.53125C22.2879 5.11458 20.09 4.40625 17.1525 4.40625H12.7775V23.9375H17.1525C18.715 23.9375 20.0483 23.6458 21.1525 23.0625C22.2775 22.4583 23.1942 21.6667 23.9025 20.6875C24.6108 19.6875 25.1317 18.5417 25.465 17.25C25.7983 15.9583 25.965 14.6042 25.965 13.1875ZM55.9062 0C55.3438 1.6875 54.8542 3.4375 54.4375 5.25C54.0208 7.04167 53.6771 8.84375 53.4062 10.6562C53.1354 12.4479 52.9271 14.2188 52.7812 15.9688C52.6562 17.7188 52.5938 19.375 52.5938 20.9375C52.5938 22.0833 52.5938 23.375 52.5938 24.8125C52.6146 26.2292 52.6979 27.6771 52.8438 29.1562C53.0104 30.6354 53.2708 32.0833 53.625 33.5C53.9792 34.8958 54.5 36.1458 55.1875 37.25C55.8958 38.3542 56.7917 39.2396 57.875 39.9062C58.9792 40.5729 60.3438 40.9062 61.9688 40.9062C63.4062 40.9062 64.6354 40.6667 65.6562 40.1875C66.6979 39.6875 67.5729 39.0208 68.2812 38.1875C69.0104 37.3542 69.5833 36.3958 70 35.3125C70.4375 34.2292 70.7708 33.0938 71 31.9062C71.25 30.7188 71.4062 29.5208 71.4688 28.3125C71.5521 27.0833 71.5938 25.9167 71.5938 24.8125V0H84V22.875C84 26.1458 83.4792 29.1875 82.4375 32C81.3958 34.8125 79.9271 37.2604 78.0312 39.3438C76.1354 41.4062 73.8438 43.0312 71.1562 44.2188C68.4688 45.3854 65.4896 45.9688 62.2188 45.9688C59.9688 45.9688 57.875 45.6458 55.9375 45C54 44.375 52.2188 43.5104 50.5938 42.4062C48.9896 41.2812 47.5521 39.9583 46.2812 38.4375C45.0312 36.8958 43.9792 35.2188 43.125 33.4062C42.2917 31.5938 41.6562 29.6875 41.2188 27.6875C40.7812 25.6667 40.5833 23.625 40.625 21.5625V20.9375C40.6458 19.2708 40.8125 17.5521 41.125 15.7812C41.4375 13.9896 41.8542 12.1979 42.375 10.4062C42.9167 8.61458 43.5625 6.84375 44.3125 5.09375C45.0625 3.32292 45.8958 1.625 46.8125 0H55.9062Z"
                                    fill="#F77F00"
                                />
                            </svg>
                        </div>
                        <div class="hidden sm:ml-6 sm:flex gap-6 items-center px-16">
                            {!isFuncionario && !isAlunoLogado && <a
                                href="/"
                                class="text-ru-orange-500 text-lg "
                                aria-current="page"
                            >
                                Home
                            </a>}
                            {isAlunoLogado && !isFuncionario && (
                                <a href="/logado/aluno/recarga" class="text-ru-orange-500 text-lg underline">
                                    Fazer recarga
                                </a>
                            )}
                            {isAlunoLogado && !isFuncionario && <a href={`/logado/aluno/${logado}`} class="text-ru-orange-500 text-lg underline">
                                Comidas
                            </a>}
                            {isFuncionario && <a href="/" class="text-ru-orange-500 text-lg underline">
                                Home
                            </a>
                            }
                            {isFuncionario && <a href="/logado/funcionario/criarCardapio" class="text-ru-orange-500 text-lg underline">
                                Modificar Cardapios
                            </a>
                            }
                            {isFuncionario && <a href={`/logado/funcionario/12345678912`} class="text-ru-orange-500 text-lg underline">
                                Pratos disponível
                            </a>}

                        </div>
                    </div>
                    {!isFuncionario && !isAlunoLogado &&
                        (
                            <div class="flex flex-row gap-x-6">
                                <Button
                                    style={{
                                        borderWidth: "1px",
                                        borderColor: "#D4D2E3",
                                    }}
                                    secondary
                                    aria-label="Login"
                                    href="/loginAluno"
                                >
                                    Login
                                </Button>
                                <Button
                                    primary
                                    aria-label="Cadastro"
                                    href="/cadastro"
                                    style={{ color: "white" }}
                                >
                                    Cadastro
                                </Button>
                            </div>
                        )}
                    {!isFuncionario && isAlunoLogado && (
                        <div class="flex items-center">
                            <span class="text-ru-orange-500 text-lg mr-5">
                                Saldo: R$ {saldo ?? "00,00"}
                            </span>
                            <a href="/logado/aluno/perfil" class="bg-ru-orange-500 px-4 flex flex-col pb-2 rounded-[20px]">
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    width="28"
                                    height="29"
                                    viewBox="0 0 28 29"
                                    fill="none"
                                    class="ml-1"
                                >
                                    <mask
                                        id="mask0_294_294"
                                        style="mask-type:luminance"
                                        maskUnits="userSpaceOnUse"
                                        x="4"
                                        y="16"
                                        width="20"
                                        height="10"
                                    >
                                        <path
                                            fill-rule="evenodd"
                                            clip-rule="evenodd"
                                            d="M4.66663 16.9867H23.1465V25.5897H4.66663V16.9867Z"
                                            fill="white"
                                        />
                                    </mask>
                                    <g mask="url(#mask0_294_294)">
                                        <path
                                            fill-rule="evenodd"
                                            clip-rule="evenodd"
                                            d="M13.9077 18.7367C8.9365 18.7367 6.4165 19.5907 6.4165 21.2765C6.4165 22.9775 8.9365 23.8397 13.9077 23.8397C18.8777 23.8397 21.3965 22.9857 21.3965 21.2999C21.3965 19.5989 18.8777 18.7367 13.9077 18.7367ZM13.9077 25.5897C11.6222 25.5897 4.6665 25.5897 4.6665 21.2765C4.6665 17.4312 9.941 16.9867 13.9077 16.9867C16.1932 16.9867 23.1465 16.9867 23.1465 21.2999C23.1465 25.1452 17.8732 25.5897 13.9077 25.5897Z"
                                            fill="white"
                                        />
                                    </g>
                                    <mask
                                        id="mask1_294_294"
                                        style="mask-type:luminance"
                                        maskUnits="userSpaceOnUse"
                                        x="7"
                                        y="2"
                                        width="14"
                                        height="13"
                                    >
                                        <path
                                            fill-rule="evenodd"
                                            clip-rule="evenodd"
                                            d="M7.71143 2.40808H20.1015V14.7964H7.71143V2.40808Z"
                                            fill="white"
                                        />
                                    </mask>
                                    <g mask="url(#mask1_294_294)">
                                        <path
                                            fill-rule="evenodd"
                                            clip-rule="evenodd"
                                            d="M13.9076 4.07359C11.4098 4.07359 9.37746 6.10476 9.37746 8.60259C9.3693 11.0923 11.3865 13.1223 13.8738 13.1316L13.9076 13.9646V13.1316C16.4043 13.1316 18.4355 11.0993 18.4355 8.60259C18.4355 6.10476 16.4043 4.07359 13.9076 4.07359ZM13.9076 14.7964H13.8703C10.4613 14.7859 7.6998 12.0058 7.71146 8.59909C7.71146 5.18659 10.4905 2.40759 13.9076 2.40759C17.3236 2.40759 20.1015 5.18659 20.1015 8.60259C20.1015 12.0186 17.3236 14.7964 13.9076 14.7964Z"
                                            fill="white"
                                        />
                                    </g>
                                </svg>
                                <span class="text-white">Perfil</span>
                            </a>
                        </div>

                    )}
                    {isFuncionario && <a href="/logado/aluno/perfil" class="bg-ru-orange-500 px-4 flex flex-col pb-2 rounded-[20px]">
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="28"
                            height="29"
                            viewBox="0 0 28 29"
                            fill="none"
                            class="ml-1"
                        >
                            <mask
                                id="mask0_294_294"
                                style="mask-type:luminance"
                                maskUnits="userSpaceOnUse"
                                x="4"
                                y="16"
                                width="20"
                                height="10"
                            >
                                <path
                                    fill-rule="evenodd"
                                    clip-rule="evenodd"
                                    d="M4.66663 16.9867H23.1465V25.5897H4.66663V16.9867Z"
                                    fill="white"
                                />
                            </mask>
                            <g mask="url(#mask0_294_294)">
                                <path
                                    fill-rule="evenodd"
                                    clip-rule="evenodd"
                                    d="M13.9077 18.7367C8.9365 18.7367 6.4165 19.5907 6.4165 21.2765C6.4165 22.9775 8.9365 23.8397 13.9077 23.8397C18.8777 23.8397 21.3965 22.9857 21.3965 21.2999C21.3965 19.5989 18.8777 18.7367 13.9077 18.7367ZM13.9077 25.5897C11.6222 25.5897 4.6665 25.5897 4.6665 21.2765C4.6665 17.4312 9.941 16.9867 13.9077 16.9867C16.1932 16.9867 23.1465 16.9867 23.1465 21.2999C23.1465 25.1452 17.8732 25.5897 13.9077 25.5897Z"
                                    fill="white"
                                />
                            </g>
                            <mask
                                id="mask1_294_294"
                                style="mask-type:luminance"
                                maskUnits="userSpaceOnUse"
                                x="7"
                                y="2"
                                width="14"
                                height="13"
                            >
                                <path
                                    fill-rule="evenodd"
                                    clip-rule="evenodd"
                                    d="M7.71143 2.40808H20.1015V14.7964H7.71143V2.40808Z"
                                    fill="white"
                                />
                            </mask>
                            <g mask="url(#mask1_294_294)">
                                <path
                                    fill-rule="evenodd"
                                    clip-rule="evenodd"
                                    d="M13.9076 4.07359C11.4098 4.07359 9.37746 6.10476 9.37746 8.60259C9.3693 11.0923 11.3865 13.1223 13.8738 13.1316L13.9076 13.9646V13.1316C16.4043 13.1316 18.4355 11.0993 18.4355 8.60259C18.4355 6.10476 16.4043 4.07359 13.9076 4.07359ZM13.9076 14.7964H13.8703C10.4613 14.7859 7.6998 12.0058 7.71146 8.59909C7.71146 5.18659 10.4905 2.40759 13.9076 2.40759C17.3236 2.40759 20.1015 5.18659 20.1015 8.60259C20.1015 12.0186 17.3236 14.7964 13.9076 14.7964Z"
                                    fill="white"
                                />
                            </g>
                        </svg>
                        <span class="text-white">Perfil</span>
                    </a>}
                </div>
            </nav>
        </>
    );
}
