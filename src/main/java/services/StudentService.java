package services;

import entity.Student;

import java.util.List;

public interface StudentService {

    boolean studentModifying(int idStudent, Student newStudent);

    boolean studentDelete(int idStudent);

    boolean addStudent(Student newStudent);

    List<Student> getStudents();

    Student getStudentById(int id);

}
