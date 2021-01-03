package compeng.arved.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private String yayinTuru;
    private String endeksTuru;
    private String baslik;
    private String yazarlar;
    private String date;
    private String dergiAdi;
    private String konferans;
    private int cilt;
    private int sayi;
    private int sayfa;
    private String doi;
    //file ?
}