package compeng.arved.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    private String id;
    private String projeAdi;
    private String projeYurutucusu;
    private String projeninAmaci;
    private String projeDurumu;
    private boolean kurumIciProje;
    private boolean uluslararasiProje;
    private String projeTuru;
    private String alanBilgisi;
    private double projeButcesi;
    private String paraBirimi;
    private boolean kontratliProje;
    private boolean disDestekliProje;
    private boolean uluslararasiIsbirlikliProje;
    private int arastirmaciSayisi;
}
