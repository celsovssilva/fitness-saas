package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.response.AvaliacaoFisicaResponse;
import com.example.fitness_saas.service.PdfService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.query.Page;

import javax.swing.text.Document;
import java.awt.*;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class PdfServiceIMPL implements PdfService {

    @Override
    public ByteArrayInputStream gerarPdfAvaliacao(AvaliacaoFisicaResponse dados) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try{
            PdfWriter.getInstance(document,out);
            document.open();
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD,18);
            Font fontPadrao = FontFactory.getFont(FontFactory.HELVETICA,12);

            Paragraph p = new Paragraph("RELATÓRIO DE AVALIAÇÃO",fontTitulo);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            p.setSpacingAfter(20);
            document.add(p);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            table.addCell( new Phrase("Aluno",dados.nomeAluno(),fontPadrao));
            table.addCell( new Phrase("Data", dados.dataAvaliacao(),fontPadrao));
            table.addCell( new Phrase("Peso", dados.peso(),fontPadrao));
            table.addCell( new Phrase("% Gordura" + dados.percentualGordura() + "%"));
            document.add(table);
            document.add(new Phrase("\nIMC: " + dados.imc(), fontPadrao));
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();

        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
