package dao;

import entity.Discipline;

import java.util.Set;

public interface DisciplineDao {

    Set<Discipline> getDisciplines();

    Discipline getById(int id);

    public boolean add(Discipline newDiscipline);

    boolean update(int idOldDiscipline, Discipline discipline);

    boolean delete(int id);

}
