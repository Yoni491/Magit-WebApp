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
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PullRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String localBranchName = request.getParameter("localBranch");
        String remoteBranchName = request.getParameter("remoteBranch");
        String PrPurpose = request.getParameter("PrPurpose");

        if((localBranchName!=null)&&remoteBranchName!=null) {
            Repository localRepo = SessionUtils.getRepo(request);
            Repository remoteRepo = UsersDataBase.getRepo(localRepo.getRemoteRepoName(),localRepo.getRemoteRepoUserName());
            for(Map.Entry<String, MagitObject> entry: localRepo.getObjList().entrySet())
            {
                if(!remoteRepo.getObjList().entrySet().contains(entry))
                    remoteRepo.getObjList().put(entry.getKey(),entry.getValue());
            }
            Branch localBranch=localRepo.getRemoteBranches().stream().filter(br->br.getName().equals(localBranchName)).findFirst().orElse(null);
            if(localRepo.getHeadBranchName().equals(localBranchName))
                localBranch=localRepo.getHeadBranch();
            if(remoteRepo.getHeadBranchName().equals(remoteBranchName)||
                    (remoteRepo.getBranches().stream().filter(br->br.getName().equals(localBranchName)).findFirst().orElse(null)==null))
                if(localBranch!=null) {
                    String path=remoteRepo.getPath()+"/.magit";
                    new File(path).mkdir();
                    path=path+"/PR";
                    new File(path).mkdir();
                    path=path+"/"+SessionUtils.getUsername(request);
                    new File(path).mkdir();
                    remoteRepo.deployCommit(remoteRepo.sha1ToCommit_ex3(localBranch.getSha1()),path);
                    //second part of the function
                    Message msg = new Message(localRepo.getName(),SessionUtils.getUsername(request), localRepo.getRemoteRepoUserName(),
                            localBranchName, remoteBranchName,localBranch.getSha1());
                    PR pr = new PR(localRepo.getName(),SessionUtils.getUsername(request), localRepo.getRemoteRepoUserName(),
                            localBranchName, remoteBranchName,localBranch.getSha1(),PrPurpose,path);
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