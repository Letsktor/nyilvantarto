package hu.undieb.nyilvantarto;

import java.util.List;

public class TimeTable {
    private String day;
    private int oraKezdete;
    private String oraNev;
    private List<Student> students;

    public TimeTable(String day, int oraKezdete, String oraNev) {
        this.day = day;
        this.oraKezdete = oraKezdete;
        this.oraNev = oraNev;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getOraKezdete() {
        return oraKezdete;
    }

    public void setOraKezdete(int oraKezdete) {
        this.oraKezdete = oraKezdete;
    }

    public String getOraNev() {
        return oraNev;
    }

    public void setOraNev(String oraNev) {
        this.oraNev = oraNev;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
