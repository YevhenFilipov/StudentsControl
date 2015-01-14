package dao;

import java.util.List;

public interface MarksDao {

    List<entity.MarkValue> getMarksByTerm(int idStudent, int idTerm);

    boolean deleteMarksByStudent(int idStudent);

    boolean deleteMarksByTerm(int idTerm);

    boolean deleteMarksByDiscipline(int idDiscipline);

    boolean deleteMarksByTermDiscipline(int idTerm, int idDiscipline);

    boolean deleteMark(int idStudent, int idTerm, int idDiscipline);

    boolean updateMark(int idStudent, int idTerm, int idDiscipline, int mark);

    int setMark(int idStudent, int idTerm, int idDiscipline, int mark);
}

