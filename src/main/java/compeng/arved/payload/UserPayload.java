package compeng.arved.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload {

    @Id
    private String id;
    @NotNull(message = "{name.notempty}")
    private String name;
    @NotNull(message = "Boş bırakılamaz")
    private String surname;
    @NotNull(message = "Boş bırakılamaz")
    private String email;
    @NotNull(message = "Boş bırakılamaz")
    @Size(min = 6, message = "min 6 karakter gerekli")
    private String password;
    //private String image;
    //private String department;
    //private List<Role> roles;
}
