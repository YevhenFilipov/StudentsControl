package database;

import com.mysql.jdbc.Statement;
import entity.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class DBConnection {
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class);
    private Connection conn = null;
    private ResultSet rs = null;

    private static PreparedStatement loadAllRoles;
    private static PreparedStatement loadAllLogins;
    private static PreparedStatement loadAccountByLogin;
    private static PreparedStatement loadRolesById;
    private static PreparedStatement getIdAccountRoles;
    private static PreparedStatement getDisciplines;
    private static PreparedStatement getDisciplineById;
    private static PreparedStatement updateDiscipline;
    private static PreparedStatement deleteDiscipline;
    private static PreparedStatement deleteTerm;
    private static PreparedStatement addNewTerm;
    private static PreparedStatement updateTerm;
    private static PreparedStatement getIdDisciplineByIdTerm;
    private static PreparedStatement getAllTerms;
    private static PreparedStatement getTermById;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudentById;
    private static PreparedStatement addNewStudent;
    private static PreparedStatement updateStudent;
    private static PreparedStatement deleteStudent;
    private static PreparedStatement getIdPairTermDiscipline;
    private static PreparedStatement getIdPairsTermDiscipline;
    private static PreparedStatement getIdPairsTermDisciplineByDiscipline;
    private static PreparedStatement getMarksByTerm;
    private static PreparedStatement deleteMarksByStudent;
    private static PreparedStatement deleteMarksByTerm;
    private static PreparedStatement deleteMark;
    private static PreparedStatement updateMark;
    private static PreparedStatement setMark;
    private static PreparedStatement addDiscipline;
    private static PreparedStatement setDisciplineOfTerm;
    private static PreparedStatement deleteDisciplineOfTerm;

    public DBConnection(String url) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url);
            loadPreparedStatements();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private void loadPreparedStatements() {
        try {
            loadAllRoles = conn.prepareStatement("SELECT * FROM roles");
            loadAllLogins = conn.prepareStatement("SELECT login, id FROM account");
            loadAccountByLogin = conn.prepareStatement("SELECT * FROM account WHERE login = ?");
            loadRolesById = conn.prepareStatement("SELECT * FROM roles WHERE id_roles =?");
            getIdAccountRoles = conn.prepareStatement("SELECT id_role FROM account_role WHERE id_account = ?");
            getDisciplines = conn.prepareStatement("SELECT * FROM discipline");
            getDisciplineById = conn.prepareStatement("SELECT * FROM discipline WHERE id = ?");
            updateDiscipline = conn.prepareStatement("UPDATE discipline SET name = ? WHERE id = ?");
            deleteDiscipline = conn.prepareStatement("DELETE FROM discipline WHERE id = ?");
            deleteTerm = conn.prepareStatement("DELETE FROM term WHERE id = ?");
            addNewTerm = conn.prepareStatement("INSERT INTO term (duration) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            updateTerm = conn.prepareStatement("UPDATE term SET duration = ? WHERE id = ?");
            getIdDisciplineByIdTerm = conn.prepareStatement("SELECT id_discipline FROM term_discipline WHERE id_term = ?");
            getIdPairsTermDiscipline = conn.prepareStatement("SELECT id FROM term_discipline WHERE id_term = ?");
            setDisciplineOfTerm = conn.prepareStatement("INSERT INTO term_discipline (id_term, id_discipline) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            deleteDisciplineOfTerm = conn.prepareStatement("DELETE FROM term_discipline WHERE id_term = ? AND id_discipline = ?");
            deleteMarksByStudent = conn.prepareStatement("DELETE FROM marks WHERE id_student = ?");
            deleteMarksByTerm = conn.prepareStatement("DELETE FROM marks WHERE id_pair_term_discipline = ?");
            deleteMark = conn.prepareStatement("DELETE FROM marks WHERE id_student = ? AND id_pair_term_discipline = ?");
            getAllTerms = conn.prepareStatement("SELECT * FROM term");
            getTermById = conn.prepareStatement("SELECT * FROM term WHERE id = ?");
            getAllStudents = conn.prepareStatement("SELECT * FROM student");
            getStudentById = conn.prepareStatement("SELECT * FROM student WHERE id = ?");
            addNewStudent = conn.prepareStatement("INSERT INTO student (student_first_name, student_last_name, student_group, student_date) VALUES (?,?,?,?)");
            updateStudent = conn.prepareStatement("UPDATE student SET student_first_name = ?, student_last_name = ?, student_group = ?, student_date = ? WHERE id = ?");
            deleteStudent = conn.prepareStatement("DELETE FROM student WHERE id = ?");
            getIdPairTermDiscipline = conn.prepareStatement("SELECT id FROM term_discipline WHERE id_term = ? AND id_discipline = ?");
            getIdPairsTermDisciplineByDiscipline = conn.prepareStatement("SELECT id FROM term_discipline WHERE id_discipline = ?");
            getMarksByTerm = conn.prepareStatement("SELECT * FROM marks WHERE id_pair_term_discipline = ? AND id_student = ?");
            updateMark = conn.prepareStatement("UPDATE marks SET mark = ? WHERE id_pair_term_discipline = ? AND id_student = ?");
            setMark = conn.prepareStatement("INSERT INTO marks (id_pair_term_discipline, mark, id_student) VALUES (? ,?, ?)", Statement.RETURN_GENERATED_KEYS);
            addDiscipline = conn.prepareStatement("INSERT INTO discipline (name) VALUES (?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closePreparedStatements() {
        try {
            loadAllRoles.close();
            loadAllLogins.close();
            loadAccountByLogin.close();
            loadRolesById.close();
            getIdAccountRoles.close();
            getDisciplines.close();
            getDisciplineById.close();
            updateDiscipline.close();
            deleteDiscipline.close();
            deleteTerm.close();
            addNewTerm.close();
            updateTerm.close();
            getIdDisciplineByIdTerm.close();
            getAllTerms.close();
            getTermById.close();
            getAllStudents.close();
            getStudentById.close();
            addNewStudent.close();
            updateStudent.close();
            deleteStudent.close();
            getIdPairTermDiscipline.close();
            getMarksByTerm.close();
            addDiscipline.close();
            setDisciplineOfTerm.close();
            deleteDisciplineOfTerm.close();
            deleteMarksByStudent.close();
            deleteMarksByTerm.close();
            deleteMark.close();
            getIdPairsTermDiscipline.close();
            getIdPairsTermDisciplineByDiscipline.close();
            updateMark.close();
            setMark.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Role> getAllRoles() {
        rs = null;
        final List<Role> result = new LinkedList<>();
        try {
            rs = loadAllRoles.executeQuery();
            while (rs.next()) {
                final Role r = new Role();
                r.setId(rs.getInt("id_roles"));
                r.setName(rs.getString("name_roles"));
                result.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Account getAccountByLogin(String login) {
        rs = null;
        final Account result = new Account();
        try {
            loadAccountByLogin.setNString(1, login);
            rs = loadAccountByLogin.executeQuery();

            while (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setUsername(rs.getString("login"));
                result.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Role> getRolesById(int id) {
        rs = null;
        final List<Role> result = new LinkedList<>();
        try {
            loadRolesById.setInt(1, id);
            rs = loadRolesById.executeQuery();

            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id_roles"));
                role.setName(rs.getString("name_roles"));
                result.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void close() {
        closePreparedStatements();
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            LOGGER.info("close() exception " + e.getMessage());
            e.printStackTrace();
        }

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOGGER.warn("close() exception " + e.getMessage());
            }
        }

    }

    public List<Integer> getIdAccountRoles(int idAccount) {
        rs = null;
        final List<Integer> idAccountRoles = new ArrayList<>();
        try {
            getIdAccountRoles.setInt(1, idAccount);
            rs = getIdAccountRoles.executeQuery();

            while (rs.next()) {
                idAccountRoles.add(rs.getInt("id_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAccountRoles;
    }

    public Set<Discipline> getDisciplines() {
        rs = null;
        final Set<Discipline> disciplinesList = new HashSet<>();
        try {
            rs = getDisciplines.executeQuery();
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setName(rs.getString("name"));
                disciplinesList.add(discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinesList;
    }

    public Discipline getDisciplineById(int id) {
        rs = null;
        final Discipline discipline = new Discipline();
        try {
            getDisciplineById.setInt(1, id);
            rs = getDisciplineById.executeQuery();
            while (rs.next()) {
                discipline.setId(rs.getInt("id"));
                discipline.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discipline;
    }

    public boolean addDiscipline(Discipline newDiscipline) {
        try {
            //"INSERT INTO discipline (name) VALUES (?)"
            addDiscipline.setString(1, newDiscipline.getName());
            addDiscipline.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDiscipline(int idOldDiscipline, Discipline discipline) {

        try {
            //"UPDATE discipline SET name = ? WHERE id = ?"
            updateDiscipline.setString(1, discipline.getName());
            updateDiscipline.setInt(2, idOldDiscipline);
            updateDiscipline.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDiscipline(int id) {
        try {
            deleteDiscipline.setInt(1, id);
            deleteDiscipline.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTerm(int id) {
        try {
            // "DELETE FROM term WHERE id = ?"
            deleteTerm.setInt(1, id);
            deleteTerm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int addNewTerm(Term newTerm) {
        int id = -1;
        rs = null;
        try {
            //"INSERT INTO term (duration) VALUES (?), Statement.RETURN_GENERATED_KEYS"
            addNewTerm.setInt(1, newTerm.getDuration());
            addNewTerm.execute();
            rs = addNewTerm.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            //return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateTerm(int idOldTerm, Term newTerm) {
        try {
            //"UPDATE term SET duration = ? WHERE id = ?"
            updateTerm.setInt(1, newTerm.getDuration());
            updateTerm.setInt(2, idOldTerm);
            updateTerm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Set<Integer> getIdDisciplineOfIdTerm(int idTerm) {
        rs = null;
        final Set<Integer> idDisciplinesOfTerm = new HashSet<>();
        try {
            //SELECT id_discipline FROM term_discipline WHERE id_term = ?");
            getIdDisciplineByIdTerm.setInt(1, idTerm);
            rs = getIdDisciplineByIdTerm.executeQuery();
            while (rs.next()) {
                idDisciplinesOfTerm.add(rs.getInt("id_discipline"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idDisciplinesOfTerm;
    }

    public Set<Integer> getIdPairsTermDiscipline(int idTerm) {
        rs = null;
        final Set<Integer> idPairsTermDiscipline = new HashSet<>();
        //"SELECT id FROM term_discipline WHERE id_term = ?"
        try {
            getIdPairsTermDiscipline.setInt(1, idTerm);
            rs = getIdPairsTermDiscipline.executeQuery();
            while (rs.next()) {
                idPairsTermDiscipline.add(rs.getInt("id"));
            }
            return idPairsTermDiscipline;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_SET;
        }
    }

    public Set<Integer> getIdPairsTermDisciplineByDiscipline(int idDiscipline) {

        rs = null;
        final Set<Integer> idPairsTermDiscipline = new HashSet<>();
        //"SELECT id FROM term_discipline WHERE id_discipline = ?"
        try {
            getIdPairsTermDiscipline.setInt(1, idDiscipline);
            rs = getIdPairsTermDiscipline.executeQuery();
            while (rs.next()) {
                idPairsTermDiscipline.add(rs.getInt("id"));
            }
            return idPairsTermDiscipline;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_SET;
        }
    }


    public List<Term> getAllTerms() {
        rs = null;
        final List<Term> allTerms = new LinkedList<>();
        try {
            // "SELECT * FROM term"
            rs = getAllTerms.executeQuery();
            while (rs.next()) {
                Term temp = new Term();
                temp.setId(rs.getInt("id"));
                temp.setDuration(rs.getInt("duration"));
                allTerms.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTerms;
    }

    public Term getTermById(int id) {
        rs = null;
        final Term resTerm = new Term();
        try {
            // "SELECT * FROM term WHERE id = ?"
            getTermById.setInt(1, id);
            rs = getTermById.executeQuery();
            while (rs.next()) {
                resTerm.setId(rs.getInt("id"));
                resTerm.setDuration(rs.getInt("duration"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resTerm;
    }

    public List<Student> getAllStudents() {
        rs = null;
        final List<Student> allStudents = new ArrayList<>();
        try {
            //"SELECT * FROM student"
            rs = getAllStudents.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("student_first_name"));
                student.setLastName(rs.getString("student_last_name"));
                student.setGroup(rs.getString("student_group"));
                student.setDate(rs.getDate("student_date"));
                allStudents.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;
    }

    public Student getStudentById(int id) {
        rs = null;
        final Student student = new Student();
        try {
            //"SELECT * FROM student WHERE id = ?"
            getStudentById.setInt(1, id);
            rs = getStudentById.executeQuery();
            while (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("student_first_name"));
                student.setLastName(rs.getString("student_last_name"));
                student.setGroup(rs.getString("student_group"));
                student.setDate(rs.getDate("student_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public boolean addStudent(Student newStudent) {
        try {
            //"INSERT INTO student (student_first_name, student_last_name, student_group, student_date) VALUES (?,?,?,?)"
            addNewStudent.setString(1, newStudent.getName());
            addNewStudent.setString(2, newStudent.getLastName());
            addNewStudent.setString(3, newStudent.getGroup());
            addNewStudent.setDate(4, newStudent.getDate());
            addNewStudent.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudentById(int idSudent, Student newStudent) {
        try {
            //"UPDATE student SET name = ?, first_name = ?, group = ?, date = ? WHERE id = ?"
            updateStudent.setString(1, newStudent.getName());
            updateStudent.setString(2, newStudent.getLastName());
            updateStudent.setString(3, newStudent.getGroup());
            updateStudent.setDate(4, newStudent.getDate());
            updateStudent.setInt(5, idSudent);
            updateStudent.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer getIdPairTermDiscipline(int idTerm, int idDiscipline) {

        rs = null;
        Integer result = null;
        try {
            getIdPairTermDiscipline.setInt(1, idTerm);
            getIdPairTermDiscipline.setInt(2, idDiscipline);
            rs = getIdPairTermDiscipline.executeQuery();
            while (rs.next()) {
                result = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Marks getMarksByTerm(int idPairTermDiscipline, int idStudent) {

        rs = null;
        final Marks currMark = new Marks();
        try {
            getMarksByTerm.setInt(1, idPairTermDiscipline);
            getMarksByTerm.setInt(2, idStudent);
            rs = getMarksByTerm.executeQuery();
            while (rs.next()) {
                currMark.setId(rs.getInt("id"));
                currMark.setIdPairTermDiscipline(rs.getInt("id_pair_term_discipline"));
                currMark.setMark(rs.getInt("mark"));
                currMark.setIdStudent(rs.getInt("id_student"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currMark;
    }

    public boolean deleteMarksByStudent(int idStudent) {

        //"DELETE FROM marks WHERE id_student = ?"
        try {
            deleteMarksByStudent.setInt(1, idStudent);
            deleteMarksByStudent.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMarksByTerm(int idPairTermDiscipline) {

        try {
            deleteMarksByTerm.setInt(1, idPairTermDiscipline);
            deleteMarksByTerm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMark(int idStudent, int idPairTermDiscipline) {

        // "DELETE FROM marks WHERE id_student = ? AND id_pair_term_discipline = ?"
        try {
            deleteMark.setInt(1, idStudent);
            deleteMark.setInt(2, idPairTermDiscipline);
            deleteMark.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMark(Marks mark) {

        //"UPDATE marks SET mark = ? WHERE id_pair_term_discipline = ? AND id_student = ?"
        try {
            updateMark.setInt(1, mark.getMark());
            updateMark.setInt(2, mark.getIdPairTermDiscipline());
            updateMark.setInt(3, mark.getIdStudent());
            updateMark.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int setMark(Marks mark) {

        rs = null;
        int id = -1;
        //"INSERT INTO marks (id_pair_term_discipline, mark, id_student) VALUES (? ,?, ?)", Statement.RETURN_GENERATED_KEYS)
        try {
            setMark.setInt(1, mark.getIdPairTermDiscipline());
            setMark.setInt(2, mark.getMark());
            setMark.setInt(3, mark.getIdStudent());
            setMark.execute();
            rs = setMark.getGeneratedKeys();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean deleteStudentById(int idStudent) {

        try {
            //"DELETE FROM student WHERE id = ?"
            deleteStudent.setInt(1, idStudent);
            deleteStudent.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int setDisciplineOfTerm(int idTerm, int idDiscipline) {

        // "INSERT INTO term_discipline (id_term, id_discipline) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS
        rs = null;
        int id = -1;
        try {
            setDisciplineOfTerm.setInt(1, idTerm);
            setDisciplineOfTerm.setInt(2, idDiscipline);
            setDisciplineOfTerm.execute();
            rs = setDisciplineOfTerm.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean deleteDisciplineOfTerm(int idTerm, int idDiscipline) {

        // "DELETE FROM term_discipline WHERE id_term = ? AND id_discipline = ?"
        try {
            deleteDisciplineOfTerm.setInt(1, idTerm);
            deleteDisciplineOfTerm.setInt(2, idDiscipline);
            deleteDisciplineOfTerm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
