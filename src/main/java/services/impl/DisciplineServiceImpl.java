package services.impl;

import dao.DisciplineDao;
import dao.impl.DisciplineDaoImpl;
import entity.Discipline;
import services.DisciplineService;

import java.util.Set;

public class DisciplineServiceImpl implements DisciplineService {
    @Override
    public boolean modify(int id, Discipline newDiscipline) {
        final DisciplineDao disciplineDao = new DisciplineDaoImpl();
        return disciplineDao.update(id, newDiscipline);
    }

    @Override
    public boolean delete(int id) {
        final DisciplineDao disciplineDao = new DisciplineDaoImpl();
        return disciplineDao.delete(id);
    }

    @Override
    public boolean add(Discipline newDiscipline) {
        final DisciplineDao disciplineDao = new DisciplineDaoImpl();
        return disciplineDao.add(newDiscipline);
    }

    @Override
    public Set<Discipline> getDisciplines() {
        final DisciplineDao disciplineDao = new DisciplineDaoImpl();
        return disciplineDao.getDisciplines();
    }

    @Override
    public Discipline getById(int id) {
        final DisciplineDao disciplineDao = new DisciplineDaoImpl();
        return disciplineDao.getById(id);
    }
}
