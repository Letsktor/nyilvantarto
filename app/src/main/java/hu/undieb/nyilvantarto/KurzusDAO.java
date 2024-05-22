package hu.undieb.nyilvantarto;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public interface KurzusDAO {
    DatabaseReference mDatabase =FirebaseDatabase.getInstance().getReference();
   default void writeNewKurzus(String userId,Kurzus kurzus) {
        mDatabase.child("Kurzusok").child(userId).setValue(kurzus);
    }

}
