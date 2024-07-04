package hu.undieb.nyilvantarto.model;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public interface KurzusDAO {
    public void addKurzus(Kurzus kurzus);
    public void addHallgato(String kurzusnev,String id,Hallgato hallgato);
    public void initData(LiveData<ArrayList<Kurzus>> kurzusok,LiveData<ArrayList<Hallgato>> hallgatok,LiveData<ArrayList<Ora>> orak,LiveData<ArrayList<Jelenlet>> jelenlevok);
    public String getCurrentDate();
    public ArrayList<Hallgato> getHallgatok(Kurzus kurzus);
    public void removeHallgato(String kurzusnev,String position);
    public void updateHallgato(Hallgato hallgato,String kurzusnev,String hallgatoId);
    public void addOra(String kurzusnev,Ora ora);
    public void updateOra(String kurzusnev,Ora ora,String oraId);
    public void addJelenlet();

}
