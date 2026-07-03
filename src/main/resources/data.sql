INSERT INTO tb_usuario (nome, cpf, matricula, email, senha, tipo_usuario, login_attempts) VALUES
('André Silva', '123.456.789-01', 'MAT202601', 'andre@email.com', 'pass123', 'ALUNO', 0),
('Beatriz Souza', '987.654.321-09', 'beatriz@email.com', 'pass456', 'PROFESSOR', 0),
('Carlos Lima', '456.789.123-45', 'carlos@email.com', 'pass789', 'ALUNO', 0),
('Daniela Meira', '111.222.333-44', 'daniela@email.com', 'pass111', 'ALUNO', 0),
('Eduardo Rocha', '555.666.777-88', 'MAT202605', 'eduardo@email.com', 'pass222', 'COORDENADOR', 0),
('Fernanda Alves', '999.888.777-66', 'MAT202606', 'fernanda@email.com', 'pass333', 'ALUNO', 0),
('Gabriel Costa', '444.555.666-77', 'MAT202607', 'gabriel@email.com', 'pass444', 'ALUNO', 0),
('Helena Santos', '222.333.444-55', 'MAT202608', 'helena@email.com', 'pass555', 'PROFESSOR', 0),
('Igor Martins', '777.888.999-00', 'MAT202609', 'igor@email.com', 'pass666', 'ALUNO', 0),
('Juliana Paiva', '333.444.555-66', 'MAT202610', 'juliana@email.com', 'pass777', 'ALUNO', 0),
('Kátia Oliveira', '111.333.555-77', 'MAT202611', 'katia@email.com', 'pass888', 'ALUNO', 0),
('Lucas Ferreira', '222.444.666-88', 'MAT202612', 'lucas@email.com', 'pass999', 'PROFESSOR', 0),
('Mariana Tavares', '333.555.777-99', 'MAT202613', 'mariana@email.com', 'pass000', 'ALUNO', 0),
('Paulo Mendes', '444.666.888-00', 'MAT202614', 'paulo@email.com', 'pass111', 'COORDENADOR', 0),
('Renata Pereira', '555.777.999-11', 'MAT202615', 'renata@email.com', 'pass222', 'ALUNO', 0);

INSERT INTO tb_regra_emprestimo (prazo_dias, multa_por_dia, multa_max, limite_emprestimos, ativa) VALUES
(7, 1.50, 50.00, 5, TRUE),
(14, 2.00, 100.00, 10, TRUE),
(21, 3.00, 120.00, 15, TRUE);

INSERT INTO tb_bloqueio (motivo, data_inicio, data_fim) VALUES
('Atraso grave', '2026-01-10 00:00:00', '2026-02-10 00:00:00'),
('Pendência de multa', '2026-02-01 00:00:00', NULL),
('Documento incompleto', '2026-03-15 00:00:00', '2026-03-22 00:00:00');

