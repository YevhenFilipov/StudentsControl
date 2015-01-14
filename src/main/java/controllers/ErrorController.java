package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String notFound = request.getParameter("notFound");
        request.setAttribute("notFound", notFound);
        gotoToJSP("/error.jsp", request, response);
    }
}
