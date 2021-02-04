package compeng.arved.payload;

import compeng.arved.domain.Article;
import compeng.arved.domain.Project;
import compeng.arved.domain.Role;
import compeng.arved.domain.StaffInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload {

    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    //private String image;
    //private String department;
    private List<Role> roles;
}
