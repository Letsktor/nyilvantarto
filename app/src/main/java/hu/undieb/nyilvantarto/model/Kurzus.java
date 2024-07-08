package hu.undieb.nyilvantarto.model;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;
@IgnoreExtraProperties
public class Kurzus {
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String dayOfWeek;
    public String getKurzusNev() {
        return kurzusNev;
    }

    public void setKurzusNev(String kurzusNev) {
        this.kurzusNev = kurzusNev;
    }

    private String kurzusNev;
    private List<Ora> orak=new ArrayList<>();

    public ArrayList<Hallgato> getHallgatok() {
        return hallgatok;
    }

    public void setHallgatok(ArrayList<Hallgato> hallgatok) {
        this.hallgatok = hallgatok;
    }

    private ArrayList<Hallgato> hallgatok=new ArrayList<>();
    public List<Ora> getOrak() {
        return orak;
    }

    public String getUserID() {
        return userID;
    }

    private String userID;


    public Kurzus(String kurzusNev) {
        this.kurzusNev = kurzusNev;
        this.hallgatok=new ArrayList<>();
        this.dayOfWeek=KurzusokUtils.getInstance().getDayOfTheWeek();
    }
    public Kurzus()
    {
    }

    @Override
    public String toString() {
        return "Kurzus{" +
                "kurzusNev='" + kurzusNev + '\'' +
                ", orak=" + orak +
                ", hallgatok=" + hallgatok +
                ", userID='" + userID + '\'' +
                '}';
    }
}
