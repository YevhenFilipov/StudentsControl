package entity;

public class Marks extends AbstractModelBean {

    private static final long serialVersionUID = 5136003827305681145L;

    private Integer id;
    private Integer idPairTermDiscipline;
    private Integer mark;
    private Integer idStudent;

    public Marks() {
        super();
    }

    public Marks(Integer idStudent, Integer idPairTermDiscipline, Integer mark) {
        super();
        this.idPairTermDiscipline = idPairTermDiscipline;
        this.mark = mark;
        this.idStudent = idStudent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPairTermDiscipline() {
        return idPairTermDiscipline;
    }

    public void setIdPairTermDiscipline(Integer idPairTermDiscipline) {
        this.idPairTermDiscipline = idPairTermDiscipline;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }


}
