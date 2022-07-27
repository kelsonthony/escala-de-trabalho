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
- TB_CARGO_FUNCIONARIO
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

- TB_FUNCIONARIO ((pk)matricula,id, nome, localidade, codigo, senha, email, (fk)perfil_funcionario, (fk)cargo_funcionario)
- TB_CARGO_FUNCIONARIO ((pk)id, nome, sigla, permissao_acesso, horas_extras_permitidas)
- TB_FERIADO ((pk)id , nome,tipo, data,dia_semana)
- TB_GRUPO_FUNCIONARIOS ((pk)id, nome)
- TB_HORA_EXTRA((pk)id, mes, ano, total_horas, (fk)funcionario)
- TB_ESCALA_TRABALHO ((pk)id, data, (fk)funcionario)
- TB_TURNO ((pk) id, nome, sigla, hora_inicio,hora_termino,total_horas)
- TB_TURNO_ALTERNADOS ((pk) id, quant_dias_consecutivos_trabalho, quant_dias_folga,trabalha_no_feriado)
- TB_TURNO_FIXO (pk)(id,trabalha_no_feriado,fk_dias_turno)
- TB_DIAS_TURNO_FIXO ((pk)id,segunda,terca,quarta,quinta,sexta,sabado,domingo)
- TB_ESCALA_TRABALHO (id,data,fk_funcionario)

### Padrões e Regras dos campos das tabelas

- TB_FUNCIONARIO
. localidade : Aceita apenas os valores fixos(P,R) -- ( P = Localidade Presencial), ( R = Localidade Remota )
. codigo: Padrão formado pelo valor do campo (sigla) da tabela (TB_CARGO) seguido de no máximo 3 números inteiros não negativos e pelo valor do campo localidade.

- TB_CARGO_FUNCIONARIO
. sigla : Tamanho máximo do campo (10), aceitando apenas letras de A-Z.

- TB_FERIADO
. tipo : Aceita apenas os valores fixos(Federal, Estadual e Municipal).

- TB_TURNO
. sigla: Padão formado por no máximo duas letras de A-Z seguido de no máximo de 3 números inteiros não negativos.

### Ferramentas e tecnologias utilizadas:

- Java 1.8
- Oracle Database 21c
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Maven
- Flyway
- Thymeleaf
- HTML5
- jQuery
- Bootstrap

### - Maven

Comando Maven para instalar a Library do Oracle em nosso projeto:
C:> mvn install:install-file -Dfile=C:\Oracle\app\product\21c\dbhomeXE\jdbc\lib\ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=21.3 -Dpackaging=jar
#### DICA: Alterar o caminho da propriedade -Dfile que aponta para ojdbc8.jar conforme o caminha de sus instalação.

### - Flyway

No arquivo application.properties (src/main/resources/application.properties) acrescentamos as seguintes diretivas para evitar conflitos entre o Flyway e o Hibernate:

spring.jpa.hibernate.ddl-auto=validate

spring.jpa.show-sql=false









