package compeng.arved.payload;

import compeng.arved.domain.Article;
import compeng.arved.domain.Project;
import compeng.arved.domain.StaffInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffInformationPayload {

    private int wosHIndex;
    private int wosAtifSayisi;
    private int scopusHIndex;
    private int scopusAtifSayisi;
    private String uzmanlikAlani;
}
