package compeng.arved.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BildiriPayload {
    String userId;
    String baslik;
    String yazar;
    String konferans;
    String ulke;
    String sehir;
    String yil;
    String konu;
}
