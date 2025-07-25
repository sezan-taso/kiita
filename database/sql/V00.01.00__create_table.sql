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
