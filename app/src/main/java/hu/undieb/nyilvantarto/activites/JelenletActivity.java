package hu.undieb.nyilvantarto.activites;

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

public class JelenletActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Jelenlet> hallgatok=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jelenlet);
        recyclerView=findViewById(R.id.recViewJelenlet);
        hallgatok.add(new Jelenlet("Marika"));
        recyclerView.setAdapter(new JelenletRecViewAdapter(hallgatok));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
