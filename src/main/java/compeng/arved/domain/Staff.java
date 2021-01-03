package compeng.arved.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String image;
    private String department;
    private StaffInformation staffInformation;
    private List<Article> articles;
    private List<Project> projects;
}
