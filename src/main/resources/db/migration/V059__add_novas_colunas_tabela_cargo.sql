DELETE FROM TB_CARGO;

ALTER TABLE TB_CARGO ADD (
  carga_horaria_dia FLOAT NOT NULL,
  carga_horaria_mes FLOAT
);


