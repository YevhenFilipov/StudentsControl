package services.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import entity.Student;
import services.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Override
    public boolean studentModifying(int idStudent, Student newStudent) {
        final StudentDao studentDao = new StudentDaoImpl();
        return studentDao.studentModifying(idStudent, newStudent);
    }

    @Override
    public boolean studentDelete(int idStudent) {
        final StudentDao studentDao = new StudentDaoImpl();
        return studentDao.deleteStudentById(idStudent);
    }

    @Override
    public boolean addStudent(Student newStudent) {
        final StudentDao studentDao = new StudentDaoImpl();
        return studentDao.addStudent(newStudent);
    }

    @Override
    public List<Student> getStudents() {
        final StudentDao studentDao = new StudentDaoImpl();
        return studentDao.getStudents();
    }

    @Override
    public Student getStudentById(int id) {
        final StudentDao studentDao = new StudentDaoImpl();
        return studentDao.getStudentById(id);
    }
}
