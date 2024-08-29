-- Criação da tabela ad
CREATE TABLE ad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    author VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);

-- Inserção de dados na tabela ad
INSERT INTO ad (author, title, description) VALUES ('Autor 1', 'Título 1', 'Descrição 1');
INSERT INTO ad (author, title, description) VALUES ('Autor 2', 'Título 2', 'Descrição 2');