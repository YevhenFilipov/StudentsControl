package services;

import entity.Discipline;

import java.util.Set;

public interface DisciplineService {

    boolean modify(int id, Discipline newDiscipline);

    boolean delete(int id);

    boolean add(Discipline newDiscipline);

    Set<Discipline> getDisciplines();

    Discipline getById(int id);

}
