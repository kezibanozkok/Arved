package compeng.arved.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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
