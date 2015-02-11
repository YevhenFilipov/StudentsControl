package controllers;

import constants.Constants;
import dao.RoleDao;
import dao.impl.RoleDaoImpl;
import entity.Role;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public abstract class AbstractWebtasksServletHandler extends HttpServlet
        implements Constants {
    private static final long serialVersionUID = 2616056221299712890L;
    protected final Logger LOGGER = Logger.getLogger(getClass());
    private String contextName;

    @Override
    public final void init(ServletConfig config) throws ServletException {
        contextName = config.getServletContext().getContextPath();
        super.init(config);
    }

    @Override
    protected final void service(HttpServletRequest req,
                                 HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public final void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleRequest0(req, resp);
    }

    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleRequest0(req, resp);
    }

    @Override
    protected final void doDelete(HttpServletRequest req,
                                  HttpServletResponse resp) throws ServletException, IOException {
        handleRequest0(req, resp);
    }

    @Override
    protected final void doOptions(HttpServletRequest req,
                                   HttpServletResponse resp) throws ServletException, IOException {
        handleRequest0(req, resp);
    }

    @Override
    protected final void doHead(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleRequest0(req, resp);
    }

    @Override
    protected final void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleRequest0(req, resp);
    }

    @Override
    protected final void doTrace(HttpServletRequest req,
                                 HttpServletResponse resp) throws ServletException, IOException {
        handleRequest0(req, resp);
    }

    private void handleRequest0(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        try {
            preHandleRequest(request, response);
            handleRequest(request, response);
        } catch (Exception e) {
            LOGGER.error("Application can't fulfil this request", e);
            handleError(e, request, response);
        }
    }

    protected abstract void handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception;

    protected void preHandleRequest(HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        final Object role = request.getSession().getAttribute(CURRENT_ROLE);
        if (role == null &&
                request.getMethod().equals("GET")){
            showLoginPage(request, response);
        }
    }

    protected final void handleError(Exception ex, HttpServletRequest request,
                                     HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("javax.servlet.error.exception", ex);
        gotoToJSP("error.jsp", request, response);
    }

    protected final void gotoToJSP(String page, HttpServletRequest request,
                                   HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("currentPage", "../JSP/" + page);
        request.getRequestDispatcher("/WEB-INF/templates/page-template.jsp")
                .forward(request, response);
    }

    protected final void redirectRequest(String path,
                                         HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(contextName + path);
    }

    protected boolean checkAccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Object currentRole = request.getSession().getAttribute("CURRENT_ROLE");
        if (currentRole == null || !currentRole.equals(1)) {
            request.setAttribute("notFound", "true");
            gotoToJSP("/error.jsp", request, response);
            return false;
        }
        return true;
    }

    protected void showLoginPage(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        final RoleDao roleDao = new RoleDaoImpl();
        final List<Role> roles = roleDao.getRoles();
        request.setAttribute("roles", roles);
        gotoToJSP("/login.jsp", request, response);
    }

}
