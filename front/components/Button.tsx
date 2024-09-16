import { JSX } from "preact";
import { IS_BROWSER } from "$fresh/runtime.ts";
type Attributes =
  | JSX.HTMLAttributes<HTMLButtonElement>
  | JSX.HTMLAttributes<HTMLAnchorElement>;

function isAnchorProps(
  props: Attributes,
): props is JSX.HTMLAttributes<HTMLAnchorElement> {
  return "href" in props;
}

interface Props {
  primary?: boolean;
  secondary?: boolean;
}

export function Button({
  primary,
  ...props
}: Props & Attributes) {
  const className = primary
    ? "bg-ru-orange-500 rounded-full cursor-pointer leading-[18px] font-bold text-lg px-6 py-[18px] items-center"
    : "text-ru-orange-500 rounded-full leading-[18px] cursor-pointer font-medium text-sm px-6 py-[18px] text-center";

  if (isAnchorProps(props)) {
    return (
      <a
        {...props}
        class={className}
      >
        {props.children}
      </a>
    );
  }

  return (
    <button
      {...props}
      disabled={!IS_BROWSER || props.disabled}
      class={className}
    >
      {props.children}
    </button>
  );
}
