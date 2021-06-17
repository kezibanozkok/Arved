package compeng.arved.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import compeng.arved.domain.Article;
import compeng.arved.domain.Bildiri;
import compeng.arved.domain.Parameter;
import compeng.arved.domain.Project;
import org.librepdf.openpdf.fonts.Liberation;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import com.lowagie.text.Image;

import java.io.IOException;
import java.util.List;

public class PdfExporter {
    private final List<Article> articles;
    private final List<Project> projects;
    private final List<Bildiri> bildiriList;

    public PdfExporter(List<Article> articles, List<Project> projects, List<Bildiri> bildiriList) {
        this.articles = articles;
        this.projects = projects;
        this.bildiriList = bildiriList;
    }

    private void writeTableHeaderArticle(PdfPTable table, List<Parameter> parameters) throws IOException {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(1);

        /*Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);*/
        Font font = Liberation.SERIF.create();
        font.setColor(Color.WHITE);
        font.setSize(11);

        Parameter makaleAdi = parameters.stream()
                .filter(parameter -> "MAKALE_ADI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert makaleAdi != null;
        cell.setPhrase(new Phrase(makaleAdi.getParamDesc(), font));
        table.addCell(cell);

        Parameter yazar = parameters.stream()
                .filter(parameter -> "YAZARLAR".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert yazar != null;
        cell.setPhrase(new Phrase(yazar.getParamDesc(), font));
        table.addCell(cell);

        Parameter makaleYil = parameters.stream()
                .filter(parameter -> "MAKALE_YIL".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert makaleYil != null;
        cell.setPhrase(new Phrase(makaleYil.getParamDesc(), font));
        table.addCell(cell);

        Parameter endeksTuru = parameters.stream()
                .filter(parameter -> "ENDEKS_TURU".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert endeksTuru != null;
        cell.setPhrase(new Phrase(endeksTuru.getParamDesc(), font));
        table.addCell(cell);

        Parameter uluslararasiYayin = parameters.stream()
                .filter(parameter -> "ULUSLARARASI_YAYIN".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert uluslararasiYayin != null;
        cell.setPhrase(new Phrase(uluslararasiYayin.getParamDesc(), font));
        table.addCell(cell);

        Parameter uluslararasiIsbirlikli = parameters.stream()
                .filter(parameter -> "ULUSLARARASI_ISBIRLIKLI_YAYIN".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert uluslararasiIsbirlikli != null;
        cell.setPhrase(new Phrase(uluslararasiIsbirlikli.getParamDesc(), font));
        table.addCell(cell);

        Parameter dergi = parameters.stream()
                .filter(parameter -> "DERGI_ADI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert dergi != null;
        cell.setPhrase(new Phrase(dergi.getParamDesc(), font));
        table.addCell(cell);

        Parameter cilt_volume = parameters.stream()
                .filter(parameter -> "CILT_VOLUME".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert cilt_volume != null;
        cell.setPhrase(new Phrase(cilt_volume.getParamDesc(), font));
        table.addCell(cell);

        Parameter sayi = parameters.stream()
                .filter(parameter -> "SAYI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert sayi != null;
        cell.setPhrase(new Phrase(sayi.getParamDesc(), font));
        table.addCell(cell);

        Parameter sayfa = parameters.stream()
                .filter(parameter -> "SAYFA".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert sayfa != null;
        cell.setPhrase(new Phrase(sayfa.getParamDesc(), font));
        table.addCell(cell);

        Parameter doi = parameters.stream()
                .filter(parameter -> "DOI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert doi != null;
        cell.setPhrase(new Phrase(doi.getParamDesc(), font));
        table.addCell(cell);

        Parameter bapYayini = parameters.stream()
                .filter(parameter -> "BAP_YAYINI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert bapYayini != null;
        cell.setPhrase(new Phrase(bapYayini.getParamDesc(), font));
        table.addCell(cell);

        Parameter kurumDisiYazar = parameters.stream()
                .filter(parameter -> "KURUM_DISI_YAZAR".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert kurumDisiYazar != null;
        cell.setPhrase(new Phrase(kurumDisiYazar.getParamDesc(), font));
        table.addCell(cell);
    }

    private void writeTableHeaderProject(PdfPTable table, List<Parameter> parameters) throws IOException {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(1);

        /*Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);*/
        Font font = Liberation.SERIF.create();
        font.setColor(Color.WHITE);
        font.setSize(11);

        Parameter projeAdi = parameters.stream()
                .filter(parameter -> "PROJE_ADI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert projeAdi != null;
        cell.setPhrase(new Phrase(projeAdi.getParamDesc(), font));
        table.addCell(cell);

        Parameter projeYurutucusu = parameters.stream()
                .filter(parameter -> "PROJE_YURUTUCUSU".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert projeYurutucusu != null;
        cell.setPhrase(new Phrase(projeYurutucusu.getParamDesc(), font));
        table.addCell(cell);

        Parameter projeYil = parameters.stream()
                .filter(parameter -> "PROJE_YIL".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert projeYil != null;
        cell.setPhrase(new Phrase(projeYil.getParamDesc(), font));
        table.addCell(cell);

        Parameter projeDurumu = parameters.stream()
                .filter(parameter -> "PROJE_DURUMU".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert projeDurumu != null;
        cell.setPhrase(new Phrase(projeDurumu.getParamDesc(), font));
        table.addCell(cell);

        Parameter projeTuru = parameters.stream()
                .filter(parameter -> "PROJE_TURU".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert projeTuru != null;
        cell.setPhrase(new Phrase(projeTuru.getParamDesc(), font));
        table.addCell(cell);

        Parameter alanBilgisi = parameters.stream()
                .filter(parameter -> "ALAN_BILGISI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert alanBilgisi != null;
        cell.setPhrase(new Phrase(alanBilgisi.getParamDesc(), font));
        table.addCell(cell);

        Parameter projeButcesi = parameters.stream()
                .filter(parameter -> "PROJE_BUTCESI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert projeButcesi != null;
        cell.setPhrase(new Phrase(projeButcesi.getParamDesc(), font));
        table.addCell(cell);

        Parameter paraBirimi = parameters.stream()
                .filter(parameter -> "PARA_BIRIMI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert paraBirimi != null;
        cell.setPhrase(new Phrase(paraBirimi.getParamDesc(), font));
        table.addCell(cell);

        Parameter bapProjesi = parameters.stream()
                .filter(parameter -> "BAP_PROJESI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert bapProjesi != null;
        cell.setPhrase(new Phrase(bapProjesi.getParamDesc(), font));
        table.addCell(cell);

        Parameter uluslararasiProje = parameters.stream()
                .filter(parameter -> "ULUSLARARASI_PROJE".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert uluslararasiProje != null;
        cell.setPhrase(new Phrase(uluslararasiProje.getParamDesc(), font));
        table.addCell(cell);

        Parameter kontratliProje = parameters.stream()
                .filter(parameter -> "KONTRATLI_PROJE".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert kontratliProje != null;
        cell.setPhrase(new Phrase(kontratliProje.getParamDesc(), font));
        table.addCell(cell);

        Parameter disDestekli = parameters.stream()
                .filter(parameter -> "DIS_DESTEKLI_PROJE".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert disDestekli != null;
        cell.setPhrase(new Phrase(disDestekli.getParamDesc(), font));
        table.addCell(cell);

        Parameter uluslararasiIsbirlikliProje = parameters.stream()
                .filter(parameter -> "ULUSLARARASI_ISBIRLIKLI_PROJE".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert uluslararasiIsbirlikliProje != null;
        cell.setPhrase(new Phrase(uluslararasiIsbirlikliProje.getParamDesc(), font));
        table.addCell(cell);

        Parameter arastirmaciSayisi = parameters.stream()
                .filter(parameter -> "ARASTIRMACI_SAYISI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert arastirmaciSayisi != null;
        cell.setPhrase(new Phrase(arastirmaciSayisi.getParamDesc(), font));
        table.addCell(cell);
    }

    private void writeTableHeaderBildiri(PdfPTable table, List<Parameter> parameters) throws IOException {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(1);
        Font font = Liberation.SERIF.create();
        font.setColor(Color.WHITE);
        font.setSize(13);

        Parameter bildiriBaslik = parameters.stream()
                .filter(parameter -> "BILDIRI_ADI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert bildiriBaslik != null;
        cell.setPhrase(new Phrase(bildiriBaslik.getParamDesc(), font));
        table.addCell(cell);

        Parameter yazar = parameters.stream()
                .filter(parameter -> "BILDIRI_YAZAR".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert yazar != null;
        cell.setPhrase(new Phrase(yazar.getParamDesc(), font));
        table.addCell(cell);

        Parameter konferans = parameters.stream()
                .filter(parameter -> "BILDIRI_KONFERANS".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert konferans != null;
        cell.setPhrase(new Phrase(konferans.getParamDesc(), font));
        table.addCell(cell);

        Parameter ulke = parameters.stream()
                .filter(parameter -> "BILDIRI_ULKE".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert ulke != null;
        cell.setPhrase(new Phrase(ulke.getParamDesc(), font));
        table.addCell(cell);

        Parameter sehir = parameters.stream()
                .filter(parameter -> "BILDIRI_SEHIR".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert sehir != null;
        cell.setPhrase(new Phrase(sehir.getParamDesc(), font));
        table.addCell(cell);

        Parameter yil = parameters.stream()
                .filter(parameter -> "BILDIRI_YIL".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert yil != null;
        cell.setPhrase(new Phrase(yil.getParamDesc(), font));
        table.addCell(cell);

        Parameter konu = parameters.stream()
                .filter(parameter -> "BILDIRI_KONU".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert konu != null;
        cell.setPhrase(new Phrase(konu.getParamDesc(), font));
        table.addCell(cell);
    }

    private void writeTableDataArticle(PdfPTable table) throws IOException {
        PdfPCell cell = new PdfPCell();
        Font font = Liberation.SERIF.create();
        font.setSize(11);
        font.setColor(Color.BLACK);

        for (Article article : articles) {
            cell.setPhrase(new Phrase(article.getMakaleAdi(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.getYazarListesi(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.getYil(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.getEndeksTuru(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.isUluslararasiYayin() ? "Evet" : "Hayır", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.isUluslararasiIsbirlikli() ? "Evet" : "Hayır", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.getDergiAdi(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.getCilt_volume(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.getSayi(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.getSayfaNumarasi(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.getDoi(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.isBap() ? "Evet" : "Hayır", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(article.isKurumDisiYazar() ? "Evet" : "Hayır", font));
            table.addCell(cell);
        }
    }

    private void writeTableDataProject(PdfPTable table1) throws IOException {
        PdfPCell cell = new PdfPCell();
        Font font = Liberation.SERIF.create();
        font.setSize(11);
        font.setColor(Color.BLACK);

        for (Project project : projects) {
            cell.setPhrase(new Phrase(project.getProjeAdi(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.getProjeYurutucusu(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.getYil(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.getProjeDurumu(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.getProjeTuru(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.getAlanBilgisi(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.getProjeButcesi(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.getParaBirimi(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.isBap() ? "Evet" : "Hayır", font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.isUluslararasi() ? "Evet" : "Hayır", font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.isKontratliProje() ? "Evet" : "Hayır", font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.isDisDestekli() ? "Evet" : "Hayır", font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.isUluslararasiIsbirlikli() ? "Evet" : "Hayır", font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(project.getArastirmaciSayisi(), font));
            table1.addCell(cell);
        }
    }

    private void writeTableDataBildiri(PdfPTable table1) throws IOException {
        PdfPCell cell = new PdfPCell();
        Font font = Liberation.SERIF.create();
        font.setSize(11);
        font.setColor(Color.BLACK);

        for (Bildiri bildiri : bildiriList) {
            cell.setPhrase(new Phrase(bildiri.getBaslik(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(bildiri.getYazar(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(bildiri.getKonferans(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(bildiri.getUlke(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(bildiri.getSehir(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(bildiri.getYil(), font));
            table1.addCell(cell);
            cell.setPhrase(new Phrase(bildiri.getKonu(), font));
            table1.addCell(cell);
        }
    }

    public void export(HttpServletResponse response, List<Parameter> parameters) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        /*Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(18);
        font.setColor(Color.BLACK);*/

        Font font = Liberation.SERIF.create();
        font.setSize(18);
        font.setColor(Color.BLACK);

        Font font1 = Liberation.SERIF.create();
        font1.setSize(14);
        font1.setColor(Color.BLACK);

        Image logo1 = Image.getInstance("/Users/kezibanozkok/IdeaProjects/arved/src/main/resources/static/images/AU_logo.png");
        logo1.scaleAbsolute(55, 55);
        logo1.setAbsolutePosition(40,490);
        document.add(logo1);

        Parameter bolumAdi = parameters.stream()
                .filter(parameter -> "BOLUM_ADI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        Parameter okulAdi = parameters.stream()
                .filter(parameter -> "OKUL_ADI".equals(parameter.getParamId()))
                .findAny()
                .orElse(null);

        assert okulAdi != null;
        assert bolumAdi != null;
        Paragraph header = new Paragraph(okulAdi.getParamDesc(), font);
        Paragraph subheader = new Paragraph(bolumAdi.getParamDesc(), font);
        Paragraph makale = new Paragraph("Ankara Üniversitesi Bilgisayar Mühendisliği Bölümü Araştırma-Geliştirme Faaliyet Raporları", font1);
        header.setAlignment(Paragraph.ALIGN_CENTER);
        subheader.setAlignment(Paragraph.ALIGN_CENTER);
        makale.setAlignment(Paragraph.ALIGN_CENTER);
        makale.setSpacingBefore(30);
        document.add(header);
        document.add(subheader);
        document.add(makale);

        Image logo2 = Image.getInstance("/Users/kezibanozkok/IdeaProjects/arved/src/main/resources/static/images/muhendislik_fak_logo.png");
        logo2.scaleAbsolute(55, 55);
        logo2.setAbsolutePosition(753,490);
        document.add(logo2);

        PdfPTable articleTable = new PdfPTable(13);
        articleTable.setWidthPercentage(100f);
        articleTable.setWidths(new float[]{0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f});
        articleTable.setSpacingBefore(10);

        writeTableHeaderArticle(articleTable, parameters);
        writeTableDataArticle(articleTable);
        document.add(articleTable);

        boolean newPage = document.newPage();
        //System.out.println("new page is created? : " + newPage);

        PdfPTable projectTable = new PdfPTable(14);
        projectTable.setWidthPercentage(100f);
        projectTable.setWidths(new float[]{0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f});
        projectTable.setSpacingBefore(50);

        writeTableHeaderProject(projectTable, parameters);
        writeTableDataProject(projectTable);
        document.add(projectTable);

        document.newPage();

        PdfPTable bildiriTable = new PdfPTable(7);
        bildiriTable.setWidthPercentage(100f);
        bildiriTable.setWidths(new float[]{0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f});
        bildiriTable.setSpacingBefore(50);

        writeTableHeaderBildiri(bildiriTable, parameters);
        writeTableDataBildiri(bildiriTable);
        document.add(bildiriTable);

        document.close();
    }
}