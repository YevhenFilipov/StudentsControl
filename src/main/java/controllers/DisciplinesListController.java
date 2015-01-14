package controllers;

import constants.Constants;
import entity.Discipline;
import services.DisciplineService;
import services.MarksService;
import services.impl.DisciplineServiceImpl;
import services.impl.MarksServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.StringTokenizer;


public class DisciplinesListController extends AbstractWebtasksServletHandler {

    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute(BACK_PAGE, "/home.php");
        request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "VALIDATION_MESSAGE");

        final DisciplineService disciplineService = new DisciplineServiceImpl();
        final String idDisciplineToDelete = request.getParameter("ids");
        if (idDisciplineToDelete != null && !idDisciplineToDelete.isEmpty()) {
            final StringTokenizer stringTokenizer = new StringTokenizer(idDisciplineToDelete, "|");
            final MarksService marksService = new MarksServiceImpl();
            while (stringTokenizer.hasMoreTokens()) {
                final int idDiscipline = Integer.parseInt(stringTokenizer.nextToken());
                if (!disciplineService.delete(idDiscipline)) {
                    request.getSession().setAttribute(VALIDATION_MESSAGE, "CanNotDeleteDiscipline");
                }
                if (!marksService.deleteMarksByDiscipline(idDiscipline)) {
                    request.getSession().setAttribute(VALIDATION_MESSAGE, "CantDeleteMarks");
                }
            }
        }

        final Set<Discipline> disciplines = disciplineService.getDisciplines();
        request.setAttribute("disciplines", disciplines);

        gotoToJSP("main/discipline/disciplinesList.jsp", request, response);
    }
}
