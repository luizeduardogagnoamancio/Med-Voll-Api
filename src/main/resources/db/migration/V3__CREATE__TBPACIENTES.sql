CREATE TABLE TB_PACIENTES (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    telefone VARCHAR(20),
    email VARCHAR(255),
    cpf VARCHAR(14),
    endereco VARCHAR(255)
);