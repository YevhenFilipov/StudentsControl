package controllers;

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
import java.util.List;

public class StudentProgressController extends AbstractWebtasksServletHandler {

    private Integer idCurrTerm = new TermServiceImpl().getTerms().get(0).getId();

    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.getSession().setAttribute(BACK_PAGE, "/studentsList.html");
        request.setAttribute("studentProgressButton", 1);

        final StudentService studentService = new StudentServiceImpl();
        final TermService termService = new TermServiceImpl();
        Integer idSelectedTerm;
        final Integer idSelectedStudent;
        final Student currStudent;
        final List<Term> allTerms;

        try {
            idSelectedTerm = Integer.parseInt(request.getParameter("selectTerm"));
            idCurrTerm = idSelectedTerm;
        } catch (Exception e) {
            idSelectedTerm = idCurrTerm;
        }

        request.setAttribute("idCurrTerm", idCurrTerm);

        idSelectedStudent = Integer.parseInt(request.getParameter("id"));
        currStudent = studentService.getStudentById(idSelectedStudent);
        request.setAttribute("student", currStudent);


        allTerms = termService.getTerms();
        request.setAttribute("allTerms", allTerms);

        final MarksService marksService = new MarksServiceImpl();
        final List<MarkValue> marksByTerm = marksService.getMarksByTerm(idSelectedStudent, idSelectedTerm);
        request.setAttribute("marksByTerm", marksByTerm);

        final MarkValueService markValueService = new MarkValueService();

        final Double meanMark = markValueService.getMeanMark(marksByTerm) /** 1.00*/;
        request.setAttribute("meanMark", meanMark);

        gotoToJSP("main/student/studentsProgress.jsp", request, response);

    }
}
