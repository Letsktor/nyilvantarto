package hu.undieb.nyilvantarto.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FireBaseKurzusDAO implements KurzusDAO{

    private DatabaseReference database= FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Kurzusok");
    @Override
    public void addKurzus(Kurzus kurzus) {
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {

                }
                else{
                    database.child(kurzus.getKurzusNev()).setValue(kurzus);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void addHallgato(String kurzusnev, String id, Hallgato hallgato) {
        database.child(kurzusnev).child("hallgatok").child(id).setValue(hallgato);
    }

    @Override
    public ArrayList<Hallgato> getHallgatok(Kurzus kurzus) {
        return getHallgatok(kurzus);
    }


    @Override
    public void initData(LiveData<ArrayList<Kurzus>> kurzusok,LiveData<ArrayList<Hallgato>> hallgatok,LiveData<ArrayList<Ora>> orak,LiveData<ArrayList<Jelenlet>> jelenlevok) {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Kurzus> tempK=new ArrayList<>();
                ArrayList<Ora> tempO=new ArrayList<>();
                ArrayList<Hallgato> tempH=new ArrayList<>();
                ArrayList<Jelenlet> tempJ=new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Kurzus kurzus=dataSnapshot.getValue((Kurzus.class));
                    tempK.add(kurzus);
                    tempH.addAll(kurzus.getHallgatok());

                    for(Ora o:kurzus.getOrak())
                    {
                        tempO.add(o);
                        if(o.getDate().equals(getCurrentDate()))
                        {
                            tempJ.addAll(o.getJelenlevok());
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

    @Override
    public void removeHallgato(String kurzusnev, String position) {
        DatabaseReference ref= database.child(kurzusnev).child("hallgatok").child(position);
        ref.removeValue().addOnSuccessListener(aVoid -> {
            Log.d("KurzusokUtils", "Successfully removed hallgato at path: " + ref.toString());
        }).addOnFailureListener(e -> {
            Log.e("KurzusokUtils", "Failed to remove hallgato at path: " + ref.toString(), e);
        });
    }

    @Override
    public void updateHallgato(Hallgato hallgato, String kurzusnev, String hallgatoId) {
        DatabaseReference ref=database.child(kurzusnev).child("hallgatok").child(hallgatoId);
        Map<String,Object> updateFields=new HashMap<>();
        updateFields.put("name",hallgato.getName());
        updateFields.put("cardId",hallgato.getCardId());
        updateFields.put("cardNumber",hallgato.getCardNumber());
        ref.updateChildren(updateFields);
    }

    @Override
    public void addOra(String kurzusnev,Ora ora) {
        database.child(kurzusnev).child("orak").child("0").setValue(ora);

    }

    @Override
    public void updateOra(String kurzusnev, Ora ora, String oraid) {
        DatabaseReference ref=database.child(kurzusnev).child("orak").child(oraid);

        Map<String,Object> updateFields=new HashMap<>();
        updateFields.put("date",ora.getDate());
        updateFields.put("jelenlevok",ora.getJelenlevok());
        updateFields.put("jelenlevok_szama",ora.getJelenlevok_szama());
        ref.updateChildren(updateFields);
    }

    @Override
    public void addJelenlet() {

    }
}
