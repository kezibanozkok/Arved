package compeng.arved.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "staff information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffInformation {

    @Id
    private String id;
    private int wosHIndex;
    private int wosAtifSayisi;
    private int scopusHIndex;
    private int scopusAtifSayisi;
    private String uzmanlikAlani;
}
