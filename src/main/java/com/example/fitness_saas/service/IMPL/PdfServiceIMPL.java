package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.response.AvaliacaoFisicaResponse;
import com.example.fitness_saas.service.PdfService;
// IMPORTANTE: Use sempre com.itextpdf
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfServiceIMPL implements PdfService {

    @Override
    public ByteArrayInputStream gerarPdfAvaliacao(AvaliacaoFisicaResponse dados) {

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();


            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fontPadrao = FontFactory.getFont(FontFactory.HELVETICA, 12);


            Paragraph p = new Paragraph("RELATÓRIO DE AVALIAÇÃO FÍSICA", fontTitulo);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(20);
            document.add(p);


            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);


            table.addCell(new Phrase("Aluno: " + dados.nomeAluno(), fontPadrao));
            table.addCell(new Phrase("Data: " + dados.dataAvaliacao().toString(), fontPadrao));
            table.addCell(new Phrase("Peso: " + dados.peso() + " kg", fontPadrao));
            table.addCell(new Phrase("% Gordura: " + dados.percentualGordura() + "%", fontPadrao));

            document.add(table);


            Paragraph imc = new Paragraph("\nIMC: " + dados.imc(), fontPadrao);
            document.add(imc);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}