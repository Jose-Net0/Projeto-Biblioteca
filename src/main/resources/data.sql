INSERT INTO tb_usuario (nome, cpf, matricula, email, senha, tipo_usuario) VALUES
('André Silva', '123.456.789-01', 'MAT202601', 'andre@email.com', 'pass123', 'ALUNO'),
('Beatriz Souza', '987.654.321-09', 'MAT202602', 'beatriz@email.com', 'pass456', 'PROFESSOR'),
('Carlos Lima', '456.789.123-45', 'MAT202603', 'carlos@email.com', 'pass789', 'ALUNO'),
('Daniela Meira', '111.222.333-44', 'MAT202604', 'daniela@email.com', 'pass111', 'ALUNO'),
('Eduardo Rocha', '555.666.777-88', 'MAT202605', 'eduardo@email.com', 'pass222', 'COORDENADOR'),
('Fernanda Alves', '999.888.777-66', 'MAT202606', 'fernanda@email.com', 'pass333', 'ALUNO'),
('Gabriel Costa', '444.555.666-77', 'MAT202607', 'gabriel@email.com', 'pass444', 'ALUNO'),
('Helena Santos', '222.333.444-55', 'MAT202608', 'helena@email.com', 'pass555', 'PROFESSOR'),
('Igor Martins', '777.888.999-00', 'MAT202609', 'igor@email.com', 'pass666', 'ALUNO'),
('Juliana Paiva', '333.444.555-66', 'MAT202610', 'juliana@email.com', 'pass777', 'ALUNO');

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
('2026-04-01 11:00:00', '2026-04-08 11:00:00', 10);

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
('2026-04-08 11:00:00', NULL, 'PENDENTE', 0.00, 10);

