package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController extends AbstractWebtasksServletHandler {


    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        redirectRequest("/login.php", request, response);
    }
}
