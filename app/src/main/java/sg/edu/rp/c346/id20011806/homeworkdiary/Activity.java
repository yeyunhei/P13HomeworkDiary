package sg.edu.rp.c346.id20011806.homeworkdiary;

public class Activity {

    private int id;
    private String description;
    private String addDate;
    private String dueDate;
    private int subjectid;

    public Activity(String description, String addDate, String dueDate, int subjectid) {
        this.description = description;
        this.addDate = addDate;
        this.dueDate = dueDate;
        this.subjectid = subjectid;
    }

    public Activity(int id, String description, String addDate, String dueDate, int subjectid) {
        this.id = id;
        this.description = description;
        this.addDate = addDate;
        this.dueDate = dueDate;
        this.subjectid = subjectid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }
}
