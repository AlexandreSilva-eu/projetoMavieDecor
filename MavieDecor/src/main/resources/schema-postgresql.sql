CREATE TABLE IF NOT EXISTS cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefone VARCHAR(50) NOT NULL,
    senha VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS categoria_tema (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tema (
    id SERIAL PRIMARY KEY,
    nome_tema VARCHAR(100) NOT NULL,
    thumbnail_path VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tema_categoria (
    tema_id INTEGER REFERENCES tema(id),
    categoria_id INTEGER REFERENCES categoria_tema(id),
    PRIMARY KEY (tema_id, categoria_id)
);
