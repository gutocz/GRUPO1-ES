import { type Config } from "tailwindcss";

export default {
  content: [
    "{routes,islands,components}/**/*.{ts,tsx}",
  ],
  theme: {
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
} satisfies Config;
