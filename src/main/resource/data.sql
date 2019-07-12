CREATE TABLE account
(
    'id'      INTEGER      NOT NULL,
    'user'    VARCHAR(255) NOT NULL,
    'balance' BIGINT       NOT NULL,
    PRIMARY KEY ('id')
);

CREATE TABLE transaction
(
    'id'     INTEGER NOT NULL,
    'time'   TIME    NOT NULL,
    'src'    INTEGER NOT NULL,
    'dst'    INTEGER NOT NULL,
    'amount' BIGINT  NOT NULL,
    PRIMARY KEY ('id')
);

