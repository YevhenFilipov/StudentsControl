package entity;

public class Student extends AbstractModelIdName {

    private static final long serialVersionUID = -5638726927853322152L;

    private String lastName;
    private String group;
    private StudentDate date;

    public Student() {
        super();
    }

    public Student(Integer id, String name, String lastName, String group, StudentDate date) {
        super(id, name);
        this.lastName = lastName;
        this.group = group;
        this.date = date;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String firstName) {
        this.lastName = firstName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public StudentDate getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = new StudentDate(date.getTime());
    }
}
