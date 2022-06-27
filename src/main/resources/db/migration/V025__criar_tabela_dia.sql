CREATE TABLE TB_DIA(
                     ID NUMBER(8) GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT BY 1),
                     dia_do_mes NUMBER(2) NOT NULL,
                     mes_id NUMBER(8) NOT NULL,
                     turno_id NUMBER(8) NOT NULL,
                     CONSTRAINT dia_pk PRIMARY KEY(id),
                     CONSTRAINT fk_mes FOREIGN KEY(mes_id) REFERENCES TB_MES(id),
                     CONSTRAINT fk_turno_mes FOREIGN KEY(turno_id) REFERENCES TB_TURNO(id)
);
