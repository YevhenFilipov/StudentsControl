package services;

import entity.Discipline;
import entity.Term;

import java.util.List;
import java.util.Set;

public interface TermService {

    boolean modifyTerm(int idOldTerm, Term newTerm);

    boolean delete(int id);

    int add(Term newTerm);

    List<Term> getTerms();

    Term getTerm(int id);

    Set<Discipline> getDisciplinesOfTerm(int idTerm);

    Set<Integer> getIdDisciplinesOfTerm(int idTerm);

    int setDisciplineOfTerm(int idTerm, int idDiscipline);

    boolean deleteDisciplineOfTerm(int idTerm, int idDiscipline);
}
