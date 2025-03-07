# Usa Node.js como imagen base para construir la app
FROM node:18 AS build

# Establece el directorio de trabajo
WORKDIR /usr/src/app

# Copia solo los archivos de dependencias primero (para aprovechar la caché de Docker)
COPY package*.json ./

# Instala las dependencias
RUN npm install

# Copia el resto del código fuente
COPY . .

# Construye la aplicación para producción
RUN npm run build

# Segunda etapa: usa una imagen más ligera para servir la app
FROM nginx:alpine

# Copia los archivos estáticos generados por Vite al servidor Nginx
COPY --from=build /usr/src/app/dist /usr/share/nginx/html

# Expone el puerto 80
EXPOSE 80

# Arranca Nginx
CMD ["nginx", "-g", "daemon off;"]