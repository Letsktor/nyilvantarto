package hu.undieb.nyilvantarto.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import hu.undieb.nyilvantarto.model.Jelenlet;
import hu.undieb.nyilvantarto.model.KurzusokUtils;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.model.Ora;

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
        SharedPreferences sharedPref = getSharedPreferences("KurzusNev", Context.MODE_PRIVATE);
        String kurzus_nev = sharedPref.getString("kurzus_nev", null);

        addBtn.setOnClickListener(v->{
            KurzusokUtils.getInstance().addHallgato(kurzus_nev,"0",new Hallgato("Jankó","",""));
        });
        KurzusokUtils.getInstance().getHallgatok().observe(this,Hallgatok->{
            recyclerView.setAdapter(new HallgatokRecViewAdapter(Hallgatok,kurzus_nev));
            ArrayList<Jelenlet> temp=new ArrayList<>();
            for (Hallgato h:Hallgatok)
            {
                temp.add(new Jelenlet(h.getName()));
            }
            if(temp.size()<1)
            {
                KurzusokUtils.getInstance().addOra(kurzus_nev,new Ora(KurzusokUtils.getInstance().getCurrentDate(),0,temp));


            }
            else{
                KurzusokUtils.getInstance().updateOra(kurzus_nev,new Ora(KurzusokUtils.getInstance().getCurrentDate(),0),Integer.toString(KurzusokUtils.getInstance().getKurzus(kurzus_nev)));
            }

        });
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }

}
