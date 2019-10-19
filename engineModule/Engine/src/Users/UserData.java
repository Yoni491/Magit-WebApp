package Users;

import Repository.Repository;

import java.util.*;

public class UserData {
        String username;
        boolean loggedIn;
        public HashMap<String, Repository> repoMap;
        private Set<String> forkedRepos=new HashSet<>();
        public ArrayList<Message> MsgList=new ArrayList<>();

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
