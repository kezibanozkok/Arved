package compeng.arved.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePayload {

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
