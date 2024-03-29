CREATE TABLE TB_ESPECIALIDADE(
    ID NUMBER(8) GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT BY 1),
    CARGO_ID NUMBER(8) DEFAULT NULL,
    TURNO_ID NUMBER(8) DEFAULT NULL,
    FUNCIONARIO_ID NUMBER(8) DEFAULT NULL,
    DASHBOARD_ID NUMBER(8) DEFAULT NULL,
    CONSTRAINT ESPECIALIDADE_PK PRIMARY KEY (ID),
    CONSTRAINT FK_CARGO_ESPECIALIDADE FOREIGN KEY(CARGO_ID) REFERENCES TB_CARGO(ID),
    CONSTRAINT FK_TURNO_ESPECIALIDADE FOREIGN KEY(TURNO_ID) REFERENCES TB_TURNO(ID),
    CONSTRAINT FK_DASHBOARD FOREIGN KEY(DASHBOARD_ID) REFERENCES TB_DASHBOARD(ID),
    CONSTRAINT FK_FUNCIONARIO_ESPECIALIDADE FOREIGN KEY(FUNCIONARIO_ID) REFERENCES TB_FUNCIONARIO(ID)
);
