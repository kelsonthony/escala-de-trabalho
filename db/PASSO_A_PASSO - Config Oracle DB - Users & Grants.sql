/* 

Configurar o Oracle Database 21c para o projeto Escala de Trabalho Automatizada.

Criar usuários, conceder privilégios, PDBs e Tablespaces.

Versão: 1.0

*/

-- Acessar o SQL Developer, logar com o usuário SYS e seguir os passos:

-- Passo 1 : Alternar do CDB CDB$ROOT para o PDB XEPDB1
ALTER SESSION SET container = xepdb1;

-- Passo 2 : Criar Tablespace para armazenar Tabelas do Usuario
CREATE TABLESPACE 
TS_ESCALA
DATAFILE 'TS_ESCALA_DATA.dbf' SIZE 1M ONLINE;

-- Passo 3 : Criar usuario USR_ESCALA
CREATE USER USR_ESCALA IDENTIFIED BY Q1ntess;

-- Passo 4 : Conceder privilegios para permitir que o usuario efetue login
-- Voce precisa conceder a ele o privilegio de criar sessao e tabelas
GRANT CREATE SESSION TO USR_ESCALA;
GRANT CREATE TABLE TO USR_ESCALA;

-- Passo 5 : Criar tabela de teste com o usuario USR_ESCALA
-- Conectar no sqlplus via prompt do Windows ou terminal, com o comando:
sqlplus USR_ESCALA@//localhost:1521/xepdb1

-- Após conectado, rodar o seguinte comando:
CREATE TABLE TB_TESTE
(
CODIGO NUMBER(8),
NOME VARCHAR2(50)
)TABLESPACE TS_ESCALA ;

-- Passo 6 : Para trabalhar com a tabela criada devemos ajustar os 
-- Privilegios / Permissões do usuário USR_ESCALA, para isso, 
-- seguimos mais esses passos:

-- Verificar a Tablespace padrao do usuario USR_ESCALA
SELECT  default_tablespace FROM dba_users
WHERE  username = 'USR_ESCALA';

/* Resultado 
DEFAULT_TABLESPACE   
USERS 
*/

-- Passo 7 : Conceder privilegios nas tablespaces USERS e TS_ESCALA
ALTER USER USR_ESCALA QUOTA UNLIMITED ON USERS;
ALTER USER USR_ESCALA QUOTA UNLIMITED ON TS_ESCALA;

-- Passo 8 : Conceder outros privilegios para criar view, procedure e sequence
GRANT CREATE view, CREATE procedure, CREATE sequence TO USR_ESCALA;

-- Com essas configurações realizadas o ambiente para desenvolvimento do sistema
-- de Escala de Trabalho Automática já deve estar configurado. 
-- Para testá-lo, ainda no SQLPlus do terminal, vamos realizar os seguintes comandos:

-- Passo 9 : Inserir dados na tabela de teste que criamos TB_TESTE:
INSERT INTO TB_TESTE VALUES ( 1, 'Primeiro Teste' );

-- Outros comandos no SQLplus - Realizar consulta:
SELECT * FROM TB_TESTE;

-- Retornando no SQL Developer, podemos listar as informações de PBD e TABLESPACE
-- Passo 10 : Consultar usuarios no banco PDB
SELECT 
    username, 
    default_tablespace, 
    profile, 
    authentication_type
FROM
    dba_users
WHERE 
    account_status = 'OPEN';

-- Passo 11 : Verificar Objetos da TABLESPACE TS_ESCALA
SELECT
    OWNER,
    TABLE_NAME,
    TABLESPACE_NAME
FROM
    ALL_TABLES
Where TABLESPACE_NAME = 'TS_ESCALA';

-- Passo 12 : Por fim, para termos o ambiente limpo, no SQL Plus, devemos apagar a TB_TESTE
DROP TABLE TB_TESTE;