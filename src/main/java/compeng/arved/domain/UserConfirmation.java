package compeng.arved.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User_Onay")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConfirmation {

    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
}
