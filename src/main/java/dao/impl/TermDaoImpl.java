package dao.impl;

import dao.TermDao;
import database.DataService;
import entity.Discipline;
import entity.Term;

import java.util.List;
import java.util.Set;

public class TermDaoImpl implements TermDao {
    private final DataService dataService = new DataService();

    @Override
    public boolean delete(int id) {
        return dataService.deleteTerm(id);
    }

    @Override
    public int add(Term newTerm) {
        return dataService.addNewTerm(newTerm);
    }

    @Override
    public List<Term> getTerms() {
        return dataService.getAllTerms();
    }

    @Override
    public Term getById(int id) {
        return dataService.getTermById(id);
    }

    @Override
    public boolean update(int id, Term newTerm) {
        return dataService.updateTerm(id, newTerm);
    }

    @Override
    public Set<Discipline> getDisciplinesOfTerm(int idTerm) {
        return dataService.getDisciplinesOfTerm(idTerm);
    }

    @Override
    public Set<Integer> getIdDisciplinesOfTerm(int idTerm) {
        return dataService.getIdDisciplinesOfTerm(idTerm);
    }

    @Override
    public int setDisciplineOfTerm(int idTerm, int idDiscipline) {
        return dataService.setDisciplineOfTerm(idTerm, idDiscipline);
    }

    @Override
    public boolean deleteDisciplineOfTerm(int idTerm, int idDiscipline) {
        return dataService.deleteDisciplineOfTerm(idTerm, idDiscipline);
    }
}
