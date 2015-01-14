package dao;

import entity.Discipline;
import entity.Term;

import java.util.List;
import java.util.Set;

public interface TermDao {

    boolean update(int id, Term newTerm);

    boolean delete(int id);

    List<Term> getTerms();

    Term getById(int id);

    int add(Term newTerm);

    Set<Discipline> getDisciplinesOfTerm(int idTerm);

    Set<Integer> getIdDisciplinesOfTerm(int idTerm);

    int setDisciplineOfTerm(int idTerm, int idDiscipline);

    boolean deleteDisciplineOfTerm(int idTerm, int idDiscipline);
}
