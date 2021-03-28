package compeng.arved.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPayload {

    private String userId;
    private boolean bap;
    private boolean uluslararasi;
    private String projeYil;
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
