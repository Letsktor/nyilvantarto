package hu.undieb.nyilvantarto.activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.undieb.nyilvantarto.model.Hallgato;
import hu.undieb.nyilvantarto.activites.adapters.HallgatokRecViewAdapter;
import hu.undieb.nyilvantarto.model.KurzusokUtils;
import hu.undieb.nyilvantarto.R;

public class HallgatokActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Hallgato> hallgatok=new ArrayList<>();
    private Button addBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hallgatok);
        addBtn=findViewById(R.id.btnAdd);
        recyclerView=findViewById(R.id.recViewHallgato);
        Intent intent=getIntent();
        String kurzus_nev=intent.getStringExtra("kurzus_nev");

        addBtn.setOnClickListener(v->{
            KurzusokUtils.getInstance().addHallgato(kurzus_nev,"0",new Hallgato("Jankó","",""));
        });
        KurzusokUtils.getInstance().getHallgatok().observe(this,Hallgatok->{
            recyclerView.setAdapter(new HallgatokRecViewAdapter(Hallgatok,kurzus_nev));
        });
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }

}
