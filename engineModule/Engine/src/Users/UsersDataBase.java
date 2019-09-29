package Users;

import java.util.HashMap;

public class UsersDataBase {
    public static HashMap<String,UserData> usersMap=new HashMap<>();
     public static boolean usernameExists(String name){
        return usersMap.containsKey(name);
    }
     public static void addUserName(String name){
        usersMap.put(name,new UserData(name));
    }
}

