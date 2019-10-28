package servlets;

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
import java.io.IOException;

public class ExecutePR extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Repository repo = SessionUtils.getRepo(request);
        PR pr=repo.PrMap.get(SessionUtils.getPrDeltaUsername(request));
        repo.getHeadBranch().UpdateSha1(pr.SenderCommitSha1);
        Repository localRepo =UsersDataBase.getUserData(pr.Sender).repoMap.get(pr.RepoName);
        if(localRepo.getHeadBranch().getName().equals(pr.SenderBranch))
            localRepo.getHeadBranch().setType("remote");
        Branch branch=localRepo.branchLambda_ex3(pr.SenderBranch);
        String Sender=pr.Sender;
        repo.PrMap.remove(pr.Sender);

        //delete msg(maybe)
        if(branch!=null)
            branch.setType("tracking");
        try {
            localRepo.addNewBranch(branch.getName(),branch.getSha1(),"remote");
        } catch (AlreadyExistingBranchException e) {
            e.printStackTrace();
        } catch (NoCommitHasBeenMadeException e) {
            e.printStackTrace();
        } catch (BranchNoNameException e) {
            e.printStackTrace();
        }
        Message msg= new Message(localRepo.getName(), SessionUtils.getUsername(request), localRepo.getRemoteRepoUserName(),
                branch.getName(),"PrAccepted");
        UsersDataBase.addMessageToUser(Sender,msg);
        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}