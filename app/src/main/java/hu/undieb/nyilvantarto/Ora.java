package hu.undieb.nyilvantarto;

import java.text.DateFormat;
import java.util.Date;

public class Ora {
    private String date;
    private int jelenlevok;

    public Ora(String date,int jelenlevok) {
        this.date = date;
        this.jelenlevok=jelenlevok;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getJelenlevok() {
        return jelenlevok;
    }

    public void setJelenlevok(int jelenlevok) {
        this.jelenlevok = jelenlevok;
    }

    @Override
    public String toString() {
        return "Ora{" +
                "date=" + date +
                ", hianyzok=" + jelenlevok +
                '}';
    }
}
