package controllers;

import constants.Constants;
import dao.RoleDao;
import dao.impl.RoleDaoImpl;
import entity.Account;
import entity.Role;
import exeptions.InvalidDataException;
import org.apache.commons.lang.StringUtils;
import services.LoginService;
import services.impl.LogilServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginController extends AbstractWebtasksServletHandler {
    private static final long serialVersionUID = 4544737546336836686L;

    @Override
    protected void handleRequest(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            showLoginPage(request, response);
        } else {
            loginHandler(request, response);
        }
    }

    protected void showLoginPage(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        final RoleDao roleDao = new RoleDaoImpl();
        final List<Role> roles = roleDao.getRoles();
        request.setAttribute("roles", roles);
        gotoToJSP("/login.jsp", request, response);
    }

    protected void validateRequest(String username, String password)
            throws InvalidDataException {
        if (StringUtils.isBlank(username)) {
            throw new InvalidDataException("username");
        }
        if (StringUtils.isBlank(password)) {
            throw new InvalidDataException("password");
        }
    }

    protected void loginHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        final String login = request.getParameter("username");
        final String password = request.getParameter("password");
        final Integer idRole = Integer.parseInt(request.getParameter("role"));
        try {
            validateRequest(login, password);
            final LoginService loginService = new LogilServiceImpl();
            final Account a = loginService.login(login, password, idRole);
            if (a.getId() != 0) {

                String mapping = null;
                for (Role role : a.getRoles()) {
                    if (role.getId().equals(idRole))
                        mapping = "/" + role.getName();
                }
                request.getSession().setAttribute(CURRENT_SESSION_ACCOUNT, a);
                request.getSession().setAttribute(CURRENT_ROLE, idRole);
                request.getSession().setAttribute(CURRENT_MAPPING, mapping);
                redirectRequest(mapping + "/home.html", request, response);
            } else {
                request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "role");

            }
        } catch (InvalidDataException e) {
            request.setAttribute(Constants.VALIDATION_MESSAGE,
                    e.getMessage());
            this.showLoginPage(request, response);
        }
    }
}
