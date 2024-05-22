package hu.undieb.nyilvantarto;

import com.google.firebase.database.IgnoreExtraProperties;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
@IgnoreExtraProperties
public class Ora {
    private String date;
    private int jelenlevok;
    private List<Hallgato> hallgatok;

    public List<Hallgato> getHallgatok() {
        return hallgatok;
    }

    public void setHallgatok(List<Hallgato> hallgatok) {
        this.hallgatok = hallgatok;
    }

    public Ora(String date, int jelenlevok) {
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
