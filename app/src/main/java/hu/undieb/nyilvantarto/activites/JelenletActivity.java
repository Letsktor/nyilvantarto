package hu.undieb.nyilvantarto.activites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.undieb.nyilvantarto.model.Hallgato;
import hu.undieb.nyilvantarto.activites.adapters.JelenletRecViewAdapter;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.model.Jelenlet;
import hu.undieb.nyilvantarto.model.KurzusokUtils;

public class JelenletActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Jelenlet> hallgatok=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jelenlet);
        recyclerView=findViewById(R.id.recViewJelenlet);
        SharedPreferences sharedPref = getSharedPreferences("KurzusNev", Context.MODE_PRIVATE);
        String kurzus_nev = sharedPref.getString("kurzus_nev", null);
        hallgatok.addAll(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().get(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1).getJelenlevok());
        recyclerView.setAdapter(new JelenletRecViewAdapter(hallgatok));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
