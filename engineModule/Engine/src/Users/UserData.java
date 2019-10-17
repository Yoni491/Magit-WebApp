package Users;

import Repository.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class UserData {
        String username;
        boolean loggedIn;
        public HashMap<String, Repository> repoMap;
        private static Set<String> forkedRepos=new HashSet<>();
        UserData(String username){
            this.username = username;
            loggedIn = true;
            repoMap = new HashMap<>();
        }

    public String getName() {
            return username;
    }
    public boolean isInForkedRepos(String nameOfRepo){
            return forkedRepos.contains(nameOfRepo);
    }
    public void addForkedRepo(String nameOfRepo){
            forkedRepos.add(nameOfRepo);
    }
}
