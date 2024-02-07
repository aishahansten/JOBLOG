FROM node:lts AS build 
WORKDIR /usr/src/app 
COPY package*.json ./
COPY .env ./.env 
RUN npm install  
COPY . .
RUN npm run build
EXPOSE 3000
#RUN npm run start
CMD ["npm", "run", "dev"]