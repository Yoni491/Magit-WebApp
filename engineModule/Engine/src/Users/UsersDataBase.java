package Users;

import Repository.Repository;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class UsersDataBase {
    private static HashMap<String,UserData> usersMap=new HashMap<>();
     public static boolean usernameExists(String name){
        return usersMap.containsKey(name);
    }
     public static void addUserName(String name){
        usersMap.put(name,new UserData(name));
         new File("C:/magit-ex3/"+name).mkdir();
    }
    public static void addRepo(String username,String repoName, Repository repo){
             usersMap.get(username).repoMap.put(repoName,repo);
    }

    public static Set<String> getUsers() {
         return usersMap.keySet();
    }
    public static Repository getRepo(String repo,String username){
         return usersMap.get(username).repoMap.get(repo);
    }
    public static Collection<UserData> getAllRepoNames() {
       return usersMap.values();
    }
    public static UserData getUserData(String nameOfUser){
         return usersMap.get(nameOfUser);
    }
}

