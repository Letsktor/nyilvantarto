package hu.undieb.nyilvantarto;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;
@IgnoreExtraProperties
public class Ora {
    private String date;
    private int jelenlevok;
    private ArrayList<Hallgato> hallgatok=new ArrayList<>();

    public ArrayList<Hallgato> getHallgatok() {
        return hallgatok;
    }

    public void setHallgatok(ArrayList<Hallgato> hallgatok) {
        this.hallgatok = hallgatok;
    }

    public Ora(String date, int jelenlevok) {
        this.date = date;
        this.jelenlevok=jelenlevok;
    }
    public Ora()
    {

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
