package Users;

import Repository.Repository;

import java.util.HashMap;

public class UserData {
        String username;
        boolean loggedIn;
        HashMap<String, Repository> repoMap;
        UserData(String username){
            this.username = username;
            loggedIn = true;
            repoMap = new HashMap<>();
        }
}
