package controllers;

import constants.Constants;
import entity.Discipline;
import services.DisciplineService;
import services.impl.DisciplineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisciplineCreatingController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Only admin can see this page
        if (!checkAccess(request, response)) {
            return;
        }

        request.getSession().setAttribute(BACK_PAGE, "/disciplinesList.html");
        request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "VALIDATION_MESSAGE");
        request.setAttribute("DISCIPLINE_BUTTON", 1);

        if (request.getParameter("discipline_name") == null) {
            gotoToJSP("main/discipline/disciplineCreatingModifying.jsp", request, response);
            return;
        }

        final Discipline discipline = new Discipline();
        discipline.setName(request.getParameter("discipline_name"));

        DisciplineService disciplineService = new DisciplineServiceImpl();
        if (disciplineService.add(discipline))
            redirectRequest("/admin/disciplinesList.html", request, response);
        else {
            request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "CanNotCreateDiscipline");
            gotoToJSP("main/discipline/disciplineCreatingModifying.jsp", request, response);
        }

    }
}
