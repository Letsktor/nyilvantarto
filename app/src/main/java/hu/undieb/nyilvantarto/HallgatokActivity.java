package hu.undieb.nyilvantarto;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HallgatokActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Hallgato> hallgatok=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hallgatok);
        recyclerView=findViewById(R.id.recViewHallgato);
        hallgatok.add(new Hallgato("Mate","123123213-32132131","o923993219"));
        recyclerView.setAdapter(new HallgatokRecViewAdapter(hallgatok));
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }
}
