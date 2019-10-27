package servlets;

import Objects.Api.MagitObject;
import Objects.Branch.AlreadyExistingBranchException;
import Objects.Branch.Branch;
import Objects.Branch.BranchNoNameException;
import Objects.Branch.NoCommitHasBeenMadeException;
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
        try {
            processRequest(request,response);
        } catch (NoCommitHasBeenMadeException e) {
            e.printStackTrace();
        } catch (BranchNoNameException e) {
            e.printStackTrace();
        } catch (AlreadyExistingBranchException e) {
            e.printStackTrace();
        }
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoCommitHasBeenMadeException, BranchNoNameException, AlreadyExistingBranchException {
        String localBranchName = request.getParameter("localBranch");
        String remoteBranchName = request.getParameter("remoteBranch");
        String PrPurpose = request.getParameter("PrPurpose");

        if((localBranchName!=null)&&remoteBranchName!=null) {
            Repository localRepo = SessionUtils.getRepo(request);
            Repository remoteRepo = UsersDataBase.getRepo(localRepo.getRemoteRepoName(), localRepo.getRemoteRepoUserName());
            for (Map.Entry<String, MagitObject> entry : localRepo.getObjList().entrySet()) {
                if (!remoteRepo.getObjList().entrySet().contains(entry))
                    remoteRepo.getObjList().put(entry.getKey(), entry.getValue());
            }
            Branch localBranch = localRepo.getBranches().stream().filter(br -> br.getName().equals(localBranchName)).filter(br -> br.getType().equals("local")).findFirst().orElse(null);
            Branch remoteBranch = remoteRepo.getBranches().stream().filter(br -> br.getName().equals(localBranchName)).filter(br -> br.getType().equals("local")).findFirst().orElse(null);
            if (localRepo.getHeadBranchName().equals(localBranchName)) {
                localBranch = localRepo.getHeadBranch();
            }
            if (remoteRepo.getHeadBranchName().equals(remoteBranchName))
            {
                remoteBranch = remoteRepo.getHeadBranch();
            }
            if (localBranch != null && localBranch.getType().equals("local")) {
                if (remoteBranch != null)
                     {
                        String path = remoteRepo.getPath() + "/.magit";
                        new File(path).mkdir();
                        path = path + "/PR";
                        new File(path).mkdir();
                        path = path + "/" + SessionUtils.getUsername(request);
                        new File(path).mkdir();
                        remoteRepo.deployCommit(remoteRepo.sha1ToCommit_ex3(localBranch.getSha1()), path);
                        //second part of the function
                        Message msg = new Message(localRepo.getName(), SessionUtils.getUsername(request), localRepo.getRemoteRepoUserName(),
                                localBranchName, remoteBranchName, localBranch.getSha1());
                        PR pr = new PR(localRepo.getName(), SessionUtils.getUsername(request), localRepo.getRemoteRepoUserName(),
                                localBranchName, remoteBranchName, localBranch.getSha1(),remoteBranch.getSha1(), PrPurpose, path,msg);
                        UsersDataBase.addMessageToUser(localRepo.getRemoteRepoUserName(),msg);
                        remoteRepo.PrMap.put(SessionUtils.getUsername(request), pr);
                        remoteRepo.addNewBranch(localBranchName,localBranch.getSha1(),"PR");
                    } else {
                        //print error msg: no remote branch with this name exists.
                    }
            }
            else
            {
                //print error msg: no remote branch with this name exists.
            }
        }
        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (NoCommitHasBeenMadeException e) {
            e.printStackTrace();
        } catch (BranchNoNameException e) {
            e.printStackTrace();
        } catch (AlreadyExistingBranchException e) {
            e.printStackTrace();
        }
    }
}