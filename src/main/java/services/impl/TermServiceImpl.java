package services.impl;

import dao.TermDao;
import dao.impl.TermDaoImpl;
import entity.Discipline;
import entity.Term;
import services.TermService;

import java.util.List;
import java.util.Set;

public class TermServiceImpl implements TermService {

    @Override
    public boolean modifyTerm(int idOldTerm, Term newTerm) {
        final TermDao termDao = new TermDaoImpl();
        return termDao.update(idOldTerm, newTerm);
    }

    @Override
    public boolean delete(int id) {
        final TermDao termDao = new TermDaoImpl();
        return termDao.delete(id);
    }

    @Override
    public int add(Term newTerm) {
        final TermDao termDao = new TermDaoImpl();
        return termDao.add(newTerm);
    }

    @Override
    public List<Term> getTerms() {
        final TermDao termDao = new TermDaoImpl();
        return termDao.getTerms();
    }

    @Override
    public Term getTerm(int id) {
        final TermDao termDao = new TermDaoImpl();
        return termDao.getById(id);
    }

    @Override
    public int setDisciplineOfTerm(int idTerm, int idDiscipline) {
        final TermDao termDao = new TermDaoImpl();
        return termDao.setDisciplineOfTerm(idTerm, idDiscipline);
    }

    @Override
    public boolean deleteDisciplineOfTerm(int idTerm, int idDiscipline) {
        final TermDao termDao = new TermDaoImpl();
        return termDao.deleteDisciplineOfTerm(idTerm, idDiscipline);
    }

    @Override
    public Set<Discipline> getDisciplinesOfTerm(int idTerm) {
        final TermDao termDao = new TermDaoImpl();
        return termDao.getDisciplinesOfTerm(idTerm);
    }

    @Override
    public Set<Integer> getIdDisciplinesOfTerm(int idTerm) {
        final TermDao termDao = new TermDaoImpl();
        return termDao.getIdDisciplinesOfTerm(idTerm);
    }
}
