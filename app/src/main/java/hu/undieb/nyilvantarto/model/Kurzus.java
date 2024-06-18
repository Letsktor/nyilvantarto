package hu.undieb.nyilvantarto.model;


import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;
@IgnoreExtraProperties
public class Kurzus {
    public String getKurzusNev() {
        return kurzusNev;
    }

    public void setKurzusNev(String kurzusNev) {
        this.kurzusNev = kurzusNev;
    }

    private String kurzusNev;
    private List<Ora> orak=new ArrayList<>();

    public List<Hallgato> getHallgatok() {
        return hallgatok;
    }

    public void setHallgatok(List<Hallgato> hallgatok) {
        this.hallgatok = hallgatok;
    }

    private List<Hallgato> hallgatok=new ArrayList<>();
    public List<Ora> getOrak() {
        return orak;
    }

    public String getUserID() {
        return userID;
    }

    private String userID;

    @Override
    public String toString() {
        return "Kurzus{" +
                "kurzusNev='" + kurzusNev + '\'' +
                ", orak=" + orak +
                ", userID='" + userID + '\'' +
                '}';
    }

    public Kurzus(String kurzusNev) {
        this.kurzusNev = kurzusNev;

    }
    public Kurzus()
    {
    }
}
