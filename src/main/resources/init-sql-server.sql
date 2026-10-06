-- ========================================
-- Script de Inicialização SQL Server
-- Projeto: CP1-API-ANIMAL - Check Point 2
-- ========================================

-- Criar banco de dados (se não existir)
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'apiAnimais')
BEGIN
    CREATE DATABASE apiAnimais;
END
GO

-- Usar o banco de dados
USE apiAnimais;
GO

-- Criar tabela TipoAnimal
IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'tipo_animal')
BEGIN
    CREATE TABLE tipo_animal (
        id BIGINT PRIMARY KEY IDENTITY(1,1),
        nome VARCHAR(100) NOT NULL,
        descricao VARCHAR(255)
    );
    PRINT 'Tabela tipo_animal criada com sucesso.';
END
GO

-- Criar tabela Animais
IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'animais')
BEGIN
    CREATE TABLE animais (
        id BIGINT PRIMARY KEY IDENTITY(1,1),
        nome VARCHAR(100) NOT NULL,
        especie VARCHAR(100),
        idade VARCHAR(50),
        habitat VARCHAR(255)
    );
    PRINT 'Tabela animais criada com sucesso.';
END
GO

-- Inserir dados de exemplo (opcional)
-- Descomente se desejar popular com dados iniciais

/*
-- Inserir tipos de animais
INSERT INTO tipo_animal (nome, descricao) VALUES
('Felino', 'Animais da família Felidae'),
('Canino', 'Animais da família Canidae'),
('Primata', 'Animais da ordem Primates'),
('Herbívoro', 'Animais que se alimentam de plantas');

-- Inserir animais
INSERT INTO animais (nome, especie, idade, habitat) VALUES
('Leão', 'Panthera leo', '5 anos', 'Savana Africana'),
('Tigre', 'Panthera tigris', '3 anos', 'Floresta Tropical'),
('Lobo Cinzento', 'Canis lupus', '4 anos', 'Floresta Temperada'),
('Gorila', 'Gorilla gorilla', '8 anos', 'Floresta Tropical'),
('Elefante Africano', 'Loxodonta africana', '15 anos', 'Savana Africana');

PRINT 'Dados de exemplo inseridos com sucesso.';
*/

-- Verificar tabelas criadas
SELECT 'Tabelas criadas:' AS Status;
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo';

-- Contar registros
SELECT COUNT(*) AS [Total de Tipos de Animais] FROM tipo_animal;
SELECT COUNT(*) AS [Total de Animais] FROM animais;
