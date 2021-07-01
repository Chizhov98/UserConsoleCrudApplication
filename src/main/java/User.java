import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;

    }
