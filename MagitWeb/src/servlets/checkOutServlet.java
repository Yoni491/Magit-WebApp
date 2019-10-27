package servlets;

import Objects.Branch.Branch;
import Repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class checkOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String branchName = request.getParameter("branchName");

        Repository repo = SessionUtils.getRepo(request);
        if(branchName!=null) {
            Branch branch = repo.getBranches().stream().filter(br->br.getName().equals(branchName)).filter(br->!br.getType().equals("tracking")).findFirst().orElse(null);
            repo.setWc_ex3(repo.sha1ToCommit_ex3(branch.getSha1()));
            repo.checkOut_ex3(branch);
        }
        response.sendRedirect("../RepositoryPage/BranchServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}