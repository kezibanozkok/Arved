package compeng.arved.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Makale_Bilgileri")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    private String id;
    private String makaleId;
    private String userId;
    private boolean uluslararasiYayin;
    private String endeksTuru;
    private boolean uluslararasiIsbirlikli;
    private String makaleAdi;
    private String dergiAdi;
    private String yil;
    private String cilt_volume;
    private String sayi;
    private String sayfaNumarasi;
    private String doi;
    private boolean bap;
    private boolean kurumDisiYazar;
    private String yazarListesi;
}
