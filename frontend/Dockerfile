### base ###
FROM node:20-slim as node
FROM ubuntu:focal-20220531 as base
ENV NODE_ENV=production
ENV TZ=Asia/Tokyo
ENV LANG ja_JP.UTF-8
ENV LANGUAGE ja_JP:ja_JP

RUN apt-get update && apt-get -qq install -y --no-install-recommends tini tzdata && rm -rf /var/lib/apt/lists/*
EXPOSE 3000

COPY --from=node /usr/local/include/ /usr/local/include/
COPY --from=node /usr/local/lib/ /usr/local/lib/
COPY --from=node /usr/local/bin/ /usr/local/bin/
RUN corepack prepare yarn@1.22.22 --activate
RUN corepack disable && corepack enable

RUN groupadd --gid 1000 node \
    && useradd --uid 1000 --gid node --shell /bin/bash --create-home node \
    && mkdir /app \
    && chown -R node:node /app

WORKDIR /app
USER node
COPY --chown=node:node package*.json yarn*.lock ./
RUN yarn install --immutable --immutable-cache --check-cache && yarn cache clean

FROM base as dev
WORKDIR /apps
ENV NODE_ENV=development
# SSL問題が解消したらopenssl-legacy-providerを削除
ENV NODE_OPTIONS --openssl-legacy-provider
ENV PATH=/app/node_modules/.bin:$PATH
USER root

# RUN npm install --only=development && npm cache clean --force
CMD yarn install --immutable --immutable-cache --check-cache && yarn cache clean && yarn dev