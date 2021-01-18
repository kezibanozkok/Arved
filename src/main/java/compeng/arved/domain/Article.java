package compeng.arved.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    private String id;
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
    private Binary file;
}
