package org.iftm.modelo_api_rest.services;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.iftm.modelo_api_rest.entities.Emprestimo;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

// Utilitário para gerar PDF do comprovante de empréstimo
// Gera um PDF simples com dados do aluno, livro, datas, operador e timestamp
public class ComprovantePdfGenerator {

	public static byte[] generate(Emprestimo emprestimo, String operador, Date emitidoEm) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();

			// Cabeçalho do comprovante
			String titulo = "Comprovante de Empréstimo";
			document.add(new Paragraph(titulo));
			document.add(new Paragraph("-------------------------------"));

			// Dados do usuário
			document.add(new Paragraph("Aluno: " + safe(emprestimo.getUsuario() == null ? "-" : emprestimo.getUsuario().getNome())));
			document.add(new Paragraph("Matrícula: " + safe(emprestimo.getUsuario() == null ? "-" : emprestimo.getUsuario().getMatricula())));
			document.add(new Paragraph("CPF: " + safe(emprestimo.getUsuario() == null ? "-" : emprestimo.getUsuario().getCpf())));

			// Datas do empréstimo
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			document.add(new Paragraph("Data do empréstimo: " + (emprestimo.getDataEmprestimo() == null ? "-" : fmt.format(emprestimo.getDataEmprestimo()))));
			document.add(new Paragraph("Data prevista de devolução: " + (emprestimo.getDataDevolucaoPrevista() == null ? "-" : fmt.format(emprestimo.getDataDevolucaoPrevista()))));

			// Operador e identificação do empréstimo
			document.add(new Paragraph("Bibliotecário responsável: " + safe(emprestimo.getBibliotecarioResponsavel())));
			document.add(new Paragraph("Código do empréstimo: " + safe(emprestimo.getCodigoEmprestimo())));
			document.add(new Paragraph("Status: " + "EMPRESTADO"));

			// Timestamp de emissão e operador (RN01)
			String emitidoStr = emitidoEm == null ? "-" : fmt.format(emitidoEm);
			document.add(new Paragraph("Emitido em: " + emitidoStr));
			document.add(new Paragraph("Operador: " + safe(operador)));

			document.add(new Paragraph("\nObservação: Itens do empréstimo estão registrados no histórico do empréstimo."));

			document.close();
			return baos.toByteArray();
		} catch (DocumentException e) {
			throw new RuntimeException("Falha ao gerar PDF do comprovante", e);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar comprovante", e);
		}
	}

	private static String safe(Object o) {
		return o == null ? "-" : o.toString();
	}
}

