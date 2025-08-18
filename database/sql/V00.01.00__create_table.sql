CREATE TABLE "users"
(
    id           SERIAL PRIMARY KEY,
    login_id     VARCHAR(20) UNIQUE                         NOT NULL,
    password     VARCHAR(255)                               NOT NULL,
    display_name VARCHAR(20)                                NOT NULL,
    del_flag     BOOLEAN   DEFAULT false                    NOT NULL,
    created_at   TIMESTAMP DEFAULT timezone('UTC-9', now()) NOT NULL,
    updated_at   TIMESTAMP DEFAULT timezone('UTC-9', now()) NOT NULL
);

CREATE TABLE "posts"
(
    id          SERIAL PRIMARY KEY,
    author_id   INT                                         NOT NULL,
    title       VARCHAR(100)                                NOT NULL,
    body        TEXT                                        NOT NULL,
    posted_at   TIMESTAMP,
    repost_at   TIMESTAMP,
    is_draft    BOOLEAN     DEFAULT true                    NOT NULL,
    created_at   TIMESTAMP DEFAULT timezone('UTC-9', now()) NOT NULL,
    updated_at   TIMESTAMP DEFAULT timezone('UTC-9', now()) NOT NULL,
    FOREIGN KEY (author_id) references users (id)
);
