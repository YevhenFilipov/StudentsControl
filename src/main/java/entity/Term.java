package entity;

public class Term extends AbstractModelBean {


    private static final long serialVersionUID = 7252416991018625450L;

    private Integer id;
    private Integer duration;

    public Term() {
        super();
    }

    public Term(Integer id, Integer duration) {
        super();
        this.id = id;
        this.duration = duration;
    }

    public Term(Integer duration) {
        super();
        this.id = null;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Term{" +
                "id=" + id +
                ", duration=" + duration +
                '}';
    }
}
