# Escala de Trabalho Automatizada

Sistema de escala de trabalho automática 1.0


## Escopo e requisitos:

- Manter o histórico mensal da escala de trabalho.
- Manter histórico das horas extras.
- Manter um cadastro atualizado de feriados (possivelmente automático).
- Controlar a quantidade de horas extras, por funcionário.


- A administração do sistema deverá ser efetuada apenas por usuários autorizados.
- Permitir configurar o tipo de escala de trabalho.
- Controlar a identificação do perfil dos usuários.
- Manter o histórico de uso do sistema (Log).


- Permitir criar e gerenciar grupos de funcionarios.
- Permitir gerar automaticamente escalas de todos os funcionários pertencente a um determinado grupo.
- Permitir gerar escalas individualmente para os funcionários.
- Permitir ajustar manualmente as escalas de trabalho, feito pelo perfil do gestor.
- Fazer a distribuição igualitária da carga de trabalho.
- Permitir replica escalas para os próximos messes, a escolha do período feita usuário.


### MER (Modelo de Entidade e Relacionamento) Físico:

* Modelamos até a 3º Forma Normal para um banco de dados Oracle 21c.
* Utilizamos a notação IDEF1X (Integration Definition for Information Modeling).

- TB_FUNCIONARIO
- TB_PERFIL_FUNCIONARIO
- TB_FERIADO
- TB_ESCALA_TRABALHO
- TB_HORA_EXTRA
- TB_TURNO_FIXO
- TB_TURNO_ALTERNADOS
- TB_CARGA_TRABALHO (???)
- TB_LOG
- TB_GRUPO_FUNCIONARIOS
- TB_ESCALA_TRABALHO
- TB_MES
- TB_DIA_MES



### Campos das Tabelas:

- TB_FUNCIONARIO ((pk)matricula,id, nome, senha, email, (fk)perfil_funcionario, (fk)cargo_funcionario)


- TB_PERFIL_FUNCIONARIO ((pk)id, nome, sigla, horas_extras_permitidas)
- TB_FERIADO ((pk)id , nome,tipo, data,dia_semana)
- TB_GRUPO_FUNCIONARIOS ((pk)id, nome)
- TB_HORA_EXTRA((pk)id, mes, ano, total_horas, (fk)funcionario)


- TB_ESCALA_TRABALHO ((pk)id, data, (fk)funcionario)


- TB_TURNO ((pk) id, nome, sigla, hora_inicio,hora_termino,total_horas)


- TB_TURNO_ALTERNADOS ((pk) id, quant_dias_consecutivos_trabalho, quant_dias_folga,trabalha_no_feriado)


- TB_TURNO_FIXO (pk)(id,trabalha_no_feriado,fk_dias_turno)
- TB_DIAS_TURNO_FIXO ((pk)id,segunda,terca,quarta,quinta,sexta,sabado,domingo)


- TB_ESCALA_TRABALHO (id,data,fk_funcionario)


### Ferramentas utilizadas:

- Oracle Database 21c
- Erwin 7.3
- Java 1.8
- Spring Framework
- Spring Boot
- Spring Web MVC
- Spring Security







