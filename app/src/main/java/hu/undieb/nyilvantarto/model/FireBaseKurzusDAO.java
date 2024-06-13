package hu.undieb.nyilvantarto.model;

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

public class FireBaseKurzusDAO implements KurzusDAO{

    private DatabaseReference database= FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Kurzusok");
    @Override
    public void addKurzus(Kurzus kurzus) {
        database.child(kurzus.getKurzusNev()).setValue(kurzus);
    }

    @Override
    public void addHallgato(String kurzusnev, String id, Hallgato hallgato) {
        database.child(kurzusnev).child("orak").child(id).child("hallgatok").child("0").setValue(hallgato);
    }

    @Override
    public ArrayList<Hallgato> getHallgatok(Ora ora) {
        return ora.getHallgatok();
    }

    @Override
    public void initData(LiveData<ArrayList<Kurzus>> kurzusok,LiveData<ArrayList<Hallgato>> hallgatok,LiveData<ArrayList<Ora>> orak) {
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
    public String getCurrentDate(){
        Date date= new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }
}
