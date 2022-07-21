INSERT INTO TB_USUARIO(LOGIN, SENHA, NOME, SOBRENOME) VALUES('user', '$2a$10$Z3ZMCO65/.b4p6yZA23eP.8Pt/5sz0IdoiqICYCbfw7V.FNzX554e', 'Usuário', 'Padrão do Sistema');
INSERT INTO TB_USUARIO(LOGIN, SENHA, NOME, SOBRENOME) VALUES('editor', '$2a$10$Z3ZMCO65/.b4p6yZA23eP.8Pt/5sz0IdoiqICYCbfw7V.FNzX554e', 'Editor', 'Padrão do Sistema');
INSERT INTO TB_USUARIO(LOGIN, SENHA, NOME, SOBRENOME) VALUES('admin', '$2a$10$Z3ZMCO65/.b4p6yZA23eP.8Pt/5sz0IdoiqICYCbfw7V.FNzX554e', 'Administrador', 'Padrão do Sistema');

INSERT INTO TB_PERFIL(NOME) VALUES('USER');
INSERT INTO TB_PERFIL(NOME) VALUES('EDITOR');
INSERT INTO TB_PERFIL(NOME) VALUES('ADMIN');

INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_DASHBOARD');
INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_GERENCIAMENTO');
INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_LOG');
INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_USUARIO');
INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_PERFIL');

INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_FUNCIONARIO');
INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_FUNCIONARIO_LISTAR');

INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_CARGO');
INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_CARGO_LISTAR');

INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_EQUIPE');
INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_EQUIPE_LISTAR');

INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_ESCALA');
INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_ESCALA_LISTAR');
INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_ESCALA_LISTAR_TIPOS');

INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_TURNO');
INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_TURNO_LISTAR');

INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_FERIADO');
INSERT INTO TB_AUTORIZACAO(NOME) VALUES('ROLE_FERIADO_LISTAR');

INSERT INTO TB_PERFIL_USUARIO(PERFIL_ID, USUARIO_ID)
SELECT p.ID, u.ID
FROM TB_PERFIL p, TB_USUARIO u
WHERE u.LOGIN = 'user'
AND p.NOME = 'USER';

INSERT INTO TB_PERFIL_USUARIO(PERFIL_ID, USUARIO_ID)
SELECT p.ID, u.ID
FROM TB_PERFIL p, TB_USUARIO u
WHERE u.LOGIN = 'editor'
AND p.NOME = 'EDITOR';

INSERT INTO TB_PERFIL_USUARIO(PERFIL_ID, USUARIO_ID)
SELECT p.ID, u.ID
FROM TB_PERFIL p, TB_USUARIO u
WHERE u.LOGIN = 'admin'
AND p.NOME = 'ADMIN';

INSERT INTO TB_AUTORIZACAO_PERFIL(AUTORIZACAO_ID, PERFIL_ID)
SELECT a.ID, p.ID
FROM TB_AUTORIZACAO a, TB_PERFIL p
WHERE p.NOME = 'USER'
AND a.NOME IN ('ROLE_DASHBOARD',
            'ROLE_PERFIL',
            'ROLE_FUNCIONARIO_LISTAR',
            'ROLE_CARGO_LISTAR',
            'ROLE_EQUIPE_LISTAR',
            'ROLE_ESCALA_LISTAR',
            'ROLE_ESCALA_LISTAR_TIPOS',
            'ROLE_TURNO_LISTAR',
            'ROLE_FERIADO_LISTAR');

INSERT INTO TB_AUTORIZACAO_PERFIL(AUTORIZACAO_ID, PERFIL_ID)
SELECT a.ID, p.ID
FROM TB_AUTORIZACAO a, TB_PERFIL p
WHERE p.NOME = 'EDITOR'
AND a.NOME IN ('ROLE_DASHBOARD',
            'ROLE_GERENCIAMENTO',
            'ROLE_PERFIL',
            'ROLE_FUNCIONARIO',
            'ROLE_CARGO',
            'ROLE_EQUIPE',
            'ROLE_ESCALA',
            'ROLE_TURNO',
            'ROLE_FERIADO');

INSERT INTO TB_AUTORIZACAO_PERFIL(AUTORIZACAO_ID, PERFIL_ID)
SELECT a.ID, p.ID
FROM TB_AUTORIZACAO a, TB_PERFIL p
WHERE p.NOME = 'ADMIN';