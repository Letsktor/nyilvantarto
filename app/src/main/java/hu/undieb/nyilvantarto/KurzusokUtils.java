package hu.undieb.nyilvantarto;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KurzusokUtils {
    private static volatile KurzusokUtils instance;
    private LiveData<ArrayList<Kurzus>> kurzusok;
    private DatabaseReference database= FirebaseDatabase.getInstance().getReference("Kurzusok");
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


}
