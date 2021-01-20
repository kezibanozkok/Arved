package compeng.arved.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPayload {

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
