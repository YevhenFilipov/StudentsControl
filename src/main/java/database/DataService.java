package database;

import constants.Constants;
import entity.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataService {
    private static final Logger LOGGER = Logger
            .getLogger(DataService.class);
    private static List<DBConnection> conPool = new ArrayList<DBConnection>();
    private static final Object monitor = new Object();

    public boolean init() {
        try {
            LOGGER.info("init database");
            for (int i = 0; i < Constants.CONNECTING_POOL_SIZE; i++) {
                newConnection();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public DBConnection getDBConnection() {
        synchronized (monitor) {
            if (conPool.isEmpty()) {
                newConnection();
            }
            DBConnection conn = conPool.remove(0);
            return conn;
        }
    }

    public void putDBConnection(DBConnection conn) {
        synchronized (monitor) {
            conPool.add(conn);
        }
    }

    private void newConnection() {
        DBConnection conn = new DBConnection(Constants.CONNECTING_URL);
        synchronized (monitor) {
            conPool.add(conn);
        }
    }

    public List<Role> getAllRoles() {
        final DBConnection conn = getDBConnection();
        final List<Role> result = conn.getAllRoles();
        this.putDBConnection(conn);
        return result;

    }

    public Account getAccountByLogin(String login) {
        final DBConnection conn = getDBConnection();
        final Account result = conn.getAccountByLogin(login);
        this.putDBConnection(conn);
        return result;
    }

    public List<Role> getRolesById(int id) {
        final DBConnection conn = getDBConnection();
        final List<Role> result = conn.getRolesById(id);
        this.putDBConnection(conn);
        return result;

    }

    public List<Integer> getIdAccountRoles(int idAccount) {
        final DBConnection conn = getDBConnection();
        final List<Integer> idAccountRoles = conn.getIdAccountRoles(idAccount);
        this.putDBConnection(conn);
        return idAccountRoles;
    }

    public Set<Discipline> getDisciplines() {
        final DBConnection conn = getDBConnection();
        final Set<Discipline> disciplinesList = conn.getDisciplines();
        this.putDBConnection(conn);
        return disciplinesList;
    }

    public Discipline getDisciplineById(int id) {
        final DBConnection conn = getDBConnection();
        final Discipline discipline = conn.getDisciplineById(id);
        this.putDBConnection(conn);
        return discipline;
    }

    public boolean updateDiscipline(int idOldDiscipline, Discipline discipline) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.updateDiscipline(idOldDiscipline, discipline);
        this.putDBConnection(conn);
        return result;
    }

    public boolean deleteDiscipline(int id) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.deleteDiscipline(id);
        this.putDBConnection(conn);
        return result;
    }

    public boolean deleteTerm(int id) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.deleteTerm(id);
        this.putDBConnection(conn);
        return result;
    }

    public boolean updateTerm(int idOldTerm, Term newTerm) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.updateTerm(idOldTerm, newTerm);
        this.putDBConnection(conn);
        return result;
    }

    public int addNewTerm(Term newTerm) {
        final DBConnection conn = getDBConnection();
        final int result = conn.addNewTerm(newTerm);
        this.putDBConnection(conn);
        return result;
    }

    public List<Term> getAllTerms() {
        final DBConnection conn = getDBConnection();
        final List<Term> allTerms = conn.getAllTerms();
        this.putDBConnection(conn);
        return allTerms;
    }

    public Term getTermById(int id) {
        final DBConnection conn = getDBConnection();
        final Term resTerm = conn.getTermById(id);
        this.putDBConnection(conn);
        return resTerm;
    }

    public List<Student> getAllStudents() {
        final DBConnection conn = getDBConnection();
        final List<Student> result = conn.getAllStudents();
        this.putDBConnection(conn);
        return result;
    }

    public Student getStudentById(int id) {
        final DBConnection conn = getDBConnection();
        final Student result = conn.getStudentById(id);
        this.putDBConnection(conn);
        return result;
    }

    public boolean addStudent(Student newStudent) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.addStudent(newStudent);
        this.putDBConnection(conn);
        return result;
    }

    public boolean updateStudentById(int idStudent, Student newStudent) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.updateStudentById(idStudent, newStudent);
        this.putDBConnection(conn);
        return result;
    }

    public boolean deleteStudentById(int idStudent) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.deleteStudentById(idStudent);
        this.putDBConnection(conn);
        return result;
    }

    public Integer getIdPairTermDiscipline(int idTerm, int idDiscipline) {
        final DBConnection conn = getDBConnection();
        final Integer result = conn.getIdPairTermDiscipline(idTerm, idDiscipline);
        this.putDBConnection(conn);
        return result;
    }

    public Marks getMarksByTerm(int idPairTermDiscipline, int idStudent) {
        final DBConnection conn = getDBConnection();
        final Marks result = conn.getMarksByTerm(idPairTermDiscipline, idStudent);
        this.putDBConnection(conn);
        return result;
    }

    public boolean updateMark(Marks mark) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.updateMark(mark);
        this.putDBConnection(conn);
        return result;
    }

    public int setMark(Marks mark) {
        final DBConnection conn = getDBConnection();
        final int result = conn.setMark(mark);
        this.putDBConnection(conn);
        return result;
    }

    public boolean deleteMarksByStudent(int idStudent) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.deleteMarksByStudent(idStudent);
        this.putDBConnection(conn);
        return result;
    }

    public boolean deleteMarksByTerm(int idTerm) {
        final DBConnection conn = getDBConnection();
        final Set<Integer> idPairsTermDiscipline = conn.getIdPairsTermDiscipline(idTerm);
        boolean result = true;
        for (Integer idPair : idPairsTermDiscipline) {
            result = result & conn.deleteMarksByTerm(idPair);
        }
        this.putDBConnection(conn);
        return result;
    }

    public boolean deleteMarksByTermDiscipline(int idTerm, int idDiscipline) {
        final DBConnection conn = getDBConnection();
        final Integer idPair = conn.getIdPairTermDiscipline(idTerm, idDiscipline);
        boolean result = conn.deleteMarksByTerm(idPair);
        this.putDBConnection(conn);
        return result;
    }

    public boolean deleteMark(int idStudent, int idPairTermDiscipline) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.deleteMark(idStudent, idPairTermDiscipline);
        this.putDBConnection(conn);
        return result;
    }

    public boolean deleteMarksByDiscipline(int idDiscipline) {
        final DBConnection conn = getDBConnection();
        final Set<Integer> idPairTermDiscipline = conn.getIdPairsTermDisciplineByDiscipline(idDiscipline);
        boolean result = true;
        for (Integer currentId : idPairTermDiscipline) {
            result = result && conn.deleteMarksByTerm(currentId);
        }
        this.putDBConnection(conn);
        return result;
    }


    public Set<Discipline> getDisciplinesOfTerm(int idTerm) {
        final Set<Discipline> resultDisciplines = new HashSet<>();
        final DBConnection conn = getDBConnection();
        final Set<Integer> idsDisciplines = conn.getIdDisciplineOfIdTerm(idTerm);
        for (int currId : idsDisciplines) {
            resultDisciplines.add(conn.getDisciplineById(currId));
        }
        this.putDBConnection(conn);
        return resultDisciplines;
    }

    public Set<Integer> getIdDisciplinesOfTerm(int idTerm) {
        final DBConnection conn = getDBConnection();
        final Set<Integer> idsDisciplines = conn.getIdDisciplineOfIdTerm(idTerm);
        this.putDBConnection(conn);
        return idsDisciplines;
    }


    public boolean addDiscipline(Discipline newDiscipline) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.addDiscipline(newDiscipline);
        this.putDBConnection(conn);
        return result;
    }

    public int setDisciplineOfTerm(int idTerm, int idDiscipline) {
        final DBConnection conn = getDBConnection();
        final int result = conn.setDisciplineOfTerm(idTerm, idDiscipline);
        this.putDBConnection(conn);
        return result;
    }

    public boolean deleteDisciplineOfTerm(int idTerm, int idDiscipline) {
        final DBConnection conn = getDBConnection();
        final boolean result = conn.deleteDisciplineOfTerm(idTerm, idDiscipline);
        this.putDBConnection(conn);
        return result;
    }

    public void close() {
        for (DBConnection conn : conPool) {
            conn.close();
        }
    }
}