INSERT INTO tb_livro (titulo, autor, categoria, editora, ano_publicacao, quantidade_exemplares, status) VALUES
('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 'Infantil', 'Ática', 1943, 3, 'DISPONIVEL'),
('Dom Casmurro', 'Machado de Assis', 'Romance', 'Penguin', 1899, 2, 'EMPRESTADO'),
('1984', 'George Orwell', 'Ficção', 'Companhia das Letras', 1949, 4, 'DISPONIVEL'),
('A Arte da Guerra', 'Sun Tzu', 'Estratégia', 'Martins Fontes', 500, 1, 'DISPONIVEL'),
('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', 'Fantasia', 'Rocco', 1997, 5, 'RESERVADO'),
('O Hobbit', 'J.R.R. Tolkien', 'Fantasia', 'HarperCollins', 1937, 2, 'DISPONIVEL'),
('Memórias Póstumas de Brás Cubas', 'Machado de Assis', 'Romance', 'Penguin', 1881, 2, 'DISPONIVEL'),
('O Alquimista', 'Paulo Coelho', 'Ficção', 'Rocco', 1988, 3, 'DISPONIVEL'),
('O Senhor dos Anéis: A Sociedade do Anel', 'J.R.R. Tolkien', 'Fantasia', 'HarperCollins', 1954, 1, 'EMPRESTADO'),
('Sapiens', 'Yuval Noah Harari', 'História', 'Companhia das Letras', 2011, 4, 'DISPONIVEL');

INSERT INTO tb_reserva (data_reserva, status, codigo_usuario, codigo_livro) VALUES
('2026-06-01 09:00:00', 'PENDENTE', 1, 1),
('2026-06-02 13:00:00', 'ATENDIDA', 2, 2),
('2026-06-05 14:20:00', 'CANCELADA', 3, 5),
('2026-06-06 10:45:00', 'PENDENTE', 4, 9),
('2026-06-07 16:30:00', 'ATENDIDA', 5, 10);

INSERT INTO tb_emprestimo (data_emprestimo, data_devolucao_prevista, fk_usuario) VALUES
('2026-03-01 10:00:00', '2026-03-08 10:00:00', 1),
('2026-03-02 11:30:00', '2026-03-09 11:30:00', 2),
('2026-03-05 09:00:00', '2026-03-12 09:00:00', 3),
('2026-03-10 14:00:00', '2026-03-17 14:00:00', 4),
('2026-03-12 16:45:00', '2026-03-19 16:45:00', 5),
('2026-03-15 08:20:00', '2026-03-22 08:20:00', 6),
('2026-03-20 13:10:00', '2026-03-27 13:10:00', 7),
('2026-03-22 10:00:00', '2026-03-29 10:00:00', 8),
('2026-03-25 15:30:00', '2026-04-01 15:30:00', 9),
('2026-04-01 11:00:00', '2026-04-08 11:00:00', 10),
('2026-04-05 09:20:00', '2026-04-12 09:20:00', 11),
('2026-04-08 14:40:00', '2026-04-15 14:40:00', 12),
('2026-04-10 12:10:00', '2026-04-17 12:10:00', 13),
('2026-04-12 16:55:00', '2026-04-19 16:55:00', 14),
('2026-04-15 10:30:00', '2026-04-22 10:30:00', 15);

INSERT INTO tb_item_emprestimo (data_devolucao_prevista, data_devolucao_real, status, multa_gerada, fk_emprestimo) VALUES
('2026-03-08 10:00:00', '2026-03-07 16:00:00', 'DEVOLVIDO', 0.00, 1),
('2026-03-09 11:30:00', '2026-03-10 09:00:00', 'ATRASADO', 15.50, 2),
('2026-03-12 09:00:00', '2026-03-12 08:30:00', 'DEVOLVIDO', 0.00, 3),
('2026-03-17 14:00:00', NULL, 'PENDENTE', 0.00, 3),
('2026-03-19 16:45:00', '2026-03-25 10:00:00', 'ATRASADO', 45.00, 3),
('2026-03-22 08:20:00', '2026-03-21 17:00:00', 'DEVOLVIDO', 0.00, 6),
('2026-03-27 13:10:00', NULL, 'PENDENTE', 0.00, 7),
('2026-03-29 10:00:00', '2026-03-29 09:45:00', 'DEVOLVIDO', 0.00, 8),
('2026-04-01 15:30:00', '2026-04-05 14:00:00', 'ATRASADO', 20.00, 9),
('2026-04-08 11:00:00', NULL, 'PENDENTE', 0.00, 10),
('2026-04-12 09:20:00', NULL, 'PENDENTE', 0.00, 11),
('2026-04-15 14:40:00', NULL, 'PENDENTE', 0.00, 12),
('2026-04-17 12:10:00', NULL, 'PENDENTE', 0.00, 13),
('2026-04-19 16:55:00', NULL, 'PENDENTE', 0.00, 14),
('2026-04-22 10:30:00', NULL, 'PENDENTE', 0.00, 15);

