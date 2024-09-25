export const getCookieValue = (cookieString: string | null, cookieName: string): string | null => {
    if (!cookieString) return null;
    const cookies = cookieString.split("; ");
    const cookie = cookies.find(c => c.startsWith(cookieName + "="));
    return cookie ? cookie.split("=")[1] : null;
}