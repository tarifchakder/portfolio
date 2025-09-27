import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";
import path from "path";
import { componentTagger } from "lovable-tagger";

// Detect production for GitHub Pages
const isProd = process.env.NODE_ENV === "production";

export default defineConfig({
    base: isProd ? "/portfolio/" : "/", // <-- important
    server: {
        host: "::",
        port: 8080,
    },
    plugins: [
        react(),
        !isProd && componentTagger(),
    ].filter(Boolean),
    resolve: {
        alias: {
            "@": path.resolve(__dirname, "./src"),
        },
    },
});