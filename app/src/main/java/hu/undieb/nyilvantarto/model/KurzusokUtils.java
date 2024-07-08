package hu.undieb.nyilvantarto.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class KurzusokUtils {
    private static volatile KurzusokUtils instance;
    private KurzusDAO kDAO;
    private MutableLiveData<ArrayList<Kurzus>> kurzusok;
    private LiveData<ArrayList<Hallgato>> hallgatok;
    private LiveData<ArrayList<Ora>> orak;
    private LiveData<ArrayList<Jelenlet>> jelenlevok;
    private LiveData<ArrayList<Hallgato>> temp;
    private String kurzus="";
    private KurzusokUtils(KurzusDAO kDAO){
        this.kDAO=kDAO;

        if (null==kurzusok)
        {
            hallgatok=new MutableLiveData<>(new ArrayList<Hallgato>());
            kurzusok=new MutableLiveData<>();
            orak=new MutableLiveData<>();
            jelenlevok=new MutableLiveData<>();
            initData();

        }
    }

    private void initData() {
        kDAO.initData(kurzusok,hallgatok,orak,jelenlevok);
    }
    public static KurzusokUtils getInstance(){
        if (null == instance) {
            instance = new KurzusokUtils(new FireBaseKurzusDAO());
        }
        return instance;
    }
    public LiveData<ArrayList<Kurzus>> getKurzusok(){
        return kurzusok;

    }
    public Kurzus getKurzus(String kurzusnev)
    {
        Kurzus ki=new Kurzus();
        int index=-1;
        for (Kurzus k:kurzusok.getValue()) {
            if(k.getKurzusNev().equals(kurzusnev)==true)
            {   ki=k;
                index=kurzusok.getValue().indexOf(k);
            }
        }
        return ki;
    }

    public LiveData<ArrayList<Hallgato>> getHallgatok(String kurzusnev){
        ArrayList<Hallgato> choosenHallgatok=new ArrayList<>();

        //LiveData<ArrayList<Hallgato>> choosenHallgatok=new MutableLiveData<>(new ArrayList<>());
        for (Kurzus k:kurzusok.getValue()) {
                if(k.getKurzusNev().equals(kurzusnev)==true)
                {
                    //choosenHallgatok.getValue().addAll(k.getHallgatok());
                    choosenHallgatok.addAll(k.getHallgatok());
                    break;
                }
            }
        //((MutableLiveData<ArrayList<Hallgato>>) hallgatok).postValue(choosenHallgatok);
        if( !kurzus.equals(kurzusnev))
        {
            ((MutableLiveData<ArrayList<Hallgato>>) hallgatok).setValue(choosenHallgatok);
        }
        Log.d("HALLGatokegy",hallgatok.getValue().toString());
        Log.d("HALLGatokketto",choosenHallgatok.toString().toString());
        kurzus=kurzusnev;
        return hallgatok;

    }
    public void removeHallgato(String kurzusnev,String position)
    {
       kDAO.removeHallgato(kurzusnev,position);
    }
    public void addKurzus(Kurzus kurzus){
       kDAO.addKurzus(kurzus);
    }

    public String getCurrentDate(){
        return kDAO.getCurrentDate();
    }

    public void addHallgato(String kurzusnev,String id,Hallgato hallgato){
        kDAO.addHallgato(kurzusnev,id,hallgato);
        hallgatok.getValue().add(hallgato);

    }
    public void updateHallgato(Hallgato hallgato,String kurzusnev,String hallgatoId)
    {
        kDAO.updateHallgato(hallgato,kurzusnev,hallgatoId);
        hallgatok.getValue().set(Integer.parseInt(hallgatoId),hallgato);
    }
    public void addOra(String kurzusnev, Ora ora)
    {
      kDAO.addOra(kurzusnev,ora);
    }
    public void updateOra(String kurzusnev,Ora ora,String oraId)
    {
        kDAO.updateOra(kurzusnev,ora,oraId);
    }
    public String getDayOfTheWeek()
    {
        return kDAO.getDayOfTheWeek();
    }
    public void updateJelenlet(String kurzusnev, String oraid, int position, Jelenlet jelenlet)
    {
        kDAO.updateJelenlet(kurzusnev,oraid,position,jelenlet);
    }
}
