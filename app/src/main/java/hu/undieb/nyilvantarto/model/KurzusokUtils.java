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
import java.util.Locale;

public class KurzusokUtils {
    private static volatile KurzusokUtils instance;
    private KurzusDAO kDAO;
    private LiveData<ArrayList<Kurzus>> kurzusok;
    private LiveData<ArrayList<Hallgato>> hallgatok;
    private LiveData<ArrayList<Ora>> orak;
    private DatabaseReference database= FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Kurzusok");
    private KurzusokUtils(KurzusDAO kDAO){
        this.kDAO=kDAO;

        if (null==kurzusok)
        {
            hallgatok=new MutableLiveData<>();
            kurzusok=new MutableLiveData<>();
            orak=new MutableLiveData<>();
            initData();
        }
    }

    private void initData() {
        kDAO.initData(kurzusok,hallgatok,orak);
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
    public LiveData<ArrayList<Hallgato>> getHallgatok(){

        return hallgatok;
    }
    public void removeHallgato(String kurzusnev,String id,String position)
    {
       DatabaseReference ref= database.child(kurzusnev).child("orak").child(id).child("hallgatok").child(position);
        ref.removeValue().addOnSuccessListener(aVoid -> {
            Log.d("KurzusokUtils", "Successfully removed hallgato at path: " + ref.toString());
        }).addOnFailureListener(e -> {
            Log.e("KurzusokUtils", "Failed to remove hallgato at path: " + ref.toString(), e);
        });
    }
    public void addKurzus(Kurzus kurzus){
       kDAO.addKurzus(kurzus);
    }
    public ArrayList<Hallgato> getHallgatok(Ora ora){

        return kDAO.getHallgatok(ora);
    }
    public String getCurrentDate(){
        return kDAO.getCurrentDate();
    }
    public void addHallgato(String kurzusnev,String id,Hallgato hallgato){
        kDAO.addHallgato(kurzusnev,id,hallgato);
    }
    public void updateHallgato(Hallgato hallgato,String kurzusnev,String hallgatoId)
    {
        DatabaseReference ref=database.child(kurzusnev).child("orak").child(Integer.toString(orak.getValue().size()-1)).child("hallgatok").child(hallgatoId);

    }



}
