package controllers;

import entity.Student;
import services.MarksService;
import services.StudentService;
import services.impl.MarksServiceImpl;
import services.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.StringTokenizer;

public class StudentsListController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.getSession().setAttribute(BACK_PAGE, "/home.php");

        final StudentService studentService = new StudentServiceImpl();
        final String idStudentsToDelete = request.getParameter("ids");

        if (idStudentsToDelete != null && !idStudentsToDelete.isEmpty()) {
            final StringTokenizer stringTokenizer = new StringTokenizer(idStudentsToDelete, "|");
            final MarksService marksService = new MarksServiceImpl();
            while (stringTokenizer.hasMoreTokens()) {
                final int idStudent = Integer.parseInt(stringTokenizer.nextToken());
                studentService.studentDelete(idStudent);
                marksService.deleteMarksByStudent(idStudent);
            }
        }

        final List<Student> studentList = studentService.getStudents();
        request.setAttribute("students", studentList);

        gotoToJSP("main/student/studentsList.jsp", request, response);
    }
}
