package servlets;

import Objects.Blob.Blob;
import Objects.Commit.Commit;
import Repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class deleteFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Repository repo =SessionUtils.getRepo(request);
        String fileSha1 = request.getParameter("blobSha1");

        if(fileSha1!=null)
            repo.deleteFile_ex3(fileSha1);
        response.sendRedirect("../WcPage/WcPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}
