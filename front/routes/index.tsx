import Banner from "../components/content/Banner.tsx";
import Navbar from "../components/header/NavBar.tsx";
import { FreshContext, Handlers, PageProps } from "$fresh/server.ts";
import { getCookieValue } from "../sdk/getCookieValue.ts"

interface CustomPageProps extends PageProps {
  cookieValue: string | null;
}

export const handler: Handlers = {
  async GET(req, ctx: FreshContext) {
    const cookieValue = getCookieValue(req.headers.get("cookie"), "userType");

    if (!cookieValue) {
      return Response.redirect(new URL("/loginAluno", req.url), 303);
    }

    return ctx.render({ cookieValue });
  },
};

export default function Home({ data }: CustomPageProps) {
  return (
    <>
      <Navbar logado={data.cookieValue} />
      <Banner />
    </>
  );
}
