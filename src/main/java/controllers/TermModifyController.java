package controllers;

import constants.Constants;
import entity.Term;
import services.DisciplineService;
import services.MarksService;
import services.TermService;
import services.impl.DisciplineServiceImpl;
import services.impl.MarksServiceImpl;
import services.impl.TermServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class TermModifyController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Only admin can see this page
        if (!checkAccess(request, response)) {
            return;
        }

        request.getSession().setAttribute(BACK_PAGE, "/termsList.html");
        request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "VALIDATION_MESSAGE");
        request.setAttribute("TERM_BUTTON", 2);

        final Integer idTerm = Integer.parseInt(request.getParameter("id"));
        final TermService termService = new TermServiceImpl();

        request.setAttribute("term", termService.getTerm(idTerm));

        final DisciplineService disciplineService = new DisciplineServiceImpl();
        request.setAttribute("disciplines", disciplineService.getDisciplines());
        request.setAttribute("selectedDisciplines", termService.getDisciplinesOfTerm(idTerm));
        // If user wants to modify term
        if (request.getParameter("id_disciplines") != null) {
            final Term term = new Term();
            term.setDuration(Integer.parseInt(request.getParameter("term_duration")));
            if (!termService.modifyTerm(idTerm, term)) {
                request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "CanNotModifyTerm");
            }
            final Set<Integer> idDisciplinesOfTerm = new TreeSet<>(termService.getIdDisciplinesOfTerm(idTerm));
            final StringTokenizer stringTokenizer = new StringTokenizer(request.getParameter("id_disciplines"), "|");

            while (stringTokenizer.hasMoreTokens()) {
                final Integer currentDisciplineId = Integer.parseInt(stringTokenizer.nextToken());
                if (!idDisciplinesOfTerm.remove(currentDisciplineId)) {
                    if (termService.setDisciplineOfTerm(idTerm, currentDisciplineId) == -1) {
                        request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "canNotAddDisciplinesOfNewTerm");
                        break;
                    }
                }
            }
            final MarksService marksService = new MarksServiceImpl();
            for (int id : idDisciplinesOfTerm) {
                // Deletes all marks from this term and this discipline
                if (!marksService.deleteMarksByTermDiscipline(idTerm, id)) {
                    request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "CantDeleteMarks");
                    break;
                }
                // Deletes waste disciplines
                if (!termService.deleteDisciplineOfTerm(idTerm, id)) {
                    request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "canNotDeleteDisciplinesOfTerm");
                    break;
                }
            }

            if (!termService.modifyTerm(idTerm, term)) {
                request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "canNotModifyTerm");
            }
            redirectRequest("/admin/termsList.html", request, response);
            return;
        }

        gotoToJSP("main/term/termCreatingModifying.jsp", request, response);
    }
}
