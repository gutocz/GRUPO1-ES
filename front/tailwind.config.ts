import { type Config } from "tailwindcss";
import daisyui from 'daisyui';

export default {
  content: [
    "{routes,islands,components}/**/*.{ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        white: "#FFFFFF",
        black: "#000000",
        ru: {
          orange: {
            "500": "#F77F00",
          },
        },
      },
    },
  },
  plugins: [daisyui],
} satisfies Config;
