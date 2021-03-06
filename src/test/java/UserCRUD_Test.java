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
        Assert.assertEquals("{   \"User\" : [  {" +
                "       \"id\":\"Artem-Chizhov-Kharkov-1998\"," +
                "       \"firstName\" : \"Artem\"," +
                "       \"lastName\" : \"Chizhov\"," +
                "       \"fullName\" : \"Artem Chizhov\"," +
                "       \"email\" : \"someArtem`sMail@gmail.com\"   }," +
                "  {       \"id\":\"John-Smith-NewYork-1976\"," +
                "       \"firstName\" : \"John\"," +
                "       \"lastName\" : \"Smith\"," +
                "       \"fullName\" : \"John Smith\"," +
                "       \"email\" : \"someJohn`sMail@gmail.com\"" +
                "   }  ]}"
                , UserCRUD.readUsersJson());
    }

    @Test
    public void getListOfUnitsFromJson_nominal() throws IOException {
        ArrayList<User> newUsers = (ArrayList<User>) UserCRUD.setUsersJsonToList();
        Assert.assertEquals(newUsers.size(),users.size());
        for (int i =0;i<users.size();i++){
            Assert.assertEquals(UserCRUD.setUsersJsonToList().get(i), users.get(i));
        }
    }

    @Test
    public void getUserById_nominal() throws Exception {
        Assert.assertEquals(users.get(1),UserCRUD.getUserById("John-Smith-NewYork-1976"));
    }

    @Test
    public void deleteUserById_nominal() throws Exception {
        List<User> newUsers= UserCRUD.deleteUserById("Artem-Chizhov-Kharkov-1998");
        Assert.assertEquals(1,newUsers.size());
        Assert.assertEquals(users.get(1),newUsers.get(0));
    }


}
