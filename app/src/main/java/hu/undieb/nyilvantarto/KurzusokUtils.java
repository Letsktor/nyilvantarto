package hu.undieb.nyilvantarto;

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
    private DatabaseReference database= FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("Kurzusok");
    private KurzusokUtils(){
        if (null==kurzusok)
        {
            kurzusok=new MutableLiveData<>();
            initData();
        }
    }

    private void initData() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Kurzus> temp=new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Kurzus kurzus=dataSnapshot.getValue((Kurzus.class));
                    temp.add(kurzus);
                }
                ((MutableLiveData<ArrayList<Kurzus>>)kurzusok).setValue(temp);
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


}
