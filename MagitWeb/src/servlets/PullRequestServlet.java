package servlets;

import Objects.Api.MagitObject;
import Objects.Branch.Branch;
import Repository.Repository;
import Users.Message;
import Users.PR;
import Users.UsersDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class PullRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String localBranch = request.getParameter("localBranch");
        String remoteBranch = request.getParameter("remoteBranch");
        String PrPurpose = request.getParameter("PrPurpose");

        if((localBranch!=null)&&remoteBranch!=null) {
            Repository localRepo = SessionUtils.getRepo(request);
            Repository remoteRepo = UsersDataBase.getRepo(localRepo.getRemoteRepoName(),localRepo.getRemoteRepoUserName());
            for(Map.Entry<String, MagitObject> entry: localRepo.getObjList().entrySet())
            {
                if(!remoteRepo.getObjList().entrySet().contains(entry))
                    remoteRepo.getObjList().put(entry.getKey(),entry.getValue());
            }
            Branch br2=localRepo.getRemoteBranches().stream().filter(br->br.getName().equals(localBranch)).findFirst().orElse(null);
            if(localRepo.getHeadBranchName().equals(localBranch))
                br2=localRepo.getHeadBranch();
            if(remoteRepo.getHeadBranchName().equals(remoteBranch)||
                    (remoteRepo.getBranches().stream().filter(br->br.getName().equals(localBranch)).findFirst().orElse(null)==null))
                if(br2!=null) {
                    Message msg = new Message(localRepo.getName(),SessionUtils.getUsername(request), localRepo.getRemoteRepoUserName(),
                            localBranch, remoteBranch,br2.getSha1());
                    PR pr = new PR(localRepo.getName(),SessionUtils.getUsername(request), localRepo.getRemoteRepoUserName(),
                            localBranch, remoteBranch,br2.getSha1(),PrPurpose);
                    UsersDataBase.getUserData(localRepo.getRemoteRepoUserName()).MsgList.add(msg);
                    remoteRepo.PrMap.put(SessionUtils.getUsername(request),pr);
                }
            else
                {
                    //print error msg: no remote branch with this name exists.
                }

        }

        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}