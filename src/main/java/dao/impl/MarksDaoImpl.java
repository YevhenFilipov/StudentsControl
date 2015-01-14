package dao.impl;

import dao.MarksDao;
import database.DataService;
import entity.Discipline;
import entity.MarkValue;
import entity.Marks;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class MarksDaoImpl implements MarksDao {


    @Override
    public List<MarkValue> getMarksByTerm(int idStudent, int idTerm) {
        final DataService dataService = new DataService();
        final List<MarkValue> marksByTerm = new LinkedList<>();
        final Set<Discipline> disciplinesOfTerm = dataService.getDisciplinesOfTerm(idTerm);

        for (Discipline curDiscipline : disciplinesOfTerm) {
            int idPairTermDiscipline = dataService.getIdPairTermDiscipline(idTerm, curDiscipline.getId());
            final Marks curMark = dataService.getMarksByTerm(idPairTermDiscipline, idStudent);

            Integer mark = curMark.getMark();
            if (mark == null) {
                mark = 0;
            }
            final MarkValue currMarkValue = new MarkValue(curDiscipline.getId(), curDiscipline.getName(), mark);
            marksByTerm.add(currMarkValue);
        }
        return marksByTerm;
    }

    @Override
    public boolean deleteMarksByStudent(int idStudent) {
        final DataService dataService = new DataService();
        return dataService.deleteMarksByStudent(idStudent);
    }

    @Override
    public boolean deleteMarksByDiscipline(int idDiscipline) {
        final DataService dataService = new DataService();
        return dataService.deleteMarksByDiscipline(idDiscipline);
    }

    @Override
    public boolean deleteMarksByTerm(int idTerm) {
        final DataService dataService = new DataService();
        return dataService.deleteMarksByTerm(idTerm);
    }

    @Override
    public boolean deleteMarksByTermDiscipline(int idTerm, int idDiscipline) {
        final DataService dataService = new DataService();
        return dataService.deleteMarksByTermDiscipline(idTerm, idDiscipline);
    }

    @Override
    public boolean deleteMark(int idStudent, int idTerm, int idDiscipline) {
        final DataService dataService = new DataService();
        final Integer idPair = dataService.getIdPairTermDiscipline(idTerm, idDiscipline);
        return idPair != null && dataService.deleteMark(idStudent, idPair);
    }

    @Override
    public boolean updateMark(int idStudent, int idTerm, int idDiscipline, int mark) {
        final DataService dataService = new DataService();
        final int idPairTermDiscipline = dataService.getIdPairTermDiscipline(idTerm, idDiscipline);
        final Marks updatedMark = new Marks(idStudent, idPairTermDiscipline, mark);
        return dataService.updateMark(updatedMark);
    }

    @Override
    public int setMark(int idStudent, int idTerm, int idDiscipline, int mark) {
        final DataService dataService = new DataService();
        final int idPairTermDiscipline = dataService.getIdPairTermDiscipline(idTerm, idDiscipline);
        final Marks newMark = new Marks(idStudent, idPairTermDiscipline, mark);
        return dataService.setMark(newMark);
    }
}
