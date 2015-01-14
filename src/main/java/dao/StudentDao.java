package dao;

import entity.Student;

import java.util.List;

public interface StudentDao {

    List<Student> getStudents();

    Student getStudentById(int id);

    boolean addStudent(Student newStudent);

    boolean studentModifying(int idStudent, Student newStudent);

    boolean deleteStudentById(int idStudent);
}
