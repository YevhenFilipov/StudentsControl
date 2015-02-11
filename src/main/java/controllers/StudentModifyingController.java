package controllers;

import constants.Constants;
import entity.Student;
import services.StudentService;
import services.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StudentModifyingController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Only admin can see this page
        if (!checkAccess(request, response)) {
            return;
        }

        request.getSession().setAttribute(BACK_PAGE, "/studentsList.html");
        request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "VALIDATION_MESSAGE");
        request.setAttribute("STUDENT_BUTTON", 2);

        final Integer idStudent = Integer.parseInt(request.getParameter("id"));
        if (request.getParameter("first_name") == null) {
            final StudentService studentService = new StudentServiceImpl();
            final Student student = studentService.getStudentById(idStudent);

            request.setAttribute("student", student);
            gotoToJSP("main/student/studentsCreatingModifying.jsp", request, response);
            return;
        }

        final Student modifyingStudent = new Student();
        final StudentService studentService = new StudentServiceImpl();

        modifyingStudent.setLastName(request.getParameter("first_name"));
        modifyingStudent.setName(request.getParameter("name"));
        modifyingStudent.setGroup(request.getParameter("group"));

        final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.UK);
        final Date formattedDate = formatter.parse(request.getParameter("date"));
        java.sql.Date date = new java.sql.Date(formattedDate.getTime());
        modifyingStudent.setDate(date);
        if (studentService.studentModifying(idStudent, modifyingStudent)) {
            redirectRequest("/admin/studentsList.html", request, response);
        } else {
            request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "CanNotModifyStudent");
            gotoToJSP("main/student/studentsCreatingModifying.jsp", request, response);
        }
    }
}
