package controllers;

import constants.Constants;
import entity.MarkValue;
import entity.Student;
import entity.Term;
import services.MarksService;
import services.StudentService;
import services.TermService;
import services.impl.MarkValueService;
import services.impl.MarksServiceImpl;
import services.impl.StudentServiceImpl;
import services.impl.TermServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 */
public class StudentProgressModifyController extends AbstractWebtasksServletHandler {


    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Only admin can see this page
        if (!checkAccess(request, response)) {
            return;
        }

        request.setAttribute("studentProgressButton", 2);
        request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "VALIDATION_MESSAGE");

        final int idSelectedStudent = Integer.parseInt(request.getParameter("id_student"));


        Integer idFirstTerm = new TermServiceImpl().getTerms().get(0).getId();
        Integer idSelectedTerm;

        try {
            idSelectedTerm = Integer.parseInt(request.getParameter("id_term"));
            idFirstTerm = idSelectedTerm;
        } catch (Exception e) {
            idSelectedTerm = idFirstTerm;
        }

        final MarksService marksService = new MarksServiceImpl();
        final List<MarkValue> marksByTerm = marksService.getMarksByTerm(idSelectedStudent, idSelectedTerm);
        final Map<Integer, Integer> idDisciplineMark = new HashMap<>(marksByTerm.size());

        for (MarkValue markValue : marksByTerm) {
            idDisciplineMark.put(markValue.getIdMarkName(), markValue.getMarkValue());
        }

        request.setAttribute("marksByTerm", marksByTerm);

        final String disciplineMarks = request.getParameter("discipline_marks");
        if (disciplineMarks != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(disciplineMarks, "|");
            while (stringTokenizer.hasMoreTokens()) {
                final int idDiscipline = Integer.parseInt(stringTokenizer.nextToken());
                final int mark = Integer.parseInt(stringTokenizer.nextToken());

                if (idDisciplineMark.get(idDiscipline).equals(0)) {
                    //If mark doesn't exist
                    marksService.setMark(idSelectedStudent, idSelectedTerm, idDiscipline, mark);
                } else { // If mark exist and have to edit
                    // If user wants to clean mark
                    if (mark == 0) {
                        marksService.deleteMark(idSelectedStudent, idSelectedTerm, idDiscipline);
                    } else {
                        // If user wants to change mark
                        marksService.updateMark(idSelectedStudent, idSelectedTerm, idDiscipline, mark);
                    }
                }
            }
            request.setAttribute("selectTerm", idSelectedTerm);
            request.setAttribute("id", idSelectedStudent);
            redirectRequest("/admin/studentProgress.html?id=" + idSelectedStudent, request, response);
            return;
        }
        final StudentService studentService = new StudentServiceImpl();
        final Student currStudent = studentService.getStudentById(idSelectedStudent);
        final TermService termService = new TermServiceImpl();
        request.setAttribute("idFirstTerm", idSelectedTerm);
        request.setAttribute("student", currStudent);

        final List<Term> allTerms = termService.getTerms();
        request.setAttribute("allTerms", allTerms);

        final Double meanMark = new MarkValueService().getMeanMark(marksByTerm);
        request.setAttribute("meanMark", meanMark);

        request.getSession().setAttribute(BACK_PAGE, "/studentProgress.html?id=" + idSelectedStudent);

        gotoToJSP("main/student/studentsProgress.jsp", request, response);


    }
}
