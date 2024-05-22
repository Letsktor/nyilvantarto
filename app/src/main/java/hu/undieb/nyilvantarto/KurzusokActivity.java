package hu.undieb.nyilvantarto;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KurzusokActivity extends AppCompatActivity {
    private RecyclerView kurzusokRecView;
    private KurzusokRecViewAdapter adapter;
    private ArrayList<Kurzus> kurzusok=new ArrayList<>();
    private KurzusDAO kurzusDAO=new KurzusDAO() {
        @Override
        public void writeNewKurzus(String userId, Kurzus kurzus) {
            KurzusDAO.super.writeNewKurzus(userId, kurzus);
        }
    };
    private Button btnAdd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurzusok);
        kurzusokRecView=findViewById(R.id.kurzusRecView);
        btnAdd=findViewById(R.id.btnAdd);
        adapter=new KurzusokRecViewAdapter(this);
        kurzusokRecView.setAdapter(adapter);
        kurzusokRecView.setLayoutManager(new LinearLayoutManager(this));
        btnAdd.setOnClickListener(v->{
            kurzusDAO.writeNewKurzus("dasdasdas",new Kurzus("Prog 1"));
        });
        //kurzusDAO.writeNewKurzus("dasdasdas",new Kurzus("Prog 1"));
        kurzusok.add(0, new Kurzus("Prog 1"));

        adapter.setKurzusok(kurzusok);
    }

}
