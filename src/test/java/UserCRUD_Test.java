import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCRUD_Test {
    private List<User> users;
    private User user;

    @Before
    public void setUp() {
        users = new ArrayList<>();
        user = new User();
        user.setFirstName("Artem");
        user.setLastName("Chizhov");
        user.setFullName("Artem Chizhov");
        user.setId("Artem-Chizhov-Kharkov-1998");
        user.setEmail("someArtem`sMail@gmail.com");
        users.add(user);

        user = new User();
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setFullName("John Smith");
        user.setId("John-Smith-NewYork-1976");
        user.setEmail("someJohn`sMail@gmail.com");
        users.add(user);
    }

    @Test
    public void createJson_nominal() {
        UserCRUD.createJson(users);
        Assert.assertEquals("[\n" +
                "  {\n" +
                "   \"User\" : {\n" +
                "   \"id:\":\"Artem-Chizhov-Kharkov-1998\",\n" +
                "   \"firstName\" : \"Artem \",\n" +
                "   \"lastName\" : \"Chizhov \",\n" +
                "   \"fullName\" :  \"Artem Chizhov \",\n" +
                "   \"email\" :\" someArtem`sMail@gmail.com\"\n" +
                "  }\n" +
                "},\n" +
                "  {\n" +
                "   \"User\" : {\n" +
                "   \"id:\":\"John-Smith-NewYork-1976\",\n" +
                "   \"firstName\" : \"John \",\n" +
                "   \"lastName\" : \"Smith \",\n" +
                "   \"fullName\" :  \"John Smith \",\n" +
                "   \"email\" :\" someJohn`sMail@gmail.com\"\n" +
                "  }\n" +
                "}\n" +
                "]",UserCRUD.readUsersJson());
    }

@Test
    public void getListOfUnitsFromJson_nominal() throws IOException {
       Assert.assertEquals(UserCRUD.setUsersJsonToList(),users);

}





}
