package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.response.AvaliacaoFisicaResponse;
import com.example.fitness_saas.service.PdfService;

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

        Document document = new Document(PageSize.A4,30,30,30,30);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();


            Font fontTitulo = new Font(Font.FontFamily.HELVETICA,14,Font.BOLD);
            Font fontPadrao = new Font(Font.FontFamily.HELVETICA,10,Font.BOLD);
            Font fontLabel =new Font(Font.FontFamily.HELVETICA,8,Font.BOLD);
            Font fontDados=new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL);

            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);
            PdfPCell cellTitulo = new PdfPCell(new Phrase("AVALIAÇÃO FÍSICA", fontTitulo));
            cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTitulo.setPadding(8);
            table.addCell(cellTitulo);
            document.add(table);

            document.add(new Phrase("\nIndentificação do aluno", fontPadrao));
            PdfPTable table2 = new PdfPTable(4);
            table2.setWidthPercentage(100);
            table2.setSpacingBefore(5);

            table.addCell(criarCampoFormulario("Nome:", dados.nomeAluno(), fontLabel, fontDados, 4));
            table.addCell(criarCampoFormulario("Data da Avaliação:", dados.dataAvaliacao().toString(), fontLabel, fontDados, 2));
            table.addCell(criarCampoFormulario("Gênero:", "M/F", fontLabel, fontDados, 2));

            document.add(table);

            document.add(new Phrase("\nComposição Corporal", fontTitulo));
            PdfPTable table3 = new PdfPTable(2);
            table3.setWidthPercentage(100);
            table3.setSpacingBefore(5);


            table3.addCell(criarCampoFormulario("IMC", dados.imc().toString(), fontLabel, fontDados, 1));
            table3.addCell(criarCampoFormulario("Altura" +  dados.altura(),"---", fontLabel, fontDados, 1));
            table3.addCell(criarCampoFormulario("Peso: " + dados.peso() , " kg", fontLabel, fontDados, 1));
            table3.addCell(criarCampoFormulario("% Gordura: " + dados.percentualGordura() ,"%", fontLabel, fontDados, 1));
            table3.addCell(criarCampoFormulario("Braço Direito:" + dados.bracoDireito(),"cm", fontLabel,fontDados,1));
            table3.addCell(criarCampoFormulario("Braço Esquerdo:" + dados.bracoEsquerdo(),"cm", fontLabel,fontDados,1));
            table3.addCell(criarCampoFormulario("Cintura:" + dados.cintura(),"cm", fontLabel,fontDados,1));
            table3.addCell(criarCampoFormulario("Coxa Direita:" + dados.coxaDireita(), "cm", fontLabel,fontDados,1));
            table3.addCell(criarCampoFormulario("Coxa Esquerda:" + dados.coxaEsquerda(), "cm", fontLabel,fontDados,1));
            table3.addCell(criarCampoFormulario("Torax:" +  dados.torax(),"cm", fontLabel,fontDados,1));
            table3.addCell(criarCampoFormulario("Massa Muscular:" + dados.massaMagraKg(),"kg", fontLabel,fontDados,1));

            document.add(table3);

            document.close();


        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    private PdfPCell criarCampoFormulario(String label, String valor, Font flabel,Font fDados, int colspan){
        Paragraph p = new  Paragraph();
        p.add(new Phrase(label + "\n" , flabel));
        p.add(new Phrase(valor != null ? valor : "", fDados));
        PdfPCell cell = new PdfPCell(p);
        cell.setColspan(colspan);
        cell.setPadding(5);
        cell.setMinimumHeight(30);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        return cell;
    }
}