package hu.undieb.nyilvantarto;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KurzusokDAO {
    private static KurzusokDAO instance;
    private ArrayList<Kurzus> kurzusok;
    private DatabaseReference database= FirebaseDatabase.getInstance().getReference("Kurzusok");
    private KurzusokDAO(){
        if (null==kurzusok)
        {
            kurzusok=new ArrayList<>();
            initData();
        }
    }

    private void initData() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Kurzus kurzus=dataSnapshot.getValue(Kurzus.class);
                    kurzusok.add(kurzus);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static KurzusokDAO getInstance(){
        if(null!=instance){
            return instance;
        }
        else{
            instance=new KurzusokDAO();
            return instance;
        }
    }
    public ArrayList<Kurzus> getKurzusok(){
        return kurzusok;
    }
    public void addKurzus(Kurzus kurzus){
       database.child("Kurzus").setValue(kurzus);
    }


}
