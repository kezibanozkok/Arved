package compeng.arved.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Parameters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parameter {
    @Id
    private String id;
    private String paramId;
    private String paramDesc;
}
