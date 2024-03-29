CREATE TABLE TB_SUPORTE (
    ID NUMBER(8) GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT BY 1),
    RE VARCHAR2(8) NOT NULL,
    EMAIL VARCHAR2(250) NOT NULL,
    SOLICITACAO VARCHAR2(25) NOT NULL,
    USER_IP VARCHAR2(48) NOT NULL,
    STATUS CHAR(1) NOT NULL, -- A (Atendido) ou N (Não Atendido)
    CONSTRAINT SUPORTE_PK PRIMARY KEY (ID)
);