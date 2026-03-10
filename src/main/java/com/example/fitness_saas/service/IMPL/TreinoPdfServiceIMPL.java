package com.example.fitness_saas.service.IMPL;

import com.example.fitness_saas.response.ItemTreinoResponse;
import com.example.fitness_saas.response.TreinoResponse;
import com.example.fitness_saas.service.TreinoPdfService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class TreinoPdfServiceIMPL implements TreinoPdfService {

    @Override
    public ByteArrayInputStream gerarTreinoPDF(TreinoResponse treino) {
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font fontCabecalho = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            Font fontCorpo = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

            Paragraph p = new Paragraph("FICHA DE TREINO", fontTitulo);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            p.setSpacingAfter(20);
            document.add(p);

            document.add(new Phrase(" Aluno:" + treino.nomeAluno(), fontCorpo));
            document.add(new Phrase("\nTreino:" + treino.nomeTreino(), fontCabecalho));
            document.add(new Phrase("\nPersonal:" + treino.nomePersonal() + "\n\n", fontCorpo));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{4f, 1.5f, 2f, 2.5f});

            String[] headers = {"Exercicíos", "Séries", "Repetoções", "Descanso/Obs"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(h, fontCabecalho));
                cell.setBackgroundColor(BaseColor.DARK_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(6);
                table.addCell(cell);
            }
            for (ItemTreinoResponse item : treino.itens()) {
                PdfPCell cellNome= new PdfPCell(new Phrase(item.exercicioNome(), fontCorpo));
                cellNome.setPadding(5);
                table.addCell(cellNome);
                table.addCell(criarCelulaCentralizada(String.valueOf(item.series()), fontCorpo));
                table.addCell(criarCelulaCentralizada(String.valueOf(item.repeticoes()), fontCorpo));
                String obs = item.descanso() + "s" + (item.observacao() != null ? "-" + item.observacao() : "");
                PdfPCell cellObs = new PdfPCell(new Phrase(obs, fontCorpo));
                cellObs.setPadding(5);
                cellObs.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cellObs);
            }
            document.add(table);
            document.close();


        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }


        return new ByteArrayInputStream(out.toByteArray());
    }

    private PdfPCell criarCelulaCentralizada(String texto, Font fonte) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, fonte));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        return cell;
    }
}
