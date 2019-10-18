package servlets;

import Objects.Blob.Blob;
import Repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class saveFileContent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newContent = request.getParameter("fileContent");
        String blobSha1 = request.getParameter("blobSha1");
        Repository repo = SessionUtils.getRepo(request);
        Blob blobToChange =repo.sha1ToBlob_ex3(blobSha1);
        if(blobToChange!=null) {
            blobToChange.setContent(newContent);
            SessionUtils.setBlobSha1(request, blobToChange.getSha1());
            repo.changeBlobSha1_ex3(blobSha1,blobToChange);
        }
        response.sendRedirect("../WcPage/WcPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}