CREATE TABLE IF NOT EXISTS account
(
    id      INTEGER      NOT NULL,
    user    VARCHAR(255) NOT NULL,
    balance BIGINT       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS transaction
(
    id           INTEGER   NOT NULL,
    time         TIMESTAMP NOT NULL,
    srcAccountId INTEGER   NOT NULL,
    dstAccountId INTEGER   NOT NULL,
    amount       BIGINT    NOT NULL,
    PRIMARY KEY (id)
);

