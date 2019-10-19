package servlets;

import Objects.Branch.Branch;
import Repository.Repository;
import Users.Message;
import Users.UsersDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PullRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String localBranch = request.getParameter("localBranch");
        String remoteBranch = request.getParameter("remoteBranch");
        if((localBranch!=null)&&remoteBranch!=null) {
            Repository repo = SessionUtils.getRepo(request);
            Repository remoteRepo = UsersDataBase.getRepo(repo.getRemoteRepoName(),repo.getRemoteRepoUserName());
            //need to copy all the objlist from one repo to another, to not have problems with commit files.
            Branch br2=repo.getBranches().stream().filter(br->br.getName().equals(localBranch)).findFirst().orElse(null);
            if(br2!=null) {
                Message msg = new Message(repo.getName(),SessionUtils.getUsername(request), repo.getRemoteRepoUserName(),
                        localBranch, remoteBranch,br2.getSha1());
                UsersDataBase.getUserData(repo.getRemoteRepoUserName()).MsgList.add(msg);
            }

        }

        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}