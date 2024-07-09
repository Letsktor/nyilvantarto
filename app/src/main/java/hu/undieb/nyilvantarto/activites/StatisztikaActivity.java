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

import hu.undieb.nyilvantarto.model.KurzusokUtils;
import hu.undieb.nyilvantarto.model.Ora;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.activites.adapters.StatisztikaRecViewAdapter;

public class StatisztikaActivity extends AppCompatActivity {
    RecyclerView statisztikaRecView;
    List<Ora> orak=new ArrayList<>();
    StatisztikaRecViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisztika);
        statisztikaRecView=findViewById(R.id.statisztikaRecView);
        SharedPreferences sharedPref = getSharedPreferences("KurzusNev", Context.MODE_PRIVATE);
        String kurzus_nev = sharedPref.getString("kurzus_nev", null);
        orak= KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak();
        adapter=new StatisztikaRecViewAdapter(orak,this);
        statisztikaRecView.setAdapter(adapter);
        statisztikaRecView.setLayoutManager(new LinearLayoutManager(this));

    }
}
