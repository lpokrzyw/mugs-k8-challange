FROM node:18

WORKDIR /home/node/app
COPY package*.json ./

RUN useradd -u 1001 lukasz

RUN npm install
RUN npm ci --omit=dev

COPY --chown=lukasz:lukasz . .

USER lukasz
EXPOSE 8889
CMD [ "node", "app.js" ]


