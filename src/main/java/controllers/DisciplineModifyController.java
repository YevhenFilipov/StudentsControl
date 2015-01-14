package controllers;

import constants.Constants;
import entity.Discipline;
import services.DisciplineService;
import services.impl.DisciplineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisciplineModifyController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Only admin can see this page
        if (!checkAccess(request, response)) {
            return;
        }

        request.getSession().setAttribute(BACK_PAGE, "/disciplinesList.php");
        request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "VALIDATION_MESSAGE");
        request.setAttribute("DISCIPLINE_BUTTON", 2);

        final Integer idDiscipline = Integer.parseInt(request.getParameter("id"));
        final DisciplineService disciplineService = new DisciplineServiceImpl();

        if (request.getParameter("discipline_name") == null) {
            final Discipline discipline = disciplineService.getById(idDiscipline);
            request.setAttribute("discipline", discipline);
            gotoToJSP("main/discipline/disciplineCreatingModifying.jsp", request, response);
            return;
        }

        final Discipline modifiedDiscipline = new Discipline();
        modifiedDiscipline.setName(request.getParameter("discipline_name"));

        if (disciplineService.modify(idDiscipline, modifiedDiscipline)) {
            redirectRequest("/admin/disciplinesList.php", request, response);
        } else {
            request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "CanNotModifyDiscipline");
            gotoToJSP("main/discipline/disciplineCreatingModifying.jsp", request, response);
        }
    }
}
