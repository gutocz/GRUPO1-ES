interface Props {
    label: string;
    placeholder: string;
    type: string;
    id: string;
    name: string;
    required: boolean;
}

export default function Input(
    { label, placeholder, type, id, name, required }: Props,
) {
    return (
        <div class="pb-5">
            <label
                htmlFor={id}
                class="text-lg font-bold block mb-3 text-ru-orange-500 "
            >
                {label}
            </label>

            <input
                type={type}
                id={id}
                placeholder={placeholder}
                class="bg-[#FAF6F1] px-5 w-full min-h-14 lg:min-h-[72px] text-base rounded-[50px] outline-none"
                name={name}
                required={required}
            />
        </div>
    );
}
