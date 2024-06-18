package hu.undieb.nyilvantarto.activites;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.undieb.nyilvantarto.model.Kurzus;
import hu.undieb.nyilvantarto.model.KurzusokUtils;
import hu.undieb.nyilvantarto.model.Ora;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.activites.adapters.KurzusokRecViewAdapter;

public class KurzusokActivity extends AppCompatActivity {
    private RecyclerView kurzusokRecView;
    private KurzusokRecViewAdapter adapter;
    private ArrayList<Kurzus> kurzusok=new ArrayList<>();
    private Button btnAdd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurzusok);

        kurzusokRecView=findViewById(R.id.kurzusRecView);
        btnAdd=findViewById(R.id.btnAdd);
        adapter=new KurzusokRecViewAdapter(kurzusok,this);
        kurzusokRecView.setAdapter(adapter);
        kurzusokRecView.setLayoutManager(new LinearLayoutManager(this));
        btnAdd.setOnClickListener(v->{
            List<Ora> temp=new ArrayList<>();
            temp.add(new Ora(KurzusokUtils.getInstance().getCurrentDate(),0));
            KurzusokUtils.getInstance().addKurzus(new Kurzus("Prog 1"));
        });
        KurzusokUtils.getInstance().getKurzusok().observe(this, Kurzusok->kurzusokRecView.setAdapter(new KurzusokRecViewAdapter(Kurzusok,this)));

    }


}
