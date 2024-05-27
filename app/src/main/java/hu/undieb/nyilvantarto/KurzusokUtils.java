package hu.undieb.nyilvantarto;

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
    private LiveData<ArrayList<Kurzus>> kurzusok;
    private LiveData<ArrayList<Hallgato>> hallgatok;
    private LiveData<ArrayList<Ora>> orak;
    private DatabaseReference database= FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Kurzusok");
    private KurzusokUtils(){
        if (null==kurzusok)
        {
            hallgatok=new MutableLiveData<>();
            kurzusok=new MutableLiveData<>();
            initData();
        }
    }

    private void initData() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Kurzus> tempK=new ArrayList<>();
                ArrayList<Ora> tempO=new ArrayList<>();
                ArrayList<Hallgato> tempH=new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Kurzus kurzus=dataSnapshot.getValue((Kurzus.class));
                    tempK.add(kurzus);
                    for(Ora o:kurzus.getOrak())
                    {
                        tempO.add(o);
                        if(o.getDate().equals(getCurrentDate()))
                        {
                            tempH.addAll(o.getHallgatok());
                        }
                    }
                }
                ((MutableLiveData<ArrayList<Kurzus>>)kurzusok).setValue(tempK);
                ((MutableLiveData<ArrayList<Hallgato>>)hallgatok).setValue(tempH);
                ((MutableLiveData<ArrayList<Ora>>)orak).setValue(tempO);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static KurzusokUtils getInstance(){
        if (null == instance) {
            instance = new KurzusokUtils();
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
       database.child(kurzus.getKurzusNev()).setValue(kurzus);
    }
    public ArrayList<Hallgato> getHallgatok(Ora ora){

        return ora.getHallgatok();
    }
    public String getCurrentDate(){
        Date date= new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }
    public void addHallgato(String kurzusnev,String id,Hallgato hallgato){
        database.child(kurzusnev).child("orak").child(id).child("hallgatok").child("0").setValue(hallgato);
    }
    public void updateHallgato(Hallgato hallgato,String kurzusnev,String hallgatoId)
    {
        DatabaseReference ref=database.child(kurzusnev).child("orak").child(Integer.toString(orak.getValue().size()-1)).child("hallgatok").child(hallgatoId);

    }



}
