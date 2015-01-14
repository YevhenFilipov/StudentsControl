package entity;


public class MarkValue extends AbstractModelBean {
    private int idMarkName;
    private String markName = null;
    private Integer markValue = null;

    public MarkValue() {
    }

    public MarkValue(int idMarkName, String markName, Integer markValue) {
        this.idMarkName = idMarkName;
        this.markName = markName;
        this.markValue = markValue;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public Integer getMarkValue() {
        return markValue;
    }

    public void setMarkValue(Integer markValue) {
        this.markValue = markValue;
    }

    public int getIdMarkName() {
        return idMarkName;
    }

    public void setIdMarkName(int idMarkName) {
        this.idMarkName = idMarkName;
    }

    @Override
    public String toString() {
        return "MarkValue{" +
                "idMarkName=" + idMarkName +
                ", markName='" + markName + '\'' +
                ", markValue=" + markValue +
                '}';
    }
}


