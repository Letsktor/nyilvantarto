package hu.undieb.nyilvantarto;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public interface KurzusDAO {
    DatabaseReference mDatabase =FirebaseDatabase.getInstance().getReference();
    static void writeNewKurzus(String userId, String name) {
        Kurzus kurzus = new Kurzus(name);
        mDatabase.child("Kurzusok").child(userId).setValue(kurzus);
    }

}
