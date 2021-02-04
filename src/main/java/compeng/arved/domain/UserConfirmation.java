package compeng.arved.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User_Onay")
public class UserConfirmation {

    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
}
