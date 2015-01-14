package controllers;

import constants.Constants;
import entity.Discipline;
import entity.Term;
import services.DisciplineService;
import services.TermService;
import services.impl.DisciplineServiceImpl;
import services.impl.TermServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.StringTokenizer;

public class TermCreatingController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Only admin can see this page
        if (!checkAccess(request, response)) {
            return;
        }

        request.getSession().setAttribute(BACK_PAGE, "/termsList.php");
        request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "VALIDATION_MESSAGE");
        request.setAttribute("TERM_BUTTON", 1);

        final DisciplineService disciplineService = new DisciplineServiceImpl();
        final Set<Discipline> disciplines = disciplineService.getDisciplines();
        request.setAttribute("disciplines", disciplines);

        if (request.getParameter("disciplines_list") == null) {
            gotoToJSP("main/term/termCreatingModifying.jsp", request, response);
            return;
        }
        final int termDuration = Integer.parseInt(request.getParameter("term_duration"));
        final StringTokenizer stringTokenizer = new StringTokenizer(request.getParameter("id_disciplines"), "|");
        final TermService termService = new TermServiceImpl();

        final int idNewTerm = termService.add(new Term(termDuration));
        if (idNewTerm == -1) {
            request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "canNotCreateNewTerm");
            gotoToJSP("main/term/termCreatingModifying.jsp", request, response);
            return;
        }

        while (stringTokenizer.hasMoreTokens()) {
            final int idDiscipline = Integer.parseInt(stringTokenizer.nextToken());
            final int id = termService.setDisciplineOfTerm(idNewTerm, idDiscipline);
            if (id == -1) {
                request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "canNotAddDisciplinesOfNewTerm");
                gotoToJSP("main/term/termCreatingModifying.jsp", request, response);
                return;
            }
        }

        redirectRequest("/admin/termsList.php", request, response);
    }
}
