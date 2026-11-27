# Etapa 1: Compilación del proyecto Kotlin Multiplatform a WebAssembly
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Instalar libatomic1 necesario para Node.js en Kotlin/Wasm
RUN apt-get update && apt-get install -y libatomic1 && rm -rf /var/lib/apt/lists/*

COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew :shared:wasmJsBrowserDistribution --no-daemon

# Etapa 2: Servidor web ligero con Nginx
FROM nginx:alpine

COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/shared/build/dist/wasmJs/productionExecutable /usr/share/nginx/html

EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]