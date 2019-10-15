package servlets;

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

    public static void clearSession (HttpServletRequest request) {
        request.getSession().invalidate();
    }
}