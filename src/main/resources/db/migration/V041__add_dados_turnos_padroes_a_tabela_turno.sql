INSERT INTO tb_turno_fixo(domingo,segunda,terca,quarta,quinta,sexta,sabado) VALUES(1,1,1,1,1,1,1);
INSERT INTO TB_TURNO(padrao_sistema,nome,sigla,trabalha_no_feriado,hora_inicio,hora_termino,total_horas,turno_fixo_id) VALUES(1,'Feriado','FR',0,'00:00','00:00','00:00',(SELECT MAX(id) FROM tb_turno_fixo));

INSERT INTO tb_turno_fixo(domingo,segunda,terca,quarta,quinta,sexta,sabado) VALUES(1,1,1,1,1,1,1);
INSERT INTO TB_TURNO(padrao_sistema,nome,sigla,trabalha_no_feriado,hora_inicio,hora_termino,total_horas,turno_fixo_id) VALUES(1,'Folga','FG',0,'00:00','00:00','00:00',(SELECT MAX(id) FROM tb_turno_fixo));
