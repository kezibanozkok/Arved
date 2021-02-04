package compeng.arved.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Proje_Bilgileri")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    private String id;
    private String projeId;
    private String userId;
    private boolean bap;
    private boolean uluslararasi;
    private String yil;
    private String projeDurumu;
    private String projeTuru;
    private String alanBilgisi;
    private String projeAdi;
    private String projeButcesi;
    private String paraBirimi;
    private boolean kontratliProje;
    private boolean disDestekli;
    private boolean uluslararasiIsbirlikli;
    private String arastirmaciSayisi;
    private String projeYurutucusu;
    //private String projeninAmaci;
}
