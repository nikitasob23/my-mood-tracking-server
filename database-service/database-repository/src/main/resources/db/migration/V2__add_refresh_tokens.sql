CREATE TABLE refresh_tokens(
    user_id BIGINT,
    token   VARCHAR(255),
    CONSTRAINT refresh_tokens_pk PRIMARY KEY (user_id),
    CONSTRAINT refresh_tokens_user_id_fk FOREIGN KEY (user_id) REFERENCES usrs (id)
);