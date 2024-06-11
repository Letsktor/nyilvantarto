package hu.undieb.nyilvantarto.activites;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hu.undieb.nyilvantarto.model.Ora;
import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.activites.adapters.StatisztikaRecViewAdapter;

public class StatisztikaActivity extends AppCompatActivity {
    RecyclerView statisztikaRecView;
    ArrayList<Ora> orak=new ArrayList<>();
    StatisztikaRecViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisztika);
        statisztikaRecView=findViewById(R.id.statisztikaRecView);
        orak.add(0,new Ora("03.11", 9));
        adapter=new StatisztikaRecViewAdapter(orak,this);
        statisztikaRecView.setAdapter(adapter);
        statisztikaRecView.setLayoutManager(new LinearLayoutManager(this));

    }
}
