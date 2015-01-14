package controllers;

import entity.Discipline;
import entity.Term;
import services.MarksService;
import services.TermService;
import services.impl.MarksServiceImpl;
import services.impl.TermServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TermsListController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.getSession().setAttribute(BACK_PAGE, "/home.php");
        request.getSession().setAttribute(VALIDATION_MESSAGE, "VALIDATION_MESSAGE");

        final TermService termService = new TermServiceImpl();

        if (request.getParameter("id_term_to_delete") != null) {
            final Integer idTermToDelete = Integer.parseInt(request.getParameter("id_term_to_delete"));
            final Set<Discipline> disciplinesOfTerm = termService.getDisciplinesOfTerm(idTermToDelete);
            final MarksService marksService = new MarksServiceImpl();
            // Delete all disciplines of term
            for (Discipline discipline : disciplinesOfTerm) {
                if (!termService.deleteDisciplineOfTerm(idTermToDelete, discipline.getId())) {
                    request.getSession().setAttribute(VALIDATION_MESSAGE, "canNotDeleteDisciplinesOfTerm");
                }
            }
            // Delete all marks of term
            if (marksService.deleteMarksByTerm(idTermToDelete)) {
                request.getSession().setAttribute(VALIDATION_MESSAGE, "CantDeleteMarks");
            }
            // Delete term
            if (!termService.delete(idTermToDelete)) {
                request.getSession().setAttribute(VALIDATION_MESSAGE, "canNotDeleteTerm");
            }
        }
        final List<Term> terms = termService.getTerms();
        request.setAttribute("terms", terms);


        final int idCurrTerm;

        if (request.getParameter("selectTerm") == null ||
                request.getParameter("selectTerm").length() == 0) {
            idCurrTerm = terms.get(terms.size() - 1).getId();
        } else {
            idCurrTerm = Integer.parseInt(request.getParameter("selectTerm"));
        }

        final int termDuration = termService.getTerm(idCurrTerm).getDuration();

        request.setAttribute("idCurrTerm", idCurrTerm);
        request.setAttribute("termDuration", termDuration);

        final List<String> nameOfDisciplines = new ArrayList<>(termService.getDisciplinesOfTerm(idCurrTerm).size());
        for (Discipline currentDiscipline : termService.getDisciplinesOfTerm(idCurrTerm)) {
            nameOfDisciplines.add(currentDiscipline.getName());
        }

        request.setAttribute("disciplines", nameOfDisciplines);

        gotoToJSP("main/term/termsList.jsp", request, response);
    }
}
