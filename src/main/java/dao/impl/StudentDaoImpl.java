package dao.impl;

import dao.StudentDao;
import database.DataService;
import entity.Student;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> getStudents() {
        final DataService dataService = new DataService();
        return dataService.getAllStudents();
    }

    @Override
    public Student getStudentById(int id) {
        final DataService dataService = new DataService();
        return dataService.getStudentById(id);
    }

    @Override
    public boolean addStudent(Student newStudent) {
        final DataService dataService = new DataService();
        return dataService.addStudent(newStudent);
    }

    @Override
    public boolean studentModifying(int idStudent, Student newStudent) {
        final DataService dataService = new DataService();
        return dataService.updateStudentById(idStudent, newStudent);
    }

    @Override
    public boolean deleteStudentById(int idStudent) {
        final DataService dataService = new DataService();
        return dataService.deleteStudentById(idStudent);
    }

}
