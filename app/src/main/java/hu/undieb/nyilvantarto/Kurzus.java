package hu.undieb.nyilvantarto;


import com.google.firebase.database.IgnoreExtraProperties;

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
    private List<Ora> orak;

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
