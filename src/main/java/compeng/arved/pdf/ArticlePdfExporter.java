package compeng.arved.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import compeng.arved.domain.Article;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import com.lowagie.text.Image;
import java.io.IOException;
import java.util.List;

public class ArticlePdfExporter {
    private final List<Article> list;

    public ArticlePdfExporter(List<Article> list) {
        this.list = list;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Makale Adi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Yazar", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Yil", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Endeks Turu", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Uluslararasi Yayin", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Uluslararasi Isbirlikli", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Dergi Adi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cilt/Volume", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Sayi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Sayfa", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("DOI", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("BAP Projesinden Uretilen Yayin", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Kurum Disi Yazarli", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Article article : list) {
            table.addCell(article.getMakaleAdi());
            table.addCell(article.getYazarListesi());
            table.addCell(article.getYil());
            table.addCell(article.getEndeksTuru());
            table.addCell(article.isUluslararasiYayin() ? "Evet" : "Hayir");
            table.addCell(article.isUluslararasiIsbirlikli() ? "Evet" : "Hayir");
            table.addCell(article.getDergiAdi());
            table.addCell(article.getCilt_volume());
            table.addCell(article.getSayi());
            table.addCell(article.getSayfaNumarasi());
            table.addCell(article.getDoi());
            table.addCell(article.isBap() ? "Evet" : "Hayir");
            table.addCell(article.isKurumDisiYazar() ? "Evet" : "Hayir");
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(18);
        font.setColor(Color.BLACK);

        Image logo1 = Image.getInstance("/Users/kezibanozkok/IdeaProjects/arved/src/main/resources/static/images/AU_logo.png");
        logo1.scaleAbsolute(55, 55);
        logo1.setAbsolutePosition(40,490);
        document.add(logo1);

        Paragraph header = new Paragraph("ANKARA ÜNİVERSİTESİ", font);
        Paragraph subheader = new Paragraph("BİLGİSAYAR MÜHENDİSLİĞİ", font);
        header.setAlignment(Paragraph.ALIGN_CENTER);
        subheader.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(header);
        document.add(subheader);

        Image logo2 = Image.getInstance("/Users/kezibanozkok/IdeaProjects/arved/src/main/resources/static/images/muhendislik_fak_logo.png");
        logo2.scaleAbsolute(55, 55);
        logo2.setAbsolutePosition(753,490);
        document.add(logo2);

        PdfPTable table = new PdfPTable(13);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f});
        table.setSpacingBefore(30);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}