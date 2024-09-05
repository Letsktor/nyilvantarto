package hu.undieb.nyilvantarto.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Ora {
    private String date="";

    public int getJelenlevok_szama() {
        return jelenlevok_szama;
    }

    public void setJelenlevok_szama(int jelenlevok_szama) {
        this.jelenlevok_szama = jelenlevok_szama;
    }

    private int jelenlevok_szama;
    private ArrayList<Jelenlet> jelenlevok=new ArrayList<>();

    public ArrayList<Jelenlet> getJelenlevok() {
        return jelenlevok;
    }

    public void setJelenlevok(ArrayList<Jelenlet> jelenlevok) {
        this.jelenlevok = jelenlevok;
    }

    public Ora(String date, int jelenlevok_szama) {
        this.date = date;
        this.jelenlevok_szama=jelenlevok_szama;
        this.jelenlevok=new ArrayList<>();
    }
    public Ora(String date, int jelenlevok_szama,ArrayList<Jelenlet> jelenlevok) {
        this.date = date;
        this.jelenlevok_szama=jelenlevok_szama;
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



    @Override
    public String toString() {
        return "Ora{" +
                "date=" + date +
                ", jelenlevok=" + jelenlevok +
                '}';
    }
}
