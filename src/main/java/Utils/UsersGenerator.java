package Utils;

import Models.UserModel;
import Models.UserType;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UsersGenerator {
    public static void generateUsersList(){
        ArrayList<UserModel> users = new ArrayList<UserModel>(4);
        users.add(new UserModel("andrei","parolamea", UserType.CLIENT));
        users.add(new UserModel("bogdan","ciaooo",UserType.ADMIN));
        users.add(new UserModel("cristi","parola123456",UserType.CLIENT));

        Gson gson = new Gson();
        String usersList = gson.toJson(users);

        try (FileWriter file = new FileWriter("users.json")) {
            file.write(usersList);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
