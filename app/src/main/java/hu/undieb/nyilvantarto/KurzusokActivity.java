package hu.undieb.nyilvantarto;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class KurzusokActivity extends AppCompatActivity {
    private RecyclerView kurzusokRecView;
    private KurzusokRecViewAdapter adapter;
    private ArrayList<Kurzus> kurzusok=new ArrayList<>();
    private Button btnAdd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurzusok);
        KurzusokDAO.getInstance().getKurzusok().observe(this,Kurzusok->kurzusok=Kurzusok);
        kurzusokRecView=findViewById(R.id.kurzusRecView);
        btnAdd=findViewById(R.id.btnAdd);
        adapter=new KurzusokRecViewAdapter(this);
        kurzusokRecView.setAdapter(adapter);
        kurzusokRecView.setLayoutManager(new LinearLayoutManager(this));
        btnAdd.setOnClickListener(v->{
            KurzusokDAO.getInstance().addKurzus(new Kurzus("Prog 1"));
        });
        adapter.setKurzusok(kurzusok);


    }


}
