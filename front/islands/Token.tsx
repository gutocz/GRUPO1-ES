import { signal } from "@preact/signals";

interface TokenProps {
    token?: string;
    validade?: string;
    time?: string;
}

export const openToken = signal(false);

export default function Token({ token = "1A2B3C", validade = "10/10", time = "20:00" }: TokenProps) {
    return (
        <dialog
            open={openToken.value}
            id="my_modal_1"
            class="modal bg-[#C9AA97]"

        >

            <div class="flex flex-col items-center justify-center bg-gray-100 border border-gray-300 rounded-lg p-6 max-w-xl relative">
                <button onClick={() => openToken.value = !openToken.value} class="text-black font-bold absolute top-2 right-2">X</button>
                <div class="flex items-center justify-center rounded-full w-12 h-12 mb-14 text-black">
                    <h2 class="text-3xl font-semibold">Token ativo</h2>

                    <p class="text-3xl font-semibold">Marmita - janta</p>
                </div>
                <div class="flex flex-row items-center">
                    <div class="bg-gray-200 p-4 rounded-lg mb-4">
                        <svg xmlns="http://www.w3.org/2000/svg" width="278" height="278" viewBox="0 0 278 278" fill="none">
                            <path d="M173.75 69.5C173.75 58.7057 173.75 53.3086 175.513 49.0511C177.864 43.3747 182.375 38.8647 188.051 36.5134C192.309 34.75 197.705 34.75 208.5 34.75C219.295 34.75 224.691 34.75 228.949 36.5134C234.625 38.8647 239.136 43.3747 241.487 49.0511C243.25 53.3086 243.25 58.7057 243.25 69.5C243.25 80.2943 243.25 85.6914 241.487 89.9489C239.136 95.6253 234.625 100.135 228.949 102.487C224.691 104.25 219.295 104.25 208.5 104.25C197.705 104.25 192.309 104.25 188.051 102.487C182.375 100.135 177.864 95.6253 175.513 89.9489C173.75 85.6914 173.75 80.2943 173.75 69.5Z" stroke="black" stroke-width="18" stroke-linejoin="round" />
                            <path d="M34.75 69.5C34.75 58.7057 34.75 53.3086 36.5134 49.0511C38.8647 43.3747 43.3747 38.8647 49.0511 36.5134C53.3086 34.75 58.7057 34.75 69.5 34.75C80.2943 34.75 85.6914 34.75 89.9489 36.5134C95.6253 38.8647 100.135 43.3747 102.487 49.0511C104.25 53.3086 104.25 58.7057 104.25 69.5C104.25 80.2943 104.25 85.6914 102.487 89.9489C100.135 95.6253 95.6253 100.135 89.9489 102.487C85.6914 104.25 80.2943 104.25 69.5 104.25C58.7057 104.25 53.3086 104.25 49.0511 102.487C43.3747 100.135 38.8647 95.6253 36.5134 89.9489C34.75 85.6914 34.75 80.2943 34.75 69.5Z" stroke="black" stroke-width="18" stroke-linejoin="round" />
                            <path d="M34.75 208.5C34.75 197.705 34.75 192.309 36.5134 188.051C38.8647 182.375 43.3747 177.864 49.0511 175.513C53.3086 173.75 58.7057 173.75 69.5 173.75C80.2943 173.75 85.6914 173.75 89.9489 175.513C95.6253 177.864 100.135 182.375 102.487 188.051C104.25 192.309 104.25 197.705 104.25 208.5C104.25 219.295 104.25 224.691 102.487 228.949C100.135 234.625 95.6253 239.136 89.9489 241.487C85.6914 243.25 80.2943 243.25 69.5 243.25C58.7057 243.25 53.3086 243.25 49.0511 241.487C43.3747 239.136 38.8647 234.625 36.5134 228.949C34.75 224.691 34.75 219.295 34.75 208.5Z" stroke="black" stroke-width="18" stroke-linejoin="round" />
                            <path d="M139 34.75V69.5" stroke="black" stroke-width="18" stroke-linecap="round" stroke-linejoin="round" />
                            <path d="M208.5 208.5H173.75" stroke="black" stroke-width="18" stroke-linecap="round" stroke-linejoin="round" />
                            <path d="M243.25 173.75H208.5" stroke="black" stroke-width="18" stroke-linecap="round" stroke-linejoin="round" />
                            <path d="M104.25 139H34.75" stroke="black" stroke-width="18" stroke-linecap="round" stroke-linejoin="round" />
                            <path d="M243.25 139H150.583C144.186 139 139 133.814 139 127.417V104.25" stroke="black" stroke-width="18" stroke-linecap="round" stroke-linejoin="round" />
                            <path d="M167.958 243.25H231.667C238.064 243.25 243.25 238.064 243.25 231.667V208.5" stroke="black" stroke-width="18" stroke-linecap="round" stroke-linejoin="round" />
                            <path d="M139 243.25V187.65V185.333C139 178.936 144.186 173.75 150.583 173.75H173.75" stroke="black" stroke-width="18" stroke-linecap="round" stroke-linejoin="round" />
                        </svg>
                    </div>
                    <div class="text-center ml-14 text-black">
                        <p class="text-3xl font-bold mt-2">{token}</p>
                        <p class="text-lg text-gray-600">
                            <span>Válido até: </span>
                            <span>{time} – {validade}</span>
                        </p>
                    </div>
                </div>
            </div>
        </dialog>
    );
}