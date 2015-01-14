package dao.impl;

import dao.DisciplineDao;
import database.DataService;
import entity.Discipline;

import java.util.Set;

public class DisciplineDaoImpl implements DisciplineDao {

    @Override
    public Set<Discipline> getDisciplines() {
        final DataService conn = new DataService();
        return conn.getDisciplines();
    }

    @Override
    public Discipline getById(int id) {
        final DataService conn = new DataService();
        return conn.getDisciplineById(id);
    }

    @Override
    public boolean add(Discipline newDiscipline) {
        final DataService conn = new DataService();
        return conn.addDiscipline(newDiscipline);
    }

    @Override
    public boolean delete(int id) {
        final DataService conn = new DataService();
        return conn.deleteDiscipline(id);
    }

    @Override
    public boolean update(int idOldDiscipline, Discipline discipline) {
        final DataService conn = new DataService();
        discipline.setId(idOldDiscipline);
        return conn.updateDiscipline(idOldDiscipline, discipline);
    }

}
