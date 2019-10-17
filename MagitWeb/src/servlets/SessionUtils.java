package servlets;

import Repository.Repository;
import Users.UsersDataBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static String errorMsgXml="";
    public static String successMsg="";
    public static String getUsername (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute("userName") : null;
        return sessionAttribute != null ? sessionAttribute.toString() : null;
    }
    public static String getUsernameForRepos (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute("pressed") : null;
        return sessionAttribute != null ? sessionAttribute.toString() : "";
    }
    public static void setUsernameForRepos (HttpServletRequest request,String username) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("pressed", username);
        }
    }
    public static void setRepoName (HttpServletRequest request,String username) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("repoName", username);
        }
    }
    public static String getRepoName (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute("repoName") : null;
        return sessionAttribute != null ? sessionAttribute.toString() : "";
    }
    public static void setBranch (HttpServletRequest request,String username) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("branchName", username);
        }
    }
    public static String getBranch (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute("branchName") : null;
        return sessionAttribute != null ? sessionAttribute.toString() : "";
    }
    public static void setCommit (HttpServletRequest request,String username) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("commitName", username);
        }
    }
    public static String getCommit (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute("commitName") : null;
        return sessionAttribute != null ? sessionAttribute.toString() : "";
    }

    public static Repository getRepo(HttpServletRequest request)
    {
        String repoName = getRepoName(request);
        String username = SessionUtils.getUsername(request);
        return UsersDataBase.getRepo(repoName,username);

    }
    public static void setFile (HttpServletRequest request,String username) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("filePath", username);
        }
    }
    public static String getFile (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute("filePath") : null;
        return sessionAttribute != null ? sessionAttribute.toString() : "";
    }
    public static void clearSession (HttpServletRequest request) {
        request.getSession().invalidate();
    }


}