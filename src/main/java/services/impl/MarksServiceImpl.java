package services.impl;

import dao.MarksDao;
import dao.impl.MarksDaoImpl;
import entity.MarkValue;
import services.MarksService;

import java.util.List;

public class MarksServiceImpl implements MarksService {
    @Override
    public List<MarkValue> getMarksByTerm(int idStudent, int idTerm) {
        final MarksDao marksDao = new MarksDaoImpl();
        return marksDao.getMarksByTerm(idStudent, idTerm);
    }

    @Override
    public boolean deleteMarksByStudent(int idStudent) {
        final MarksDao marksDao = new MarksDaoImpl();
        return marksDao.deleteMarksByStudent(idStudent);
    }

    @Override
    public boolean deleteMarksByDiscipline(int idDiscipline) {
        final MarksDao marksDao = new MarksDaoImpl();
        return marksDao.deleteMarksByDiscipline(idDiscipline);
    }

    @Override
    public boolean deleteMarksByTerm(int idTerm) {
        final MarksDao marksDao = new MarksDaoImpl();
        return marksDao.deleteMarksByTerm(idTerm);
    }

    @Override
    public boolean deleteMarksByTermDiscipline(int idTerm, int idDiscipline) {
        final MarksDao marksDao = new MarksDaoImpl();
        return marksDao.deleteMarksByTermDiscipline(idTerm, idDiscipline);
    }

    @Override
    public boolean deleteMark(int idStudent, int idTerm, int idDiscipline) {
        final MarksDao marksDao = new MarksDaoImpl();
        return marksDao.deleteMark(idStudent, idTerm, idDiscipline);
    }

    @Override
    public boolean updateMark(int idStudent, int idTerm, int idDiscipline, int mark) {
        final MarksDao marksDao = new MarksDaoImpl();
        return marksDao.updateMark(idStudent, idTerm, idDiscipline, mark);
    }

    @Override
    public int setMark(int idStudent, int idTerm, int idDiscipline, int mark) {
        final MarksDao marksDao = new MarksDaoImpl();
        return marksDao.setMark(idStudent, idTerm, idDiscipline, mark);
    }
}
