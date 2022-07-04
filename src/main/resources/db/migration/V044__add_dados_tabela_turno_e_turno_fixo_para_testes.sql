INSERT INTO tb_turno_fixo(domingo,segunda,terca,quarta,quinta,sexta,sabado) VALUES(0,1,1,1,1,1,0);
INSERT INTO TB_TURNO(padrao_sistema,nome,sigla,trabalha_no_feriado,hora_inicio,hora_termino,total_horas,turno_fixo_id) VALUES(1,'Atendimento - (01:00 - 09:00) ','A01',0,'01:00','09:00','08:00',(SELECT MAX(id) FROM tb_turno_fixo));

INSERT INTO tb_turno_fixo(domingo,segunda,terca,quarta,quinta,sexta,sabado) VALUES(0,1,1,1,1,1,1);
INSERT INTO TB_TURNO(padrao_sistema,nome,sigla,trabalha_no_feriado,hora_inicio,hora_termino,total_horas,turno_fixo_id) VALUES(1,'Service Desk - (12:00 - 18:00)','S01',0,'12:00','18:00','06:00',(SELECT MAX(id) FROM tb_turno_fixo));
