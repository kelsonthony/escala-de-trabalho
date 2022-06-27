CREATE TABLE TB_MES(
                     ID NUMBER(8) GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT BY 1),
                     numero_mes NUMBER(2) NOT NULL,
                     escala_trabalho_id NUMBER(8) NOT NULL,
                     total_horas_normais TIMESTAMP,
                     total_horas_extras TIMESTAMP,
                     sabados_trabalhados NUMBER(1),
                     domingos_trabalhados NUMBER(1),
                     feriados_trabalhados NUMBER(1),
                     CONSTRAINT mes_pk PRIMARY KEY(id),
                     CONSTRAINT fk_escala_trabalho_mes FOREIGN KEY(escala_trabalho_id) REFERENCES TB_ESCALA_TRABALHO(id)
);
