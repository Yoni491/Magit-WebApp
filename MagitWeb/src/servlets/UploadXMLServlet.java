package servlets;

import EngineRunner.ModuleTwo;
import XML.XmlNotValidException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Scanner;

import static servlets.SessionUtils.*;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadXMLServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //loggedInUsername = SessionUtils.getUsername(request);
        //loggedInUser = ServletUtils.getUsersManager(getServletContext()).getUsers().get(loggedInUsername);
        //m_UserEngine = loggedInUser.getEngine();

        Collection<Part> parts = request.getParts();
        StringBuilder XMLFileContent = new StringBuilder();

        for (Part part : parts) {
            //to write the content of the file to a string
            XMLFileContent.append(readFromInputStream(part.getInputStream()));
        }

        ModuleTwo.buildDir_Ex3();
        ModuleTwo.makeFileForXML_Ex3(XMLFileContent.toString());
        try {
            ModuleTwo.makeXMLfromRepo_Ex3();
            successMsg="File uploaded successfully.";
        } catch (XmlNotValidException e) {
            errorMsgXml=e.getMessage();
        }
        response.sendRedirect("../../");

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }
    private String readFromInputStream(InputStream inputStream) {
        return new Scanner(inputStream).useDelimiter("\\Z").next();
    }
}
