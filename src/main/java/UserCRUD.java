import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class UserCRUD {
    private static final String USERS_JSON = "users.json";


    private static void createFile(String path) {
        File file = new File(USERS_JSON);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createJson(List<User> users) {
        createFile(USERS_JSON);
        AtomicInteger count = new AtomicInteger();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(USERS_JSON, true))) {
            bufferedWriter.write("[\n");
            users.stream().forEach(e -> {
                count.getAndIncrement();
                try {
                    bufferedWriter.write("  {\n" +
                            "   \"User\" : {\n" +
                            "   \"id:\":\"" + e.getId() + "\",\n" +
                            "   \"firstName\" : \"" + e.getFirstName() + " \",\n" +
                            "   \"lastName\" : \"" + e.getLastName() + " \",\n" +
                            "   \"fullName\" :  \"" + e.getFullName() + " \",\n" +
                            "   \"email\" :\" " + e.getEmail() + "\"\n  }\n" +
                            "}"
                    );
                    if(count.get() <users.size()){
                        bufferedWriter.write(",\n");
                    }else{
                        bufferedWriter.write("\n");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            bufferedWriter.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readUsersJson() {
        if (new File(USERS_JSON).exists()) {
            AtomicReference<String> userJsonString = new AtomicReference<>("");
            try {
                Files.lines(Path.of(USERS_JSON)).forEach(e -> {
                    userJsonString.set(userJsonString + e);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return userJsonString.get();
        }
        return "";
    }

    public static List<User> setUsersJsonToList() throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader(USERS_JSON));
        String position;
        StringBuilder stringBuilder = new StringBuilder();
        while ((position = reader.readLine()) != null) {
            System.out.println("position = " + position);
            stringBuilder.append(position);
        }
        System.out.println("stringBuilder = " + stringBuilder);

        JsonElement jsonElement = new Gson().fromJson(stringBuilder.toString(), JsonObject.class);



        List<User> list = new ArrayList<>();
       // JsonElement jsonElement = new Gson().fromJson(readUsersJson(), JsonObject.class);
        System.out.println(jsonElement);
            JsonArray asJsonArray =  jsonElement.getAsJsonArray();

            for(JsonElement item:asJsonArray){
                list.add(getJsonElementOfUser(item));
            }

        return list;
    }

    private static User getJsonElementOfUser(JsonElement item) {
        JsonObject user = item.getAsJsonObject();

        User tempUser = new User();
        tempUser.setId(user.get("id").getAsString());
        tempUser.setFirstName(user.get("firstName").getAsString());
        tempUser.setLastName(user.get("lastName").getAsString());
        tempUser.setFullName(user.get("fullName").getAsString());
        tempUser.setEmail(user.get("email").getAsString());

        return tempUser;
    }
}
