package compeng.arved.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bildiriler")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bildiri {
    @Id
    String id;
    String bildiriId;
    String userId;
    String baslik;
    String yazar;
    String konferans;
    String ulke;
    String sehir;
    String yil;
    String konu;
}
