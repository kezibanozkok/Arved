package compeng.arved.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Personel_Bilgileri")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffInformation {

    @Id
    private String id;
    private String userId;
    private String fullName;
    private int wosHIndex;
    private int wosAtifSayisi;
    private int scopusHIndex;
    private int scopusAtifSayisi;
    private String uzmanlikAlani;

}
