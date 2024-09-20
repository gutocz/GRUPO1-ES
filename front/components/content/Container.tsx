import { ComponentChildren } from "preact";

export default function Container(
    { children }: { children: ComponentChildren },
) {
    return (
        <div class="bg-white rounded-3xl mx-auto mt-12 min-h-[694px] max-w-[1178px] w-full py-16 px-20 ">
            {children}
        </div>
    );
}
