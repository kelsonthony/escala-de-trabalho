CREATE TABLE TB_USUARIO (
    ID NUMBER(8) GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT BY 1),
    LOGIN VARCHAR2(125) NOT NULL,
    SENHA VARCHAR2(255) NOT NULL,
    NOME VARCHAR2(125) NOT NULL,
    SOBRENOME VARCHAR2(255) NOT NULL,
    CONSTRAINT USUARIO_PK PRIMARY KEY (ID),
    CONSTRAINT USUARIO_LOGIN_UK UNIQUE (LOGIN)
);