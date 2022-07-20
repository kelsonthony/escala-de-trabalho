/* 

Modelo de dados do projeto Escala de Trabalho Automatizada.

Implementação de tabelas, triggers, sequences, indexes e constraints em uma 
base de dados Oracle 21c XE.

Versão: 1.0

*/

-- Definindo Sequences:
-- Será criada uma sequence para cada tabela que a chave primária é gerada automaticamente. 
-- Isso substitui a operação de buscar o ID do último registro da tabela e incrementar 
-- em 1 unidade, pois essa solução pode degradar muito a performance do sistema.
-- O padrão de nome utilizado para cada sequence deve conter o nome da tabela que 
-- receberá valores a sua chave primária seguida do sufixo SEQ.
CREATE SEQUENCE [prefixo_tabela]_[nome_tabela]_SEQ MINVALUE 1 MAXVALUE 99999999 START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- Definindo Triggers:
-- Para utilizar as sequences criadas, devermos criar triggers que serão responsáveis por, 
-- antes da inclusão de cada registro na tabela, buscar o NEXTVAL da sequence passando seu 
-- valor para a chave primária da tabela em questão.
-- O nome da trigger receberá o nome da tabela associada procedido do sufixo BI (BEFORE INSERT).
CREATE OR REPLACE TRIGGER [nome_tabela]_BI BEFORE INSERT
  ON [nome_tabela]
  FOR EACH ROW

 DECLARE

 BEGIN
   SELECT [nome_sequence].NEXTVAL
     INTO :NEW.[chave_primária]
     FROM DUAL;

 END [nome_tabela]_BI;

-- Definindo Unique Index:
-- Para garantir a integridade do modelo de dados, devemos em alguns momentos utilizar unique index. 
-- O nome dos índices deve conter o prefixo UK seguido do nome da coluna e o nome da tabela.
-- Se o nome passar de 30 caracteres, o nome da coluna e o nome da tabela devem ser resumidos.
CREATE UNIQUE INDEX UK_[nome_coluna]_[nome_tabela] ON [nome_tabela] TABLESPACE [nome_tablespace];

-- Definindo Check Constraints:
-- Essa verificação de restrições é utilizada quando necessitamos validar a informação de uma determinada coluna. 
-- O nome das check constraints serão iniciandos com CK precedido do nome da coluna e nome da tabela.
-- Por exemplo: Na tabela TB_FUNCIONARIO uma check constraint garante que o conteúdo do campo perfil_funcionario
-- seja limitado as opções "D - Diretor", "G - Gestor" e etc.


-- Identificando as Tabelas do Modelo:
-- Como regra, os nomes das tabelas deverão ser escritos no singular, ser precedidos pelo 
-- identificador TB_ e não deverão ultrapassar 26 caracteres. 
-- Além disso, a tabela deverá estar na tablespace referente à sua subárea.
-- Tablespace TSD_ESCALA para Dados.
-- Tablespace TSI_ESCALA para Indexes.
-- Além disso, a boa prática diz que todas as tabelas devem ter uma breve descrição 
-- sobre sua função em seus comentários. Desta forma, qualquer pessoa que fizer um 
-- describe na estrutura da tabela, saberá qual sua função.






