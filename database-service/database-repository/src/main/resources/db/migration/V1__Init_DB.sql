CREATE TABLE usrs(
    id          BIGINT,
    name        VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    active      BIT          NOT NULL,
    non_locked  BIT(1)       NOT NULL,
    creation    DATETIME(6)  DEFAULT NULL,
    last_visit  DATETIME(6)  DEFAULT NULL,
    CONSTRAINT usrs_pk PRIMARY KEY (id)
);

CREATE TABLE usrs_seq(next_val BIGINT, CONSTRAINT usrs_seq_pk PRIMARY KEY (next_val));
INSERT INTO usrs_seq VALUES ( 1 );

CREATE TABLE mood_entries(
    id      BIGINT,
    degree  INTEGER NOT NULL,
    node    VARCHAR(2048),
    user_id BIGINT NOT NULL,
    CONSTRAINT mood_entries_pk PRIMARY KEY (id),
    CONSTRAINT mood_entries_user_id_fk FOREIGN KEY (user_id) REFERENCES usrs (id)
);

CREATE TABLE mood_entries_seq(next_val BIGINT, CONSTRAINT mood_entries_seq_pk PRIMARY KEY (next_val));
INSERT INTO mood_entries_seq VALUES ( 1 );

CREATE TABLE user_roles(
    user_id BIGINT,
    role   VARCHAR(15),
    CONSTRAINT user_roles_pk PRIMARY KEY (user_id, role),
    CONSTRAINT user_roles_user_id_fk FOREIGN KEY (user_id) REFERENCES usrs (id)
);