package compeng.arved.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import compeng.arved.domain.Project;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ProjectPdfExporter {
    private final List<Project> list;

    public ProjectPdfExporter(List<Project> list) {
        this.list = list;
    }


    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Proje Adi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Proje Yurutucusu", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Yil", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Proje Durumu", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Proje Turu", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Alan Bilgisi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Proje Butcesi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Para Birimi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("BAP Projesi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Uluslararasi Proje", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Kontratli Proje", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Dis Destekli", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Uluslararasi Isbirlikli", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Arastirmaci Sayisi", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Project project : list) {
            table.addCell(project.getProjeAdi());
            table.addCell(project.getProjeYurutucusu());
            table.addCell(project.getYil());
            table.addCell(project.getProjeDurumu());
            table.addCell(project.getAlanBilgisi());
            table.addCell(project.getProjeButcesi());
            table.addCell(project.getParaBirimi());
            table.addCell(project.isBap() ? "Evet" : "Hayir");
            table.addCell(project.isUluslararasi() ? "Evet" : "Hayir");
            table.addCell(project.isKontratliProje() ? "Evet" : "Hayir");
            table.addCell(project.isDisDestekli() ? "Evet" : "Hayir");
            table.addCell(project.isUluslararasiIsbirlikli() ? "Evet" : "Hayir");
            table.addCell(project.getArastirmaciSayisi());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

        Image image = Image.getInstance("/Users/kezibanozkok/IdeaProjects/arved/src/main/resources/static/images/AU_logo.png");
        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(12);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("MAKALE RAPORLARI", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfContentByte canvas = writer.getDirectContentUnder();
        image.scaleAbsolute(500, 500);
        image.setAbsolutePosition(170, 50);
        canvas.saveState();
        PdfGState state = new PdfGState();
        state.setFillOpacity(0.1f);
        canvas.setGState(state);
        canvas.addImage(image);
        canvas.restoreState();

        PdfPTable table = new PdfPTable(14);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
