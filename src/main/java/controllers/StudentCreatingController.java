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

public class StudentCreatingController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Only admin can see this page
        if (!checkAccess(request, response)) {
            return;
        }

        request.getSession().setAttribute(BACK_PAGE, "/studentsList.php");
        request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "VALIDATION_MESSAGE");
        request.setAttribute("STUDENT_BUTTON", 1);

        if (request.getParameter("first_name") == null) {
            gotoToJSP("main/student/studentsCreatingModifying.jsp", request, response);
            return;
        }

        final Student newStudent = new Student();
        newStudent.setLastName(request.getParameter("first_name"));
        newStudent.setName(request.getParameter("name"));
        newStudent.setGroup(request.getParameter("group"));

        final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.UK);

        final Date formattedDate = formatter.parse(request.getParameter("date"));
        java.sql.Date date = new java.sql.Date(formattedDate.getTime());
        newStudent.setDate(date);

        final StudentService studentService = new StudentServiceImpl();
        if (studentService.addStudent(newStudent))
            redirectRequest("/admin/studentsList.php", request, response);
        else {
            request.getSession().setAttribute(Constants.VALIDATION_MESSAGE, "CanNotCreateStudent");
            gotoToJSP("main/student/studentsCreatingModifying.jsp", request, response);
        }
    }
}
