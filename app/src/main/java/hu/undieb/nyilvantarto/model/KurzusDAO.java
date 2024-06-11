package hu.undieb.nyilvantarto.model;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public interface KurzusDAO {
    public void addKurzus(Kurzus kurzus);
    public void addHallgato(String kurzusnev,String id,Hallgato hallgato);
    public void initData(LiveData<ArrayList<Kurzus>> kurzusok,LiveData<ArrayList<Hallgato>> hallgatok,LiveData<ArrayList<Ora>> orak);
    public String getCurrentDate();
}
